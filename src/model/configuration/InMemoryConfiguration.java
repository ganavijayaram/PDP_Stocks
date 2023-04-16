package model.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of Configuration interface to maintain all the configuration settings
 * in memory for faster lookups. This is a singleton class meaning will only have one instance
 * of this class to support same configuration across the application.
 */
public class InMemoryConfiguration implements Configuration<String, String> {

  // singleton instance of the class
  private static final Configuration<String, String> instance = new InMemoryConfiguration();

  // map containing all the key,value pairs for all config settings.
  private final Map<String, String> configMap;

  /**
   * Initializes an instance of the In-memory Configuration and adds the key value pair for
   * data provider for the Stock Data as WEB based provider. This configuration can be used
   * across the application to determine any and every configuration for the application.
   */
  private InMemoryConfiguration() {
    // TODO : As of now sets the data provider for the application as web based provider
    // Can switch between the provider based on user input as well later on using
    // the singleton object of the configuration class.
    this.configMap = new HashMap<>();
    this.configMap.put("data-provider", "WEBAPI");
  }

  /**
   * Return the singleton instance of the In-memory Configuration and adds the key value pair for
   * data provider for the Stock Data as WEB based provider. This configuration can be used
   * across the application to determine any and every configuration for the application.
   *
   * @return singleton object of Configuration class.
   */
  public static Configuration<String, String> getInstance() {
    return instance;
  }

  @Override
  public String get(String key) {
    return this.configMap.get(key);
  }

  @Override
  public void put(String key, String value) {
    this.configMap.put(key, value);
  }

  @Override
  public boolean contains(String key) {
    return this.configMap.containsKey(key);
  }

  @Override
  public Set<String> keys() {
    return configMap.keySet();
  }
}
