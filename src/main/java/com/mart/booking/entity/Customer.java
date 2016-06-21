package com.mart.booking.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * entity class for CUSTOMER table
 * @author rpathak
 *
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "EMAIL") })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Customer implements Serializable{
	
	private static final long serialVersionUID = -1131169512745672238L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer customerId;
	
	private String email;
	
	private String phone;
	
	private String address;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
