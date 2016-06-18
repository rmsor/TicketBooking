package com.mart.booking.dao;

import java.util.List;

import com.mart.booking.domain.Level;

public interface LevelDAO {
	
	 /**
	  * returs list of all levels of seats in venue
	  * @return List<Level>
	  */
	 public List<Level> list();
}
