package com.abacus.franchise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.StateService;

@RestController
@RequestMapping("abacus/v1/")
public class StateController {

	@Autowired
	StateService stateService;
	
	
	@GetMapping("state/getAllState")
	public SuccessResponse getAllState() {
		return stateService.getAllState();
	}
	
	@GetMapping("district/getAllDistrictByState")
	public SuccessResponse getAllDistrictById(@RequestParam Long stateId) {
		return stateService.getAllDistrictById(stateId);
	}
	


	
}
