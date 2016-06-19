package com.mart.booking.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mart.booking.domain.Level;

@Repository
public class LevelDAOImpl implements LevelDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
//	@Value("#{sqlProperties[list.level.query]}")
//	String listSql;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<Level> list() {
		@SuppressWarnings("unchecked")
		List<Level> listUser = (List<Level>) sessionFactory.getCurrentSession().createCriteria(Level.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listUser;
	}
}
