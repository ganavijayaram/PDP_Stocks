package model.stockpriceprovider;

import java.time.LocalDate;

/**
 * The interface used to represent the stock data provider. Contains methods to
 * evaluate the price of the stock at  the specified date.
 */
public interface StockDataProvider {

  /**
   * Returns the price of the given stock on the given date.
   *
   * @param stock stock whose price needs to be determined.
   * @param date  the date at which the price needs to be evaluated.
   * @return the price of stock.
   */
  double price(String stock, LocalDate date) throws IllegalArgumentException;

  /**
   * Checks if the stock symbol is valid.
   *
   * @param stock stock
   * @return true/false
   */
  boolean isValid(String stock);

  /**
   * Checks if the stock data is available for the stock on the specified date. The stock
   * data is not available on Weekends and national holiday for any specified stock.
   *
   * @param date the date at which the stock data availability needs to be determined.
   * @return if the data is available for the stock on the specified date.
   */
  boolean isAvailable(LocalDate date);

}
