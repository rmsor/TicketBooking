package com.mart.booking.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mart.booking.entity.Level;

@Repository
@Transactional
public class LevelDAOImpl implements LevelDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override	
	public List<Level> list() {
		@SuppressWarnings("unchecked")
		List<Level> listUser = (List<Level>) sessionFactory.getCurrentSession().createCriteria(Level.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listUser;
	}
	
	@Override
	public Level getById(Integer id){
		 Level level =  (Level)sessionFactory.getCurrentSession().get(Level.class, id);
		 return level;
	}
}
