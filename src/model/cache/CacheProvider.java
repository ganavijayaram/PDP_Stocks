package model.cache;

import java.util.Set;

/**
 * Works a cache for quick access of data to reduce fetch time for the records.
 * Support different types of key,value pair for the cache. Supports
 * operations to add data to the cache, retrieve data from cache in O(1) and
 * query if cache contains the key.
 *
 * @param <K> the type of Key
 * @param <V> the type of value
 */
public interface CacheProvider<K, V> {

  /**
   * Query the value for a key from the cache. Supports
   * fast retrieval of the key,value pair from the cache.
   *
   * @param key the key for the cache.
   * @return the value stored in cache against the key.
   */
  V get(K key);

  /**
   * Adds the key value pair to the cache. Used to add data into
   * the cache. If the key already exists, it will be overwritten.
   *
   * @param key   the key of the data.
   * @param value the value of the data.
   */
  void put(K key, V value);

  /**
   * Returns if the key is available in the cache.
   *
   * @param key the key of the cache.
   * @return true/false.
   */
  boolean contains(K key);

  Set<K> keys();

}
