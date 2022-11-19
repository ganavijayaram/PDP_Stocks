package model.stock;

import java.util.Date;

/**
 * A public interface for operations related to stocks.
 */
public interface StockModel {
  /**
   * Get closing value.
   *
   * @param ticker ticket
   * @param d      date
   * @return closing value
   */

  public double getClosingValue(String ticker, Date d);

  /**
   * Get lowest value.
   *
   * @param ticker ticker
   * @param d      date
   * @return lowest value
   */

  public double getLowValue(String ticker, Date d);

  /**
   * Get highest value.
   * @param ticker ticker
   * @param d date
   * @return highest value
   */

  public double getHighValue(String ticker, Date d);

  /**
   * Get opening value.
   *
   * @param ticker ticker
   * @param d      date
   * @return opening value
   */

  public double getOpenValue(String ticker, Date d);

  /**
   * Get the volume.
   *
   * @param ticker ticker
   * @param d      date
   * @return volume
   */

  public double getVolValue(String ticker, Date d);

  /**
   * A method to call the API.
   *
   * @param ticker stock
   */
  public void callAPI(String ticker);

}
