package com.mart.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mart.booking.dao.LevelDao;
import com.mart.booking.data.LevelResponse;
import com.mart.booking.domain.Level;

@Service
public class LevelServiceImpl implements LevelService{
	
	@Autowired
	LevelDao levelDao;
	
	@Override
	public LevelResponse getLevels() {
		
		LevelResponse levelResponse=new LevelResponse();
		
		List<Level> levels=levelDao.list();
		
		levelResponse.setLevels(levels);
		
		return levelResponse;
	}

}
