package model.portfolio;

import java.time.LocalDate;
import java.util.Map;

import model.strategy.Strategy;

/**
 * Representation of Portfolio which contains a set of trades comprising stocks.
 * Supports operations such as getting the name of the portfolio, total value of the portfolio
 * on a specified date, add/purchase of stock trade , sale of stock trade, total money
 * invested in the portfolio and evaluating composition of the portfolio.
 */
public interface Portfolio {

  /**
   * Returns the name of the portfolio. Acts as a unique identifier for a portfolio.
   * Multiple portfolio cannot have same name in the application.
   *
   * @return the name of the portfolio.
   */
  String name();

  /**
   * Returns the total value of portfolio factoring in all the purchases and sale made
   * till the specified date along with the price fluctuations for the trade on the
   * specified date.
   *
   * @param date The date at which the total value of the portfolio needs to be determined.
   * @return the total value of the portfolio.
   * @throws IllegalArgumentException if the date specified is in the future.
   */
  double value(LocalDate date) throws IllegalArgumentException;

  /**
   * Method for making a trade of the stock (i.e. buying the stock) and adding it in
   * the portfolio.
   *
   * @param stock      the stock that the user wishes to buy.
   * @param shares     the amount of shares of the stock.
   * @param date       the date of purchase.
   * @param commission the commission fee involved in the trade.
   * @throws UnsupportedOperationException for portfolios which do not support
   *                                       trading options.
   */
  void buy(String stock, Double shares, LocalDate date, Double commission)
          throws UnsupportedOperationException;

  /**
   * Method responsible for selling the specified shares of stock from the portfolio.
   *
   * @param stock      the stock that the user wishes to sell.
   * @param shares     the amount of shares of the stock.
   * @param date       the date of purchase.
   * @param commission the commission fee involved in the trade.
   * @throws IllegalArgumentException      if the portfolio does not contain the stock
   *                                       or do not have enough shares of the stock to
   *                                       make the sale.
   * @throws UnsupportedOperationException for portfolios which do not support
   *                                       trading options
   */
  void sell(String stock, Double shares, LocalDate date, Double commission) throws
          IllegalArgumentException, UnsupportedOperationException;

  /**
   * Evaluated and returns the total money invested in the portfolio till the
   * specified date. Takes in account the trade transactions as well as the
   * commission involved for making the trade transactions.
   *
   * @param date the date at which cost basis needs to be evaluated.
   * @return the total invested amount.
   * @throws UnsupportedOperationException for portfolio which do not support trading
   *                                       options.
   */
  double costBasis(LocalDate date) throws UnsupportedOperationException;

  /**
   * Returns the aggregated composition of a Portfolio (i.e. describing each stock with the number
   * of shares that a portfolio contains) at a date later than all the trades in the portfolio.
   *
   * @return the stock composition of portfolio.
   */
  String composition();

  /**
   * Returns the aggregated composition of a Portfolio (i.e. describing each stock with the number
   * of shares that a portfolio contains) at a specified date which takes in account only
   * the trade transactions performed till specified date.
   *
   * @param date the date at which the composition needs to be evaluated.
   * @return the composition of a portfolio.
   */
  String composition(LocalDate date);

  /**
   * Returns the total value of portfolio at equal intervals within the time period specified
   * by the user.
   *
   * @param from the start date of the time period.
   * @param to   the last date of the time period.
   * @return the value of portfolios at equal intervals starting from start and end date specified
   */
  Map<LocalDate, Double> values(LocalDate from, LocalDate to) throws IllegalArgumentException;

  /**
   * Applies a given investment strategy to the portoflio.
   *
   * @param strategy the strategy to be applied.
   * @throws UnsupportedOperationException If the portfolio is inflexible portfolio.
   */
  void applyStrategy(Strategy strategy) throws UnsupportedOperationException;
}
