package com.abacus.franchise.service;

import org.springframework.stereotype.Service;

import com.abacus.franchise.response.SuccessResponse;

@Service
public interface StateService {

	public SuccessResponse getAllState();
	
	public SuccessResponse getAllDistrictById(Long stateId);
	
	public SuccessResponse getAllTehsilById(Long districtId);
}
