package com.mart.booking.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * bean configuration for messageSource
 * @author rpathak
 *
 */
@Configuration
public class MessageSourceConfig {
	
	private static final String ERROR_PROPERTIES="properties/error.properties";
	
	/**
	 * initialize messageSource Bean
	 * @return
	 */
	@Bean
	public ResourceBundleMessageSource messageSource(){
		
		ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
		
		messageSource.setBasename(ERROR_PROPERTIES);
		
		return messageSource;
	}
}
