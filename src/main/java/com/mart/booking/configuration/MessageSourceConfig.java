package com.mart.booking.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig {
	
	private static final String ERROR_PROPERTIES="properties/error.properties";
	
	@Bean
	public ResourceBundleMessageSource messageSource(){
		
		ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
		
		messageSource.setBasename(ERROR_PROPERTIES);
		
		return messageSource;
	}
}
