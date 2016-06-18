package com.mart.booking.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Level {
	
	@Id
	private int levelId;
	
	private String levelName;
	
	private double price;
	
	private long noOfRows;
	
	private long seatsInRow;

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getNoOfRows() {
		return noOfRows;
	}

	public void setNoOfRows(long noOfRows) {
		this.noOfRows = noOfRows;
	}

	public long getSeatsInRow() {
		return seatsInRow;
	}

	public void setSeatsInRow(long seatsInRow) {
		this.seatsInRow = seatsInRow;
	}

	@Override
	public String toString() {
		return "Level [levelId=" + levelId + ", levelName=" + levelName + ", price=" + price + ", noOfRows=" + noOfRows
				+ ", seatsInRow=" + seatsInRow + "]";
	}	
	
	
}
