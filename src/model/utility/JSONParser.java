package model.utility;

import org.json.JSONObject;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * JSON parser responsible for parsing the data to map.
 */
public class JSONParser {

  /**
   * Parses the string data to Map.
   *
   * @param data the string data
   * @return the parsed map from the string data.
   */
  public static Map<LocalDate, Double> toMap(String data) {
    Map<LocalDate, Double> map = new HashMap<>();
    JSONObject json = new JSONObject(data);
    JSONObject stockData = (JSONObject) json.get("Time Series (Daily)");
    Iterator<String> keysItr = stockData.keys();
    while (keysItr.hasNext()) {
      String key = keysItr.next();
      Object value = ((JSONObject) stockData.get(key)).get("4. close");
      map.put(LocalDate.parse(key), Double.parseDouble((String) value));
    }
    return map;
  }
}
