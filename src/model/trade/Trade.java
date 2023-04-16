package model.trade;

import java.time.LocalDate;

/**
 * Represents a trade(i.e a transaction).
 *
 * @param <T> the type of trade.
 */
public interface Trade<T> {

  /**
   * Returns the trade(i.e. stock or other types) which was traded(purchased/sold) by a user.
   *
   * @return the transactions in a trade.
   */
  T get();

  /**
   * Returns the value of a trade performed by the user at a specified date.
   *
   * @param date The date at which the trade value needs to be determined.
   * @return the value of the trade at the specified date.
   */
  double value(LocalDate date);

  /**
   * Returns the number of shares of T in a trade.
   *
   * @return the number of the shares of T in trade.
   */
  double quantity();

  /**
   * Method responsible for buying a specified number of shares.
   *
   * @param quantity the number of shares of T that needs to be purchased.
   */
  void buy(double quantity);

  /**
   * Method responsible for selling a specified number of shares.
   *
   * @param quantity the number of shares of T that needs to be purchased.
   * @throws IllegalArgumentException if the number of the shares are not less than specified.
   */
  void sell(double quantity) throws IllegalArgumentException;

  /**
   * Returns the date of the trade/transaction.
   *
   * @return the date of the trade.
   * @throws IllegalArgumentException if the trade does not have a date associated.
   */
  LocalDate tradeDate() throws UnsupportedOperationException;

  /**
   * Returns the commission fee associated with a trade.
   *
   * @return the commission fee associated.
   * @throws UnsupportedOperationException if the trade does not support commission fee.
   */
  double commission() throws UnsupportedOperationException;
}
