package com.mart.booking.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mart.booking.entity.Customer;
import com.mart.booking.entity.Level;

/**
 * repository class for customer
 * @author rpathak
 *
 */
@Repository
@Transactional
public class CustomerDAOImpl implements CustomerDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * returns list of all customers
	 * @return List<Customer>
	 */
	@Override	
	public List<Customer> list() {
		@SuppressWarnings("unchecked")
		List<Customer> listCustomer = (List<Customer>) sessionFactory.getCurrentSession().createCriteria(Customer.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listCustomer;
	}
	
	/**
	 * get Customer by ID
	 * @param id
	 * @return Customer
	 */
	@Override
	public Customer getById(Integer id){
		Customer customer =  (Customer)sessionFactory.getCurrentSession().get(Level.class, id);
		 return customer;
	}

	/**
	 * get customer by email
	 * @param email Customer email
	 * @return Customer object
	 */
	@Override
	public Customer getByEmail(String email) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
	    criteria.add(Restrictions.eq("email", email));
		return (Customer)criteria.uniqueResult();
	}
	
	/**
	 * adds new Customer
	 * @param Customer object
	 * @return customerId
	 */
	@Override
	public Long add(Customer cust) {
		return (Long)sessionFactory.getCurrentSession().save(cust);
	}

}
