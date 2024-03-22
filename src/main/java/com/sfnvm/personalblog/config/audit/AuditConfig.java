package com.sfnvm.personalblog.config.audit;

import jakarta.annotation.Nonnull;
import java.util.Optional;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@Slf4j
@Configuration
@EnableJpaAuditing
public class AuditConfig {

  @Bean
  AuditorAware<String> auditorProvider() {
    return new AuditAwareImpl();
  }
}

class AuditAwareImpl implements AuditorAware<String> {

  @Nonnull
  @Override
  public Optional<String> getCurrentAuditor() {
    return Optional.of(AuditUtil.getCurrentAuditor());
  }
}

@Slf4j
@UtilityClass
class AuditUtil {

  private final String ANONYMOUS = "ANONYMOUS";

  public String getCurrentAuditor() {
    try {
      User userPrincipal = (User) SecurityContextHolder.getContext()
          .getAuthentication()
          .getPrincipal();
      return userPrincipal.getUsername();
    } catch (Exception ex) {
      log.debug(ex.getMessage());
      return ANONYMOUS;
    }
  }
}