package com.abacus.franchise.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.SliderImage;


@Repository
public interface SliderRepository extends JpaRepository<SliderImage, Long>{

	
}
