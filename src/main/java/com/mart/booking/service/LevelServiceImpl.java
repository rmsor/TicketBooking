package com.mart.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mart.booking.dao.LevelDAO;
import com.mart.booking.data.LevelResponse;
import com.mart.booking.domain.Level;

@Service
public class LevelServiceImpl implements LevelService{
	
	@Autowired
	LevelDAO levelDAO;
	
	@Override
	public LevelResponse getLevels() {
		
		LevelResponse levelResponse=new LevelResponse();
		
		List<Level> levels=levelDAO.list();
		
		levelResponse.setLevels(levels);
		
		return levelResponse;
	}

}
