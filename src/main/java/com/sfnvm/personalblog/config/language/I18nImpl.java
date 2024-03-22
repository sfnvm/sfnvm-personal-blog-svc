package com.sfnvm.personalblog.config.language;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

/**
 * The type 18 n.
 */
@Slf4j
@Service
@RequiredArgsConstructor
class I18nImpl implements I18n {

  private final ResourceBundleMessageSource messageSource;

  @Override
  public String get(String key) {
    try {
      return messageSource.getMessage(key, new Object[0], LocaleContextHolder.getLocale());
    } catch (NoSuchMessageException ex) {
      log.error("No message with code: {} for locale: {}", key, LocaleContextHolder.getLocale());
      return null;
    }
  }

  @Override
  public String get(String key, Object... objects) {
    if (objects != null && CollectionUtils.isEmpty(List.of(objects))) {
      return get(key);
    }

    try {
      return messageSource.getMessage(key, objects, LocaleContextHolder.getLocale());
    } catch (NoSuchMessageException ex) {
      log.error("No message with code: {} for locale: {}", key, LocaleContextHolder.getLocale());
      return null;
    }
  }
}
