package model.configuration;

import model.cache.CacheProvider;

/**
 * Configuration interface responsible for maintaining
 * all the configuration settings for the application.
 *
 * @param <K> the type of key
 * @param <V> the type of value
 */
public interface Configuration<K, V> extends CacheProvider<K, V> {
  // add all the extra methods that the configuration interface
  // offers to maintain all the config setting in the application.

}
