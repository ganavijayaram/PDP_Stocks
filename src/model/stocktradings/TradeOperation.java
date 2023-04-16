package model.stocktradings;

import java.util.Set;

/**
 * Interface to perform trade operations such as creating a new trade, getting the trade
 * performed by the user, all the available trades in the application and saving and loading
 * the trades from a data source.
 */
public interface TradeOperation<T> {

  /**
   * Used to create a trade specified by the user.
   *
   * @param trade The trade object.
   */
  void create(T trade) throws IllegalArgumentException;

  /**
   * Returns the specific trade object created by the user with the specified identifier.
   *
   * @param trade trade object
   * @return formatted string.
   * @throws IllegalArgumentException if the trade does not exist.
   */
  T get(String trade) throws IllegalArgumentException;


  /**
   * Returns the name of all the trades performed by a user.
   *
   * @return All trade names
   */
  Set<String> all();

  /**
   * Saves the specified trade in a data-source.
   *
   * @param trade the trade object.
   * @throws Exception if the operation fails.
   */
  void save(String trade) throws Exception;

  /**
   * Loads all the trades available in the data-source to the application.
   * @throws Exception if the operation fails
   */
  void load() throws Exception;


}
