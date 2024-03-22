package com.sfnvm.personalblog.config.language;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

/**
 * The type Locale config.
 */
@Configuration
class LocaleConfig {

  /**
   * Locale resolver accept header locale resolver.
   *
   * @return the accept header locale resolver
   */
  @Bean
  public AcceptHeaderLocaleResolver localeResolver() {
    final AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
    resolver.setDefaultLocale(Locale.US);
    return resolver;
  }

  /**
   * Message source resource bundle message source.
   *
   * @return the resource bundle message source
   */
  @Bean
  public ResourceBundleMessageSource messageSource() {
    final ResourceBundleMessageSource source = new ResourceBundleMessageSource();
    source.setBasename("languages/messages");
    return source;
  }
}
