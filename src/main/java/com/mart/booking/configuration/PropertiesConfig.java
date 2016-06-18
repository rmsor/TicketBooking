package com.mart.booking.configuration;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class PropertiesConfig {
	
	private static final String SQL_PROPERTIES="/WEB-INF/properties/sql.properties";
	
	private static final String ERROR_PROPERTIES="/WEB-INF/properties/error.properties";
	
	private static final String COMMON_PROPERTIES="/WEB-INF/properties/common.properties";
	
	
//	@Bean
//	public PropertiesFactoryBean sqlProperties(){
//		
//		PropertiesFactoryBean factoryBean=new PropertiesFactoryBean();
//		
//		factoryBean.setLocation(new ClassPathResource(SQL_PROPERTIES));
//		
//		return factoryBean;
//	}
//	
//	@Bean
//	public PropertiesFactoryBean errorProperties(){
//		
//		PropertiesFactoryBean factoryBean=new PropertiesFactoryBean();
//		
//		factoryBean.setLocation(new ClassPathResource(ERROR_PROPERTIES));
//		
//		return factoryBean;
//	}
//	
//	@Bean
//	public PropertiesFactoryBean commonProperties(){
//		
//		PropertiesFactoryBean factoryBean=new PropertiesFactoryBean();
//		
//		factoryBean.setLocation(new ClassPathResource(COMMON_PROPERTIES));
//		
//		return factoryBean;
//	}
}
