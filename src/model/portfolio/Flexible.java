package model.portfolio;

import java.util.Date;
import java.util.TreeMap;

/**
 * A public interface for operations related to Flexible portfolio.
 */
public interface Flexible extends Portfolio {
  /**
   * A method to sell stocks.
   * @param portFolioName portfolio name
   * @param tickerSymbol stock
   * @param date date
   * @param givenShares quantity
   * @param commission commission fee for the given transaction
   */
  public void sellStocks(String portFolioName, String tickerSymbol, Date date, Integer givenShares,
                         Double commission);

  /**
   * A method to plot performance.
   * @param portFolioName portfolio name
   * @param startDate start date of the range
   * @param endDate end date of the range
   * @return the list containing timestamp and number of asterisks
   */
  public TreeMap<String, Integer> performance(String portFolioName, Date startDate, Date endDate);


  /**
   * To check investment for a given date.
   * @param portFolioName portfolio name
   * @param date date
   * @return return the investment
   */

  double checkInvestment(String portFolioName, Date date);
}
