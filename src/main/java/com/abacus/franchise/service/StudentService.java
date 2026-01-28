package com.abacus.franchise.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abacus.franchise.exception.DataNotValidException;
import com.abacus.franchise.model.PracticeStudent;
import com.abacus.franchise.model.Student;
import com.abacus.franchise.response.SuccessResponse;

import jakarta.servlet.http.HttpServletRequest;

@Service
public interface StudentService {

	public SuccessResponse registerAndUpdateTheStudent(Student student, MultipartFile studentPhoto,HttpServletRequest request)
			throws DataNotValidException, IOException;// MultipartFile studentDocPhoto

	public SuccessResponse loginTheStudent(Student student);

	public SuccessResponse saveTheNewPasswordFotTheStudent(Student student);

	public SuccessResponse getTheAllStudent(int pageNo, int pageSize, String sortBy, String orderBy);

	public SuccessResponse getTheStudentUsingTheID(Long id);

//	public SuccessResponse getTheStudentUsingTheName(String name);
	public SuccessResponse getTheStudentUsingTheName(String name, Long franhce_id);

	public SuccessResponse getStudentsByFranchiseId(Long franchise_id, int pageNo, int pageSize, String sortBy,
			String orderBy);

	public SuccessResponse deleteTheStudentUsingTheId(Long id);

	public SuccessResponse getTheStudentUsingTheCourseID(Long id);

	public SuccessResponse getPractiseExams(Long studentId, Long corseId);

	public SuccessResponse getFinalExamForStudent(Long studentId, Long courseId);

	public SuccessResponse getAllStudentCount();

	public SuccessResponse getTheStudentUsingByFranchiesIdAndCourseId(Long franchies_id, Long course_id);

	public SuccessResponse assignTheAnotherCourseToStudent(Long courseId, Long student_id);

	SuccessResponse getFinalExamForStudentInOnline(Long studentId, Long courseId); // for online student

	public SuccessResponse switchCourse(Long newCourseId, Long studentId);

	public SuccessResponse registerAndUpdateTheStudentDemo(PracticeStudent student, MultipartFile studentPhoto,HttpServletRequest request)
			throws DataNotValidException, IOException;

	public SuccessResponse getDemoStudentById(Long studentId);;

}
