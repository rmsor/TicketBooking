package com.mart.booking.configuration;

import static org.junit.Assert.assertNotNull;

import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PropertiesConfig.class, loader=AnnotationConfigContextLoader.class)
public class PropertiesConfigTest {
	
	@Autowired 
	private Properties sqlProperties;
	
	@Autowired 
	private Properties commonProperties;
	
	@Test
	public void testSqlProperties() {
		
		assertNotNull(sqlProperties);
	}
	
	@Test
	public void testCommonProperties() {
		
		assertNotNull(commonProperties);
	}
}
