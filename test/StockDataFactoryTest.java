import org.junit.Assert;
import org.junit.Test;

import model.configuration.InMemoryConfiguration;
import model.stockpriceprovider.MockStockDataProvider;
import model.stockpriceprovider.StockDataProvider;
import model.stockpriceprovider.StockDataProviderFactory;
import model.stockpriceprovider.WebAPIStockDataProvider;

/**
 * Class for testing StockDataProvider Factory.
 */
public class StockDataFactoryTest {

  @Test
  public void getDataProvider_WebAPI() {
    InMemoryConfiguration.getInstance().put("data-provider", "WEBAPI");
    StockDataProvider provider = StockDataProviderFactory.getDataProvider();
    Assert.assertTrue(provider instanceof WebAPIStockDataProvider);
  }

  @Test
  public void getDataProvider_Mock() {
    InMemoryConfiguration.getInstance().put("data-provider", "MOCKAPI");
    StockDataProvider provider = StockDataProviderFactory.getDataProvider();
    Assert.assertTrue(provider instanceof MockStockDataProvider);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getDataProvider_Exception() {
    InMemoryConfiguration.getInstance().put("data-provider", "");
    StockDataProvider provider = StockDataProviderFactory.getDataProvider();
  }
}
