package model.dataparser;

/**
 * The interface responsible for parsing the string data to type T.
 * Generic interface to support parsing of string to a variety of
 * data type.
 *
 * @param <T> the type of the data type to be parsed to.
 */
public interface DataParser<T> {

  /**
   * Method responsible for parsing the string data to the type T.
   *
   * @param data the string format of data.
   * @return parsed data
   */
  T parse(String data);
}
