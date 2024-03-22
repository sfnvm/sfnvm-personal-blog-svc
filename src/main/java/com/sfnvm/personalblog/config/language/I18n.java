package com.sfnvm.personalblog.config.language;

/**
 * The interface 18 n.
 */
public interface I18n {

  /**
   * Get string.
   *
   * @param key the key
   * @return the string
   */
  String get(final String key);

  /**
   * Get string.
   *
   * @param key     the key
   * @param objects the objects
   * @return the string
   */
  String get(final String key, final Object... objects);
}
