package com.abacus.franchise.service;

import org.springframework.stereotype.Service;

import com.abacus.franchise.model.Course;
import com.abacus.franchise.response.SuccessResponse;

@Service
public interface CourseService {

	public SuccessResponse saveAndUpdateTheCourseInformation(Course course);

	public SuccessResponse getAllTheCoursess();

	public SuccessResponse getTheCourseUsingTheID(Long id);

	public SuccessResponse getTheCourseUsingTheName(String name);

	public SuccessResponse deleteTheCourseusingTheID(Long id);

	public SuccessResponse getCoursesByFranchise(Long franchiseId);

}
