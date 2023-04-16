package model.stock;

import java.time.LocalDate;

/**
 * This interface represent a stock of any company.
 */
public interface Stock {

  /**
   * Returns the unique ticker symbol for a Stock.
   *
   * @return the unique abbreviation for a stock
   */
  String name();

  /**
   * Returns the price of this stock at the specified date.
   *
   * @param date The date at which price of the stock needs to be evaluated.
   * @return The stock price.
   */
  double price(LocalDate date);


}
