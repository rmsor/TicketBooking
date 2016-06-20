package com.mart.booking.configuration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.mart.booking.configuration.MessageSourceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=MessageSourceConfig.class, loader=AnnotationConfigContextLoader.class)
public class MessageSourceConfigTest {
	
	@Autowired
	private MessageSource messageSource;	

	@Test
	public void testMessageSource() {
		
		assertEquals(ResourceBundleMessageSource.class, messageSource.getClass());
		
	}
}
