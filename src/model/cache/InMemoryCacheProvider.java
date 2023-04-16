package model.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of cache as in memory cache to reduce the
 * time for data retrieval. In Memory Cache keeps the copy of the
 * data in memory for faster retrieval and to reduce the
 * network and database calls.
 *
 * @param <K> the type of key
 * @param <V> the type of value
 */
public class InMemoryCacheProvider<K, V> implements CacheProvider<K, V> {

  // uses the map as key, value pair for the cache.
  private Map<K, V> map;

  /**
   * Instantiates an object of In Memory cache and sets the
   * cache to empty.
   */
  public InMemoryCacheProvider() {
    map = new HashMap<>();
  }

  @Override
  public V get(Object key) {
    // if the cache does not contain the key return null
    if (!map.containsKey(key)) {
      return null;
    }
    return map.get(key);
  }

  @Override
  public void put(K key, V value) {
    // Do not override the data is cache already contains the key.
    map.put(key, value);
  }

  @Override
  public boolean contains(K key) {
    return map.containsKey(key);
  }

  @Override
  public Set<K> keys() {
    return map.keySet();
  }
}
