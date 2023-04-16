import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import model.portfolio.Portfolio;
import model.portfolio.SimulatedPortfolio;
import model.stock.Stock;
import model.trade.SimulatedStockTrade;
import model.trade.Trade;

/**
 * Class responsible for testing SimulatedPortfolio.
 */
public class SimulatedPortfolioTest extends AbstractPortfolioTest {


  @Test(expected = UnsupportedOperationException.class)
  public void add() {
    Set<Trade<Stock>> shares = new HashSet<>();
    shares.add(this.createPurchase("GOOG", 10.0,
            LocalDate.parse("2022-10-24"), 1.0));
    this.portfolio = createPortfolio("portfolio1", shares);
    this.portfolio.buy("NOW", 10.0,
            LocalDate.parse("2022-11-04"), 30.0);

  }


  @Test(expected = UnsupportedOperationException.class)
  public void sell() {
    Set<Trade<Stock>> shares = new HashSet<>();
    shares.add(this.createPurchase("GOOG", 10.0,
            LocalDate.parse("2022-10-24"), 1.0));
    this.portfolio = createPortfolio("portfolio1", shares);
    this.portfolio.sell("GOOG", 10.0,
            LocalDate.parse("2022-11-04"), 30.0);

  }

  @Test(expected = UnsupportedOperationException.class)
  public void costBasis() {
    Set<Trade<Stock>> shares = new HashSet<>();
    shares.add(this.createPurchase("GOOG", 10.0,
            LocalDate.parse("2022-10-24"), 1.0));
    this.portfolio = createPortfolio("portfolio1", shares);
    this.portfolio.costBasis(LocalDate.parse("2022-11-04"));

  }


  @Override
  protected Portfolio createPortfolio(String name, Set<Trade<Stock>> shares) {
    return new SimulatedPortfolio(name, shares);
  }

  @Override
  protected Trade<Stock> createPurchase(String stock, Double quantity,
                                        LocalDate date, Double commission) {
    return new SimulatedStockTrade(stock, quantity);
  }
}
