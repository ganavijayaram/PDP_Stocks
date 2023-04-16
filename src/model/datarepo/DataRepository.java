package model.datarepo;


/**
 * This interface is responsible for saving the objects to the
 * data source. Serves as a repository layer for the business logic.
 *
 * @param <T> type of object.
 */
public interface DataRepository<T> {

  void setDataSource(String dataSource) throws Exception;
  /**
   * Saves the trade of type T to the data source.
   *
   * @param trade the object that needs to be saved.
   * @throws Exception throws Exception if there are any
   *                   exceptions while saving data to data source.
   */

  void save(T trade) throws Exception;

  /**
   * Reads all the values from the data source and returns in String format.
   *
   * @return Formatted String.
   * @throws Exception throws Exception if there are any
   *                   exceptions while reading data from data source.
   */
  T read() throws Exception;
}
