import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import model.stock.Stock;
import model.stock.StockImpl;
import model.trade.Trade;
import model.trade.TransactionalStockTrade;

/**
 * Tests for TransactionalStockTrade class.
 */
public class TransactionalStockTradeTest extends AbstractStockTradeTest {

  @Test
  public void createNewTransactionalTrade() {
    try {
      String expected = "Stock Symbol : AAPL,Quantity : 10.0,Date of Purchase : 2022-10-24," +
              "Commission Fee : 10.0\n";
      this.trade = this.createTrade("AAPL", 10.0, LocalDate.parse("2022-10-24"),
              10.0);
      Assert.assertNotNull(this.trade);
      Assert.assertEquals(expected, this.trade.toString());
    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  public void createNewTransactionalTrade_MarketClosed() {
    try {
      LocalDate date = LocalDate.parse("2022-10-23");
      this.trade = this.createTrade("AAPL", 12.0, date,
              1.0);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      //Pass here
    }
  }

  @Test
  public void getTrade() {
    try {
      String expected = "Stock Symbol : AAPL,Quantity : 12.0,Date of Purchase : 2022-10-24," +
              "Commission Fee : 1.0\n";
      Stock expectedTrade = new StockImpl("AAPL");
      this.trade = this.createTrade("AAPL", 12.0, LocalDate.parse("2022-10-24"),
              1.0);
      Assert.assertNotNull(this.trade);
      Assert.assertEquals(expected, this.trade.toString());
      Assert.assertEquals(expectedTrade, this.trade.get());
    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  public void tradeDate() {
    try {
      LocalDate date = LocalDate.parse("2022-10-24");
      this.trade = this.createTrade("AAPL", 12.0, date,
              1.0);
      LocalDate actual = this.trade.tradeDate();
      Assert.assertEquals(date, actual);
    } catch (UnsupportedOperationException e) {
      Assert.fail();
    }
  }

  @Test
  public void commission() {
    try {
      LocalDate date = LocalDate.parse("2022-10-24");
      this.trade = this.createTrade("AAPL", 12.0, date,
              1.0);
      Double actual = this.trade.commission();
      Assert.assertEquals(1.0, actual, 0.01);
    } catch (UnsupportedOperationException e) {
      Assert.fail();
    }
  }


  @Override
  protected Trade<Stock> createTrade(String name, Double shares, LocalDate date,
                                     Double commission) {
    return new TransactionalStockTrade(name, shares, date, commission);
  }
}
