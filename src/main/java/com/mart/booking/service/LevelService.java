package com.mart.booking.service;

import com.mart.booking.data.LevelResponse;
import com.mart.booking.entity.Level;

public interface LevelService {
	
	LevelResponse getLevels();
	
	Level getById(Integer id);
}
