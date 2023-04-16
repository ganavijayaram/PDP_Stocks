package model.stockpriceprovider;

import model.configuration.InMemoryConfiguration;

/**
 * Factory that returns the stock data provider for fetching Stock Data based on the
 * in memory configuration settings of the application.
 */
public class StockDataProviderFactory {

  // the key used to get the type of data provider initialized by the user
  // in the application configuration.
  private static final String key = "data-provider";

  /**
   * Returns the StockDataProvider that needs to be used to query the stock data
   * for a specified stock and date.
   *
   * @return the stock data provider.
   */
  public static StockDataProvider getDataProvider() {

    // Get the data provider setting from the configuration setting.
    String strType = InMemoryConfiguration.getInstance().get(key);
    // Convert it to the Data Provider Type.
    DataProviderType type = DataProviderType.valueOf(strType);
    switch (type) {
      case WEBAPI:
        return WebAPIStockDataProvider.getInstance();
      case MOCKAPI:
        return MockStockDataProvider.getInstance();
      default:
        throw new IllegalArgumentException("Illegal Data Provider Type");
    }
  }
}
