package model.stock;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A public class which implements StockModel interface.
 */
public class StockModelImpl implements StockModel {

  private String apiKey = "5XJSOM36JMEQXFEL";
  private URL url = null;

  private Map<String, Map<String, Map<String, Double>>> allcosts = new HashMap<>();

  @Override
  public double getClosingValue(String ticker, Date d) throws IllegalArgumentException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    if (allcosts.containsKey(ticker)) {
      if (allcosts.get(ticker).containsKey(dateFormat.format(d))) {
        return allcosts.get(ticker).get(dateFormat.format(d)).get("close");
      } else {
        callAPI(ticker);
        if (!allcosts.get(ticker).containsKey(dateFormat.format(d))) {
          throw new IllegalArgumentException("No data found for this date");
        }
      }
    } else {
      callAPI(ticker);
      if (!allcosts.get(ticker).containsKey(dateFormat.format(d))) {
        throw new IllegalArgumentException("No data found for this date");
      }
    }

    return allcosts.get(ticker).get(dateFormat.format(d)).get("close");
  }

  @Override
  public double getHighValue(String ticker, Date d) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return allcosts.get(ticker).get(dateFormat.format(d)).get("high");
  }

  @Override
  public double getLowValue(String ticker, Date d) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return allcosts.get(ticker).get(dateFormat.format(d)).get("low");
  }

  @Override
  public double getOpenValue(String ticker, Date d) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return allcosts.get(ticker).get(dateFormat.format(d)).get("open");
  }

  @Override
  public double getVolValue(String ticker, Date d) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return allcosts.get(ticker).get(dateFormat.format(d)).get("volume");
  }

  private void buildURL(String ticker) {
    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + ticker + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("The Alpha Vantage API has either changed or no longer works.");
    }
  }

  @Override
  public void callAPI(String ticker) {
    buildURL(ticker.toUpperCase());

    InputStream is;
    StringBuilder sb = new StringBuilder();

    try {
      is = url.openStream();
      int b;

      while ((b = is.read()) != -1) {
        sb.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No cost data found for " + ticker + ".");
    }
    Map<String, Map<String, Double>> data = new HashMap<>();
    String[] value1 = sb.toString().split("\n");
    String[] type = {"open", "high", "low", "close", "volume"};
    for (int i = 1; i < value1.length; i++) {
      String[] value2 = value1[i].split(",");
      data.put(value2[0], new HashMap<>());
      for (int j = 1; j < value2.length; j++) {
        data.get(value2[0]).put(type[j - 1], Double.parseDouble(value2[j]));
      }
    }
    allcosts.put(ticker, data);
  }
}
