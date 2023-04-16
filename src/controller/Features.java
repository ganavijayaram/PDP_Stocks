package controller;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

/**
 * Interface to implement all the features of Stocks Project.
 */
public interface Features {

  /**
   * To create flexible portfolio.
   * @param name Name of the portfolio
   * @param purchases Map of all the purchases
   */
  void createInflexiblePortfolio(String name , Map<String, Double> purchases);

  /**
   * To create a Flexible Portfolio.
   *
   * @param name name of the portfolio
   * @return if the operation passed/failed.
   */
  boolean createFlexiblePortfolio(String name);

  /**
   * To get all the portfolios.
   * @return all the portfolios
   */
  Set<String> getPortfolios();

  /**
   * To buy the stocks.
   * @param portfolio Name of the portfolio
   * @param stock Name of the stock
   * @param shares Number of shares
   * @param date Date on which you want to purchase
   * @param commission the commission
   */

  void buyStock(String portfolio, String stock, Double shares, LocalDate date,
                Double commission);

  /**
   * To sell the stocks.
   * @param portfolio Name of the portfolio
   * @param stock Name of the stock
   * @param shares Number of shares
   * @param date Date on which you want to make the purchase
   * @param commission the commission involved while purchasing the stock
   */
  void sellStock(String portfolio, String stock, Double shares, LocalDate date,
                 Double commission);

  /**
   * To find the value of the portfolio.
   * @param portfolio Name of the portfolio
   * @param date the date on which value of the portfolio has to be found
   */
  void value(String portfolio, LocalDate date);

  /**
   * To get the investment on the portfolio.
   * @param portfolio Name of the portfolio
   * @param date the date on which costbasis of the portfolio has to be found
   */
  void costBasis(String portfolio, LocalDate date);

  /**
   * To get the composition of the portfolio.
   * @param portfolio Name of the portfolio
   * @return the composition of the portfolio
   */
  String composition(String portfolio);

  /**
   * To get the composition of the portfolio on a particular date.
   * @param portfolio Name of the portfolio
   * @param date the date on which the composition has to be viewed
   * @return return on the composition on a particular day
   */
  String composition(String portfolio, LocalDate date);

  /**
   * To load a portfolio from the data source.
   * @param dataSource the datasource name
   */
  void load(String dataSource);

  /**
   * To save the portfolios to a particular datasource.
   * @param dataSource the datasource to which the portfolio should be saved
   * @param portfolioName Name of the portfolio
   */
  void save(String dataSource, String portfolioName);

  /**
   * To get value of the portfolio from given start date to given end date.
   * @param portfolioName Name of the portfolio
   * @param from start date from which the value has to be fetched
   * @param end the end date till which the date has to be fetched
   * @return the values of portfolio over the period.
   */
  Map<LocalDate, Double> values(String portfolioName, LocalDate from, LocalDate end);

  /**
   * To Create strategies.
   * @param name Name of the portfolio
   * @param principal the investment amount
   * @param weights the proportion
   * @param start the start date from which the investment has to be made
   * @param end the end date till which the investement has to be made
   * @param days the frequency of the investment
   * @param commission the commission amount involved
   *
   * @return if the operation was successful
   */
  boolean createStrategy(String name, Double principal, Map<String, Double> weights,
                         LocalDate start, LocalDate end, int days, Double commission);

  /**
   * To get all the strategies.
   * @return to return all the strategies
   */
  Set<String> getAllStrategy();

  /**
   * To apply the given strategies.
   * @param portfolioName Name of the portfolio
   * @param strategy the startegy which has to be applied
   *
   */
  void applyStrategy(String portfolioName, String strategy);
}
