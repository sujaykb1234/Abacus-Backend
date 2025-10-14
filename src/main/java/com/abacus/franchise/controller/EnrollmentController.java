package com.abacus.franchise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abacus.franchise.model.StudentEnrollment;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.EnrollmentService;

@RestController
@RequestMapping("/abacus/v1/enrollment")
public class EnrollmentController {

	@Autowired
	EnrollmentService enrollmentService;

	@GetMapping("/getStudentEnrollment/{studentId}")
	public SuccessResponse getStudentEnrollment(@PathVariable Long studentId) {
		System.out.println("dvdivdnvidnvkddnv");
		return enrollmentService.getEnrolledCourses(studentId);
	}

	@PostMapping("/mark-completed")
	public SuccessResponse markCourseAsCompleted(@RequestParam Long studentId, @RequestParam Long courseId) {
		return enrollmentService.markCourseAsCompleted(studentId, courseId);
	}

	@PostMapping("/enroll-new")
	public SuccessResponse enrollInNewCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
		return enrollmentService.enrollInNewCourse(studentId, courseId);
	}

	@GetMapping("/status")
	public ResponseEntity<List<StudentEnrollment>> getStudentCourseStatus(@RequestParam Long studentId) {
		List<StudentEnrollment> enrollments = enrollmentService.getCompletedAndInProgressCourses(studentId);
		return ResponseEntity.ok(enrollments);
	}

}
