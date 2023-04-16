import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import model.stock.Stock;
import model.trade.Trade;

/**
 * Abstract class to test the features of Simulated Trade and Transactional Trade.
 */
public abstract class AbstractStockTradeTest {

  protected Trade<Stock> trade;

  @Test(expected = IllegalArgumentException.class)
  public void createNewTrade_InvalidStock() {
    this.trade = this.createTrade("GOO", 10.0,LocalDate.parse("2022-10-24"),
            1.0);
  }

  @Test
  public void tradeValue() {
    try {
      Double value = 987.2;
      LocalDate date = LocalDate.parse("2022-11-15");
      this.trade = this.createTrade("GOOG", 10.0, LocalDate.parse("2022-10-24"),
              1.0);
      Assert.assertNotNull(this.trade);
      Assert.assertEquals(value, this.trade.value(date),0.01);
    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  public void tradeQuantity() {
    try {
      this.trade = this.createTrade("GOOG", 101.0, LocalDate.parse("2022-10-24"),
              1.0);
      Assert.assertNotNull(this.trade);
      Assert.assertEquals(101.0, this.trade.quantity(),0.01);
    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  public void buy() {
    try {
      this.trade = this.createTrade("GOOG", 10.0, LocalDate.parse("2022-10-24"),
              1.0);
      this.trade.buy(3);
      Assert.assertNotNull(this.trade);
      Assert.assertEquals(13, this.trade.quantity(),0.01);
    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  public void sell() {
    try {
      this.trade = this.createTrade("GOOG", 10.0,LocalDate.parse("2022-10-24"),
              1.0);
      this.trade.sell(3);
      Assert.assertNotNull(this.trade);
      Assert.assertEquals(7, this.trade.quantity(),0.01);
    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  public void sell_exceptionCase() {
    try {
      this.trade = this.createTrade("GOOG", 10.0, LocalDate.parse("2022-10-24"),
              1.0);
      this.trade.sell(11);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      // Passes here
    }
  }

  @Test
  public void equals_sameRef() {
    try {
      this.trade = this.createTrade("GOOG", 10.0, LocalDate.parse("2022-10-24"),
              1.0);
      Assert.assertTrue(this.trade.equals(this.trade));

    } catch (IllegalArgumentException e) {
      Assert.fail();
    }
  }

  @Test
  public void equalsTest() {
    try {
      this.trade = this.createTrade("GOOG", 10.0, LocalDate.parse("2022-10-24"),
              1.0);
      Trade<Stock> other = this.createTrade("goog", 11.0,
              LocalDate.parse("2022-10-24"), 100.0);
      Assert.assertTrue(this.trade.equals(other));

    } catch (IllegalArgumentException e) {
      Assert.fail();
    }
  }

  @Test
  public void equals_diffType() {
    try {
      this.trade = this.createTrade("GOOG", 10.0, LocalDate.parse("2022-10-24"),
              1.0);
      Assert.assertFalse(this.trade.equals(123));

    } catch (IllegalArgumentException e) {
      Assert.fail();
    }
  }

  protected abstract Trade<Stock> createTrade(String name, Double shares, LocalDate date,
                                              Double commission);
}
