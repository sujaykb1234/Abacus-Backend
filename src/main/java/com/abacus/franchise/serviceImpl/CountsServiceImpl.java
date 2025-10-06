package com.abacus.franchise.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abacus.franchise.model.Counts;
import com.abacus.franchise.repo.CountsRepo;

import java.util.Optional;

@Service
public class CountsServiceImpl {
	
	@Autowired
	CountsRepo countsRepo;
	
	public Counts getImgCount(Integer recordId) {

		Long temp=(long) recordId;
		Optional<Counts> findById = countsRepo.findById(temp);
		Counts counts = findById.get();
		
		return counts;
	}
	
	public Counts saveImgCount(Counts counts) {

		Counts save = countsRepo.save(counts);
		return save;
	}

}
