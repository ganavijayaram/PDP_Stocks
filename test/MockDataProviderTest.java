import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import model.stockpriceprovider.MockStockDataProvider;
import model.stockpriceprovider.StockDataProvider;

/**
 * Class used to test MockDataProvider which is used to query on stock data.
 */
public class MockDataProviderTest {

  @Test
  public void canCreateObject() {
    StockDataProvider provider = MockStockDataProvider.getInstance();
    Assert.assertNotNull(provider);
  }

  @Test
  public void price() {
    StockDataProvider provider = MockStockDataProvider.getInstance();
    Assert.assertTrue(provider.isAvailable(LocalDate.parse("2022-10-10")));
  }

  @Test
  public void valid() {
    StockDataProvider provider = MockStockDataProvider.getInstance();
    Assert.assertTrue(provider.isValid("IBM"));
  }

  @Test
  public void invalid() {
    StockDataProvider provider = MockStockDataProvider.getInstance();
    Assert.assertFalse(provider.isValid("NOW"));
  }

  @Test
  public void price1() {
    StockDataProvider provider = MockStockDataProvider.getInstance();
    LocalDate date = LocalDate.parse("2022-11-16");
    Assert.assertEquals(2056.0, provider.price("IBM",
            date), 0.01);
  }

  @Test
  public void price2() {
    StockDataProvider provider = MockStockDataProvider.getInstance();
    LocalDate date = LocalDate.parse("2022-11-15");
    Assert.assertEquals(2055.0, provider.price("IBM",
            date), 0.01);
  }
}
