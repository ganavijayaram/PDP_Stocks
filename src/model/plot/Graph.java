package model.plot;

import java.util.Date;
import java.util.List;
import java.util.TreeMap;

/**
 * A public interface for operations related to the graph.
 */
public interface Graph {
  /**
   * A method to find difference in years.
   *
   * @param date1 start date
   * @param date2 end date
   * @return difference
   */
  int findYears(String date1, String date2);

  /**
   * A method to find difference in months.
   *
   * @param date1 start date
   * @param date2 end date
   * @return difference
   */

  int findMonth(String date1, String date2);

  /**
   * A method to find difference in days.
   *
   * @param date1 start date
   * @param date2 end date
   * @return difference
   */

  public int findDays(String date1, String date2);

  /**
   * To get the maximum value.
   *
   * @param map   map containing all the values
   * @param start start date
   * @param end   end date
   * @return maximum value
   */

  public Double maxValue(TreeMap<Date, Double> map, Date start, Date end);

  /**
   * To get the minimum value.
   *
   * @param map   map containing all the values
   * @param start start date
   * @param end   end date
   * @return minimum value
   */

  public Double minValue(TreeMap<Date, Double> map, Date start, Date end);

  /**
   * To get base value.
   *
   * @param max maximum value
   * @param min minimum value
   * @return returns scale of the graph
   */

  public double getBaseValue(double max, double min);

  /**
   * Y axis values.
   *
   * @param numOfLines number of lines
   * @return List of string
   */

  public List<String> yAxis(int numOfLines);
}