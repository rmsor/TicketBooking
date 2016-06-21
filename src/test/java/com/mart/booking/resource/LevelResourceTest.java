package com.mart.booking.resource;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mart.booking.data.LevelResponse;
import com.mart.booking.service.LevelService;

/**
 * test class for LevelResource
 * @author rmsor
 *
 */
public class LevelResourceTest {

	@InjectMocks
	LevelResource levelResource;

	@Mock
	LevelService levelService;

	LevelResponse levelResponse;

	@Before
	public void setup() {
		levelResource = new LevelResource();
		MockitoAnnotations.initMocks(this);

		levelResponse = new LevelResponse();
	}

	@Test
	public void testGetAllLevels(){
		
		
		when(levelService.getLevels()).thenReturn(levelResponse);
		
		ResponseEntity<LevelResponse> response=levelResource.getAllLevels();
		
		assertNotNull(response);
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
	}
}
