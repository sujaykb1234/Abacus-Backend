package com.abacus.franchise.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abacus.franchise.model.StudentEnrollment;
import com.abacus.franchise.response.SuccessResponse;

@Service
public interface EnrollmentService {

	public SuccessResponse getEnrolledCourses(Long studentId);

	public SuccessResponse markCourseAsCompleted(Long studentId, Long courseId);

	public SuccessResponse enrollInNewCourse(Long studentId, Long courseId);

	public List<StudentEnrollment> getCompletedAndInProgressCourses(Long studentId);

}
