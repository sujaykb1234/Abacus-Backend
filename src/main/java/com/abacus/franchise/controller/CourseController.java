package com.abacus.franchise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abacus.franchise.model.Course;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.CourseService;

@RestController
@RequestMapping("abacus/v1/Course/")
public class CourseController {

	@Autowired
	CourseService courseService;

//Save The Course and Update the course  //DB
	@PostMapping("saveAndUpdateTheCourse")
	public SuccessResponse saveAndUpdateTheCourseInformation(@RequestBody Course course) {
		return courseService.saveAndUpdateTheCourseInformation(course);
	}

//-------------------------------------------------------------------------------------------------------------------------	
//Get All Course 	  //DB
	@GetMapping("getAllCoursess")
	public SuccessResponse getAllTheCoursess() {
		return courseService.getAllTheCoursess();
	}

//---------------------------------------------------------------------------------------------------------------------------	
//Get Course Using Id   // DB
	@GetMapping("getTheCourseUsingTheID/{id}")
	public SuccessResponse getTheCourseUsingTheID(@PathVariable Long id) {
		return courseService.getTheCourseUsingTheID(id);
	}

//-------------------------------------------------------------------------------------------------------------------------	
//Get The Course Using the Name	 //DB
	@GetMapping("getTheCourseUsingThecourseName/{name}")
	public SuccessResponse getTheCourseUsingTheName(@PathVariable String name) {
		return courseService.getTheCourseUsingTheName(name);
	}
//---------------------------------------------------------------------------------------------------------------------------	

//delete the course using the id 
	@DeleteMapping("deleteTheCourseUsingID/{id}")
	public SuccessResponse deleteTheCourseusingTheID(@PathVariable Long id) {
		return courseService.deleteTheCourseusingTheID(id);
	}
	@GetMapping("/getCoursesByFranchiseId/{franchiseId}")
	public SuccessResponse getcCourseByFranchise(@PathVariable Long franchiseId) {
		return courseService.getCoursesByFranchise(franchiseId);
	}
}
