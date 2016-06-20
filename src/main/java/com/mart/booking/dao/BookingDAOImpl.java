package com.mart.booking.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mart.booking.entity.BookingType;
import com.mart.booking.entity.Customer;
import com.mart.booking.entity.SeatHold;

@Repository
@Transactional
public class BookingDAOImpl implements BookingDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Value("#{sqlProperties['list.booking.getByBookingTypeNot']}")
	private String notExpiredBookingSql;
	
	@Value("#{sqlProperties['list.booking.availableSeats']}")
	private String availableSeats;
	
	
	@Override	
	public List<SeatHold> list() {
		@SuppressWarnings("unchecked")
		List<SeatHold> bookings = (List<SeatHold>) sessionFactory.getCurrentSession().createCriteria(Customer.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return bookings;
	}
	
	@Override
	public List<SeatHold> listActiveBooking(){
		Query query = sessionFactory.getCurrentSession().createSQLQuery(notExpiredBookingSql);
		query.setParameter("bookingType", BookingType.EXPIRED);
		@SuppressWarnings("unchecked")
		List<SeatHold> bookingList = query.list();
		return bookingList;
	}

	@Override
	public Integer add(SeatHold booking) {
		return (Integer)sessionFactory.getCurrentSession().save(booking);
	}
	
	@Override
	public SeatHold getById(Integer id){
		SeatHold booking =  (SeatHold)sessionFactory.getCurrentSession().get(SeatHold.class, id);
		return booking;
	}

	@Override
	public void update(SeatHold booking) {
		sessionFactory.getCurrentSession().update(booking);		
	}

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
