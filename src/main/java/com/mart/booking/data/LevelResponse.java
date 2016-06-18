package com.mart.booking.data;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mart.booking.domain.Level;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LevelResponse extends BaseResponse implements Serializable{
	
	private static final long serialVersionUID = -6448793809648359142L;
	
	@JsonProperty("levels")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<Level> levels;

	public List<Level> getLevels() {
		return levels;
	}

	public void setLevels(List<Level> levels) {
		this.levels = levels;
	}
	
	
}
