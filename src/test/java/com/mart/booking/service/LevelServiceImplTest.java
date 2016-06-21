package com.mart.booking.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mart.booking.data.LevelResponse;
import com.mart.booking.dao.LevelDAO;
import com.mart.booking.entity.Level;

public class LevelServiceImplTest {

	@InjectMocks
	LevelService levelService;

	@Mock
	LevelDAO levelDAO;

	@Before
	public void setup() {
		levelService=new LevelServiceImpl();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetLevels(){
		List<Level> levels=new ArrayList<Level>();
		Level level=new Level();
		level.setLevelId(1);
		level.setLevelName("testLevel");
		levels.add(level);
		
		when(levelDAO.list()).thenReturn(levels);
		
		LevelResponse levelResponse=levelService.getLevels();
		
		assertNotNull(levelResponse);
		assertEquals(levelResponse.getLevels().size(),levels.size());
		assertEquals(levelResponse.getLevels().get(0).getLevelId(),levels.get(0).getLevelId());
		assertEquals(levelResponse.getLevels().get(0).getLevelName(),levels.get(0).getLevelName());
	}
	
	@Test
	public void testGetLevelsNull(){
		
		when(levelDAO.list()).thenReturn(null);
		
		LevelResponse levelResponse=levelService.getLevels();
		
		assertNull(levelResponse.getLevels());
	}
	
	@Test
	public void testGetById(){
		Level level=new Level();
		level.setLevelId(1);
		level.setLevelName("testLevel");
				
		when(levelDAO.getById(1)).thenReturn(level);
		
		Level levelRes=levelService.getById(1);
		
		assertNotNull(levelRes);
		assertEquals(levelRes.getLevelId(),level.getLevelId());
		assertEquals(levelRes.getLevelName(),level.getLevelName());
	}
	
	@Test
	public void testGetByIdNull(){
		when(levelDAO.getById(1)).thenReturn(null);		
		Level levelRes=levelService.getById(1);
		
		assertNull(levelRes);
	}
}
