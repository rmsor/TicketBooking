package com.mart.booking.service;

import com.mart.booking.data.LevelResponse;
import com.mart.booking.entity.Level;

public interface LevelService {
	
	/**
	 * get all levels
	 * @return LevelResponse
	 */
	LevelResponse getLevels();
	
	/**
	 * get level by levelId
	 * @param id
	 * @return Level object
	 */
	Level getById(Integer id);
}
