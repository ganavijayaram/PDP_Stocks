import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.configuration.Configuration;
import model.configuration.InMemoryConfiguration;

/**
 * Test for In Memory Configuration.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InMemoryConfigurationTest {

  private final Configuration<String, String> configuration;

  public InMemoryConfigurationTest() {
    this.configuration = InMemoryConfiguration.getInstance();
  }

  @Test
  public void getDefaultDataProvider() {
    var value = this.configuration.get("data-provider");
    Assert.assertEquals("WEBAPI", value);
  }

  @Test
  public void putDefaultDataProvider() {
    this.configuration.put("data-provider", "MOCKAPI");
    var value = this.configuration.get("data-provider");
    Assert.assertEquals("MOCKAPI", value);
  }

  @Test
  public void containsDefaultProvider() {
    Assert.assertTrue(this.configuration.contains("data-provider"));
  }

  @Test
  public void allDefaultConfigs() {
    var keys = this.configuration.keys();
    Assert.assertEquals(1, keys.size());
    for (String key : keys) {
      Assert.assertEquals("data-provider", key);
    }
  }

  @Test
  public void zSet() {
    this.configuration.put("data-provider", "WEBAPI");
    var value = this.configuration.get("data-provider");
    Assert.assertEquals("WEBAPI", value);
  }


}
