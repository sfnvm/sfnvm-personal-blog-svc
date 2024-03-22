package com.sfnvm.personalblog.config;

import static com.fasterxml.jackson.annotation.JsonFormat.DEFAULT_TIMEZONE;

import jakarta.annotation.PostConstruct;
import java.util.TimeZone;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TimeZoneConfig {

  @PostConstruct
  void setDefaultTimeZone() {
    TimeZone.setDefault(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
  }
}
