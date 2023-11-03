package com.brianeno.sample.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Brian Enochson
 **/
@Configuration
public class AppConfiguration {
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}