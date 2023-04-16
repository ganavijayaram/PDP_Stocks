import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import model.stock.Stock;
import model.stock.StockImpl;
import model.trade.SimulatedStockTrade;
import model.trade.Trade;

/**
 * Tests for SimulatedStockTrade.
 */
public class SimulatedStockTradeTest extends AbstractStockTradeTest {


  @Test
  public void createNewSimulatedTrade() {
    try {
      String expected = "Stock Symbol : AAPL,Quantity : 10.0\n";
      this.trade = new SimulatedStockTrade("AAPL", 10.0);
      Assert.assertNotNull(this.trade);
      Assert.assertEquals(expected, this.trade.toString());
    } catch (Exception e) {
      Assert.fail();
    }
  }


  @Test
  public void tradeDate() {
    try {
      this.trade = new SimulatedStockTrade("GOOG", 10.0);
      this.trade.tradeDate();
      Assert.fail();
    } catch (UnsupportedOperationException e) {
      // Passes here
    }
  }

  @Test
  public void getTrade() {
    try {
      String expected = "Stock Symbol : AAPL,Quantity : 12.0\n";
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
  public void commission() {
    try {
      LocalDate date = LocalDate.parse("2022-10-24");
      this.trade = this.createTrade("AAPL", 12.0, date,
              1.0);
      Double actual = this.trade.commission();
      Assert.fail();
    } catch (UnsupportedOperationException e) {
      // Pass here
    }
  }

  @Override
  protected Trade<Stock> createTrade(String name, Double shares, LocalDate date,
                                     Double commission) {
    return new SimulatedStockTrade(name, shares);
  }
}
