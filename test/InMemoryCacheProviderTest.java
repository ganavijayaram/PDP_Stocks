import org.junit.Assert;
import org.junit.Test;

import model.cache.CacheProvider;
import model.cache.InMemoryCacheProvider;

/**
 * Test class for in memory cache Provider.
 */
public class InMemoryCacheProviderTest {

  private final CacheProvider<String, String> cache;

  public InMemoryCacheProviderTest() {
    this.cache = new InMemoryCacheProvider<>();
    this.cache.put("AAPL", "120");
  }


  @Test
  public void getDefaultDataProvider1() {

    var value = this.cache.get("AAPL");
    Assert.assertEquals("120", value);
  }

  @Test
  public void getDefaultDataProvider2() {

    var value = this.cache.get("NFT");
    Assert.assertNull(value);
  }

  @Test
  public void putDefaultDataProvider() {
    this.cache.put("MSFT", "150");
    var value = this.cache.get("MSFT");
    Assert.assertEquals("150", value);
  }

  @Test
  public void containsDefaultProvider() {
    Assert.assertTrue(this.cache.contains("AAPL"));
  }

  @Test
  public void allDefaultConfigs() {
    var keys = this.cache.keys();
    Assert.assertNotNull(keys);
  }

}
