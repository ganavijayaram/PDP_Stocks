import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import model.stock.Stock;
import model.stock.StockImpl;

/**
 * Testing for StockImpl.
 */
public class StockImplTest {

  @Test
  public void validStockSymbol() {
    Stock stock = new StockImpl("TSLA");
    Assert.assertEquals("TSLA", stock.name());
    Assert.assertEquals("TSLA", stock.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void InvalidStockSymbol() {
    new StockImpl("GOO");
  }

  @Test
  public void value1() {
    Stock stock = new StockImpl("NKE");
    LocalDate date = LocalDate.parse("2014-12-01");
    Double actual = stock.price(date);
    Double expected = 97.69;
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void equals_sameRef() {
    Stock stock = new StockImpl("NKE");
    Assert.assertTrue(stock.equals(stock));
  }

  @Test
  public void equals_diffType() {
    Stock stock = new StockImpl("NKE");
    Assert.assertFalse(stock.equals(12));
  }

  @Test
  public void equalsTest() {
    Stock stock = new StockImpl("NKE");
    Assert.assertTrue(stock.equals(new StockImpl("nke")));
  }

  @Test
  public void notEquals() {
    Stock stock = new StockImpl("NKE");
    Assert.assertFalse(stock.equals(new StockImpl("IBM")));
  }
}
