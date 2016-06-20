package com.mart.booking.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mart.booking.entity.Level;

/**
 * repository for Levels
 * @author rpathak
 *
 */
@Repository
@Transactional
public class LevelDAOImpl implements LevelDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	 /**
	  * returs list of all levels of seats in venue
	  * @return List<Level>
	  */
	@Override	
	public List<Level> list() {
		@SuppressWarnings("unchecked")
		List<Level> listUser = (List<Level>) sessionFactory.getCurrentSession().createCriteria(Level.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listUser;
	}
	
	/**
	  * 
	  * @param id
	  * @return Level
	  */
	@Override
	public Level getById(Integer id){
		 Level level =  (Level)sessionFactory.getCurrentSession().get(Level.class, id);
		 return level;
	}
}
