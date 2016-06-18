package com.mart.booking.resource;


import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mart.booking.data.BaseResponse;
import com.mart.booking.data.LevelResponse;
import com.mart.booking.service.LevelService;

@RestController()
@RequestMapping(value="rest/level")
public class LevelResource extends BaseResponse implements Serializable{
	
	
	private static final long serialVersionUID = 258612336447960285L;
	
	
	@Autowired
	LevelService levelService;
	
	@RequestMapping(value="/list/v1_0",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LevelResponse> getAllLevels(){
		LevelResponse levelResponse=levelService.getLevels();
		return new ResponseEntity<LevelResponse>(levelResponse, HttpStatus.OK);
	}
}
