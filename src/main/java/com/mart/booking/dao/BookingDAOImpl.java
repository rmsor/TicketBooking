package com.mart.booking.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mart.booking.entity.BookingType;
import com.mart.booking.entity.Customer;
import com.mart.booking.entity.SeatHold;

/**
 * repository for booking functions
 * @author rpathak
 *
 */
@Repository
@Transactional
public class BookingDAOImpl implements BookingDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Value("#{sqlProperties['list.booking.getByBookingTypeNot']}")
	private String notExpiredBookingSql;
	
	@Value("#{sqlProperties['list.booking.availableSeats']}")
	private String availableSeats;
	
	/**
	  * list all bookings 
	  * @return List<SeatHold> objects
	  */
	@Override	
	public List<SeatHold> list() {
		@SuppressWarnings("unchecked")
		List<SeatHold> bookings = (List<SeatHold>) sessionFactory.getCurrentSession().createCriteria(Customer.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return bookings;
	}
	
	/**
	  * list all booked and reserved bookings that are not expired 
	  * @return List<SeatHold> object
	  */
	@Override
	public List<SeatHold> listActiveBooking(){
		Query query = sessionFactory.getCurrentSession().createSQLQuery(notExpiredBookingSql);
		query.setParameter("bookingType", BookingType.EXPIRED);
		@SuppressWarnings("unchecked")
		List<SeatHold> bookingList = query.list();
		return bookingList;
	}
	
	
	/**
	 * get customer by email
	 * @param email Customer email
	 * @return Customer object
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SeatHold> getBookingByCustomer(Customer customer) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SeatHold.class);
	    criteria.add(Restrictions.eq("customer", customer));
		return (List<SeatHold>)criteria.list();
	}
	
	
	/**
	  * adds new booking 
	  * @param booking SeatHold object
	  * @return bookingId
	  */
	@Override
	public Integer add(SeatHold booking) {
		return (Integer)sessionFactory.getCurrentSession().save(booking);
	}
	
	/**
	  * search booking by ID
	  * @param id
	  * @return SeatHold Object
	  */
	@Override
	public SeatHold getById(Integer id){
		SeatHold booking =  (SeatHold)sessionFactory.getCurrentSession().get(SeatHold.class, id);
		return booking;
	}
	
	/**
	  * updates booking 
	  * @param booking object
	  */
	@Override
	public void update(SeatHold booking) {
		sessionFactory.getCurrentSession().update(booking);		
	}
	
	 /**
	  * return map of seatsBooked by level
	  * @return Map<Integer,Integer> bookedSeats
	  */
	@Override
	@SuppressWarnings("rawtypes")
	public Map<Integer, Integer> getSeatsBooked() {
		
		Map<Integer, Integer> seatsBookedMap=new HashMap<Integer, Integer>();
		
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(availableSeats);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List seatsBooked = query.list();
        for(Object object : seatsBooked)
        {
           Map row = (Map)object;
           seatsBookedMap.put((Integer)row.get("LEVEL"), (Integer)row.get("TOTAL"));
        }
		return seatsBookedMap;
	}
	
}
