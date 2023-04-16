package model.stockpriceprovider;

import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import model.cache.CacheProvider;
import model.cache.InMemoryCacheProvider;
import model.utility.JSONParser;

/**
 * Implementation of the stock data provider which uses WEB based API to fetch the value
 * of the date. Stores the data in in-memory cache for faster look up.
 */
public class WebAPIStockDataProvider implements StockDataProvider {

  // singleton object for the class.
  private static StockDataProvider instance = new WebAPIStockDataProvider();
  // API key for the web API.
  private final String relativeURL;
  private final String API_KEY;
  // cache provider for faster read and to reduce redundant API calls.
  private final CacheProvider<String, Map<LocalDate, Double>> stockData;

  /**
   * Initializes the singleton object of WebAPIStockDataProvider.
   */
  private WebAPIStockDataProvider() {
    this.relativeURL = "https://www.alphavantage.co/query?"
            + "function=TIME_SERIES_DAILY&outputsize=full";
    this.API_KEY = "ZTKFVCAPQ9L1VWRP";
    this.stockData = new InMemoryCacheProvider<>();
  }

  /**
   * Returns the singleton object of the Web Data provider which can be used to
   * query stock data for any specified stock and date..
   *
   * @return Object of web api stock data provider.
   */
  public static StockDataProvider getInstance() {
    return instance;
  }


  @Override
  public double price(String stock, LocalDate date) throws IllegalArgumentException {
    while (!this.stockData.get(stock).containsKey(date)) {
      date = date.minusDays(1);
    }
    return this.stockData.get(stock).get(date);
  }

  @Override
  public boolean isValid(String stock) {
    try {
      // if the cache contains the data then return true.
      if (this.stockData.contains(stock.toUpperCase())) {
        return true;
      }
      // else call the WEB API and add the data to the cache.
      Map<LocalDate, Double> response = this.getAPIResponse(stock);
      this.stockData.put(stock.toUpperCase(), response);
    } catch (IOException e) {
      return false;
    } catch (JSONException e) {
      return false;
    }
    return true;
  }

  @Override
  public boolean isAvailable(LocalDate date) {
    try {
      var stockList = new ArrayList<String>();
      stockList.addAll(this.stockData.keys());
      var stock = "GOOG";
      if (stockList.size() > 0) {
        stock = stockList.get(0);
      }
      if (!this.stockData.contains(stock)) {
        Map<LocalDate, Double> response = this.getAPIResponse(stock);
        this.stockData.put(stock.toUpperCase(), response);
      }
      return this.stockData.get(stock.toUpperCase()).containsKey(date);
    }
    catch (IOException e) {
      return false;
    } catch (JSONException e) {
      return false;
    }
  }

  /**
   * Returns the parsed API response for a specified stock.
   *
   * @param stock stock symbol.
   * @return API response for the stock.
   * @throws IOException if the reader is not able to read API response.
   */
  private Map<LocalDate, Double> getAPIResponse(String stock) throws IOException {
    String url = this.relativeURL + "&symbol=" + stock + "&apikey="
            + this.API_KEY + "&datatype=json";
    // gets the string response from the API
    String data = get(url);
    // parses the API response to a map.
    Map<LocalDate, Double> map = JSONParser.toMap(data);
    // return the parsed response.
    return map;
  }


  /**
   * Returns the response from the API in string format.
   *
   * @param url The WEB API URL.
   * @return the response from the API in string.
   */
  private String get(String url) {
    try {
      URL fullURL = new URL(url);
      InputStream inputStream = fullURL.openStream();
      InputStreamReader reader = new InputStreamReader(inputStream,
              Charset.forName("UTF-8"));
      // reads the data from the API
      BufferedReader textReader = new BufferedReader(reader);
      String jsonText = readAll(textReader);
      // returns the data
      return jsonText;
    } catch (MalformedURLException e) {
      throw new RuntimeException("the Alpha-vantage API has either changed or "
              + "no longer works");
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid stock");
    }
  }

  /**
   * Reads all the text from the Reader.
   *
   * @param reader The reader from which the text needs to be read.
   * @return the text in string.
   * @throws IOException if I/O error occurs.
   */
  private static String readAll(Reader reader) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    // appends all the characters to the string builder
    while ((cp = reader.read()) != -1) {
      sb.append((char) cp);
    }
    // returns the final string formed.
    return sb.toString();
  }
}
