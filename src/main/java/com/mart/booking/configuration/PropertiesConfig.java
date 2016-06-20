package com.mart.booking.configuration;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class PropertiesConfig {
	
	private static final String SQL_PROPERTIES="properties/sql.properties";
		
	private static final String COMMON_PROPERTIES="properties/common.properties";
	
	
	@Bean
	public PropertiesFactoryBean sqlProperties(){
		
		PropertiesFactoryBean factoryBean=new PropertiesFactoryBean();
		
		factoryBean.setLocation(new ClassPathResource(SQL_PROPERTIES));
		
		return factoryBean;
	}
	
	@Bean
	public PropertiesFactoryBean commonProperties(){
		
		PropertiesFactoryBean factoryBean=new PropertiesFactoryBean();
		
		factoryBean.setLocation(new ClassPathResource(COMMON_PROPERTIES));
		
		return factoryBean;
	}
}
