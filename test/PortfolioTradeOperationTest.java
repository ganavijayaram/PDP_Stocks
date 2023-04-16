import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import model.datarepo.FileRepository;
import model.portfolio.SimulatedPortfolio;
import model.portfolio.TransactionalPortfolio;
import model.stock.Stock;
import model.stocktradings.PortfolioTradeOperation;
import model.stocktradings.TradeOperation;
import model.trade.SimulatedStockTrade;
import model.trade.Trade;
import model.trade.TransactionalStockTrade;

/**
 * Testing for PortfolioTradeOperation class.
 */
public class PortfolioTradeOperationTest {

  private TradeOperation trade;

  @Before
  public void setup() throws Exception {
    var fileRepo = FileRepository.getInstance();
    fileRepo.setDataSource("res/portfolio.txt");
    this.trade = new PortfolioTradeOperation(fileRepo);
    Set<Trade<Stock>> shares = new HashSet<>();
    shares.add(new SimulatedStockTrade("GOOG", 2.0));
    this.trade.create(new SimulatedPortfolio("test1", shares));

    Set<Trade<Stock>> transactions = new HashSet<>();
    transactions.add(new TransactionalStockTrade("GOOG", 2.0,
            LocalDate.parse("2016-10-24"), 10.0));
    this.trade.create(new TransactionalPortfolio("test2", transactions, new HashSet<>()));
  }

  @Test
  public void createNewSimulatedPortfolio() {
    try {
      Set<Trade<Stock>> shares = new HashSet<>();
      shares.add(new SimulatedStockTrade("GOOG", 2.0));
      this.trade.create(new TransactionalPortfolio("test3", shares, new HashSet<>()));
      Assert.assertNotNull(this.trade);
    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  public void createNewTransactionalPortfolio() {
    try {
      Set<Trade<Stock>> shares = new HashSet<>();
      shares.add(new TransactionalStockTrade("GOOG", 2.0,
              LocalDate.parse("2022-10-24"), 10.0));
      this.trade.create(new TransactionalPortfolio("test3", shares, new HashSet<>()));
      Assert.assertNotNull(this.trade);
    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  public void createNewPortfolio_AlreadyExists() {
    try {
      this.trade.create(new SimulatedPortfolio("test1", new HashSet<>()));
      Assert.fail();
    } catch (IllegalArgumentException e) {
      // Pass here
    }
  }


  @Test
  public void getPortfolio_MissingPortfolio() {
    try {
      this.trade.get("test3");
      Assert.fail();
    } catch (IllegalArgumentException e) {
      // Pass here
    }
  }

  @Test
  public void getPortfolio() {
    try {
      Object portfolio1 = this.trade.get("test1");
      Object portfolio2 = this.trade.get("test2");
      String expectedP1 = "TYPE : SIMULATED\n"
              + "Portfolio Name : test1\n"
              + "STOCKS : \n"
              + "Stock Symbol : GOOG,Quantity : 2.0\n"
              + "------ END ------\n";

      String expectedP2 = "TYPE : TRANSACTIONAL\n"
              + "Portfolio Name : test2\n"
              + "PURCHASES : \n"
              + "Stock Symbol : GOOG,Quantity : 2.0,Date of Purchase : 2016-10-24,"
              + "Commission Fee : 10.0\n"
              + "SALE : \n"
              + "------ END ------\n";
      Assert.assertNotNull(portfolio1);
      Assert.assertNotNull(portfolio2);
      Assert.assertEquals(expectedP1, portfolio1.toString());
      Assert.assertEquals(expectedP2, portfolio2.toString());

    } catch (IllegalArgumentException e) {
      Assert.fail();
    }
  }

  @Test
  public void getAllTrades() {
    try {
      Set<String> allTrades = this.trade.all();
      Assert.assertNotNull(allTrades);
      Assert.assertTrue(allTrades.size() == 2);
      Assert.assertTrue(allTrades.containsAll(new ArrayList<>(
              Arrays.asList("test1", "test2"))));
    } catch (IllegalArgumentException e) {
      Assert.fail();
    }
  }

  @Test
  public void savePortfolio() {
    try {
      this.trade.save("test1");
      this.trade.save("test2");
    } catch (IllegalArgumentException e) {
      Assert.fail();
    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  public void savePortfolio_Fail() {
    try {
      this.trade.save("test10");
      Assert.fail();
    } catch (Exception e) {
      // Test pass
    }
  }

  @Test
  public void loadPortfolio() {
    try {
      this.trade.load();
      Assert.assertTrue(this.trade.all().containsAll(new
              ArrayList<>(Arrays.asList("test1", "test2"))));
    } catch (IllegalArgumentException e) {
      Assert.fail();
    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  public void loadPortfolio_Fail() {
    try {
      var fileRepo = FileRepository.getInstance();
      fileRepo.setDataSource("res");
      this.trade = new PortfolioTradeOperation(fileRepo);
      this.trade.load();
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.fail();
    } catch (Exception e) {
      // PASS
    }
  }

}
