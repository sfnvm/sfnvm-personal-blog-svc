package com.sfnvm.personalblog.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * <a
 * href="https://stackoverflow.com/questions/43195987/configured-objectmapper-not-used-in-spring-boot-webflux">
 * Reference
 * </a>
 */
@Configuration
public class JacksonConfig {

  @Bean
  public JavaTimeModule javatimeModule() {
    return new JavaTimeModule();
  }

  @Primary
  @Bean
  public ObjectMapper objectMapper() {
    return buildDefaultCustomObjectMapper();
  }

  //////////////////////////
  //// Private Methods /////
  //////////////////////////

  private ObjectMapper buildDefaultCustomObjectMapper() {
    val objectMapper = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        /*
         * Modules
         * */
        .registerModule(javatimeModule())
        /*
         * Serialization Features
         * */
        .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        .configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true)
        /*
         * Deserialization Features
         * */
        .configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
        .configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true)
        .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true)
        /*
         * Jackson Include
         * */
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

    val config = objectMapper.getDeserializationConfig();
    objectMapper.setConfig(config.with(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS));

    return objectMapper;
  }
}
