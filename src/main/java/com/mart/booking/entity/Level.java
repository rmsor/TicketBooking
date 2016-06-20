package com.mart.booking.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
public class Level  implements Serializable{
	
	private static final long serialVersionUID = 6535186084316245979L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer levelId;
	
	private String levelName;
	
	private Double price;
	
	private Integer noOfRows;
	
	private Integer seatsInRow;

	

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



	public Integer getNoOfRows() {
		return noOfRows;
	}



	public void setNoOfRows(Integer noOfRows) {
		this.noOfRows = noOfRows;
	}



	public Integer getSeatsInRow() {
		return seatsInRow;
	}



	public void setSeatsInRow(Integer seatsInRow) {
		this.seatsInRow = seatsInRow;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((levelId == null) ? 0 : levelId.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Level other = (Level) obj;
		if (levelId == null) {
			if (other.levelId != null)
				return false;
		} else if (!levelId.equals(other.levelId))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Level [levelId=" + levelId + ", levelName=" + levelName + ", price=" + price + ", noOfRows=" + noOfRows
				+ ", seatsInRow=" + seatsInRow + "]";
	}	
	
	
}
