package com.mart.booking.dao;

import java.util.List;

import com.mart.booking.entity.Level;

public interface LevelDAO {
	
	 /**
	  * returs list of all levels of seats in venue
	  * @return List<Level>
	  */
	 List<Level> list();
	 
	 /**
	  * 
	  * @param id
	  * @return Level
	  */
	 Level getById(Integer id);
}
