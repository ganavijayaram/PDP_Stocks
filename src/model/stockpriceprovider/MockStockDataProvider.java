package model.stockpriceprovider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.cache.CacheProvider;
import model.cache.InMemoryCacheProvider;

/**
 * Implementation of the stock data provider which uses mock data to mimic the real time
 * data of the stock. Stores the data in in-memory cache for faster look up.
 */
public class MockStockDataProvider implements StockDataProvider {

  // singleton object for the class.
  private static StockDataProvider instance = new MockStockDataProvider();
  // cache to store the data for the stock
  private final CacheProvider<String, Map<LocalDate, Double>> stockData;
  // valid set of stocks.
  private Set<String> validStocks;

  /**
   * Creates an instance of random stock provider which initializes the cache of the
   * stock data.
   */
  private MockStockDataProvider() {

    stockData = new InMemoryCacheProvider<>();
    validStocks = new HashSet<>();
    // Maintains a valid list of stocks supported by the application.
    validStocks.addAll(new ArrayList<>(Arrays.asList("AAPL", "MSFT", "GOOG", "GOOGL",
            "AMZN", "TSLA", "BRK/A", "BRK/B", "UNH", "XOM", "JNJ", "V", "WMT", "JPM", "CVX",
            "NVDA", "LLY", "PG", "TSM", "MA", "HD", "BAC", "PFE", "NVO", "ABBV", "KO", "MRK",
            "PEP", "META", "COST", "ORCL", "MCD", "TMO", "SHEL", "AVGO", "DIS", "TMUS", "AZN",
            "TM", "CSCO", "ASML", "DHR", "WFC", "ACN", "ABT", "NVS", "BABA", "BMY", "COP",
            "VZ", "NEE", "CRM", "SCHW", "TXN", "LIN", "AMGN", "UPS", "NKE", "MS", "ADBE", "PM",
            "RTX", "TTE", "HON", "CMCSA", "T", "QCOM", "RY", "ELV", "CVS", "LMT", "NFLX", "BHP",
            "IBM", "UNP", "GS", "LOW", "DE", "INTC", "EQNR", "TD", "MDT", "HDB", "CAT", "UL",
            "INTU", "SAP", "AXP", "SNY", "HSBC", "SPGI", "BP", "ADP", "BUD", "PLD", "SBUX",
            "GILD", "AMD", "CI", "BLK", "AMT", "PYPL", "DEO", "BA", "C", "SONY", "CB", "RIO",
            "MDLZ")));
  }

  /**
   * Returns the singleton object for the class.
   *
   * @return mock stock data provider.
   */
  public static StockDataProvider getInstance() {
    return instance;
  }

  @Override
  public double price(String stock, LocalDate date) {
    // Mocks the stock data
    double price = (stock.toUpperCase().hashCode() / 10000)
            + date.getYear() + date.getDayOfMonth() + date.getMonthValue();
    // Adds the stock data to cache to make sure there are no discrepancies.
    if (stockData.contains(stock)) {
      if (!stockData.get(stock).containsKey(date)) {
        stockData.get(stock).put(date, price);
      }
    } else {
      Map<LocalDate, Double> obj = new HashMap<>();
      obj.put(date, price);
      stockData.put(stock, obj);
    }
    return price;
  }

  @Override
  public boolean isValid(String stock) {
    return this.validStocks.contains(stock.toUpperCase());
  }

  @Override
  public boolean isAvailable(LocalDate date) {
    return true;
  }
}
