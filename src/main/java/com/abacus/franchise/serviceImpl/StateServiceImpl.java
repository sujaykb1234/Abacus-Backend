package com.abacus.franchise.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abacus.franchise.model.District;
import com.abacus.franchise.model.State;
import com.abacus.franchise.repo.DistrictRepository;
import com.abacus.franchise.repo.StateRepository;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.StateService;

@Component
public class StateServiceImpl implements StateService {

	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private DistrictRepository districtRepository;

	@Override
	public SuccessResponse getAllState() {
		
		SuccessResponse response = new SuccessResponse();
		
		List<State> allState = stateRepository.getAllState();
		
		if(allState.isEmpty()) {
			response.stateNotFound();
			return response;
		}
		
		response.stateFound(allState);
		return response;
	}

	@Override
	public SuccessResponse getAllDistrictById(Long stateId) {
		SuccessResponse response = new SuccessResponse();
		
		 List<District> allDistrictByStateId = districtRepository.getAllDistrictByStateId(stateId);
		
		if(allDistrictByStateId.isEmpty()) {
			response.districtNotFound();
			return response;
		}
		
		response.districtFound(allDistrictByStateId);
		return response;
	}

	@Override
	public SuccessResponse getAllTehsilById(Long districtId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
