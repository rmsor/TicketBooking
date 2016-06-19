package com.mart.booking.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Level {
	
	@Id
	private Integer levelId;
	
	private String levelName;
	
	private Double price;
	
	private Long noOfRows;
	
	private Long seatsInRow;

	

	public Integer getLevelId() {
		return levelId;
	}



	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}



	public String getLevelName() {
		return levelName;
	}



	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}



	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public Long getNoOfRows() {
		return noOfRows;
	}



	public void setNoOfRows(Long noOfRows) {
		this.noOfRows = noOfRows;
	}



	public Long getSeatsInRow() {
		return seatsInRow;
	}



	public void setSeatsInRow(Long seatsInRow) {
		this.seatsInRow = seatsInRow;
	}



	@Override
	public String toString() {
		return "Level [levelId=" + levelId + ", levelName=" + levelName + ", price=" + price + ", noOfRows=" + noOfRows
				+ ", seatsInRow=" + seatsInRow + "]";
	}	
	
	
}
