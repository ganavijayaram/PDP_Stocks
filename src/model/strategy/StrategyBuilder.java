package model.strategy;

import java.time.LocalDate;

/**
 * An interface for the strategy builder.
 */
public interface StrategyBuilder {

  /**
   * Sets the name.
   *
   * @param name name to be set
   * @return returns the strategy builder
   */
  StrategyBuilder setName(String name);

  /**
   * Adds a stock along with its weight.
   *
   * @param stock the stock to be added.
   * @param weight the weight of the stock.
   * @return returns the strategy builder.
   * @throws IllegalArgumentException if the stock or weights are invalid.
   */
  StrategyBuilder addStock(String stock, Double weight) throws IllegalArgumentException;

  /**
   * Sets the commission.
   * @param commission commission to be set
   * @return returns the strategy builder
   */
  StrategyBuilder setCommission(Double commission);

  /**
   * To set the investment.
   * @param principal sets the investment
   * @return returns a strategy builder
   */
  StrategyBuilder setPrincipal(Double principal);

  /**
   * To set the start date.
   * @param date date to be set as start
   * @return returns a strategy builder
   */
  StrategyBuilder setStartDate(LocalDate date);

  /**
   * To set the end date.
   * @param date setting the end date.
   * @return returns the strategy builder
   */
  StrategyBuilder setEndDate(LocalDate date);

  /**
   * To set the frequency.
   * @param frequency sets the frequency
   * @return returns the strategy builder
   */
  StrategyBuilder setFrequency(int frequency);

  /**
   * Builds the strategy object and returns.
   *
   * @return Strategy object
   * @throws IllegalArgumentException if the weights don't add up to 100.
   */
  Strategy build() throws IllegalArgumentException;

}
