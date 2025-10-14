package com.abacus.franchise.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abacus.franchise.dto.AnswerDTO;
import com.abacus.franchise.dto.AssignExamDTO;
import com.abacus.franchise.dto.ExamTimeUpdateRequest;
import com.abacus.franchise.dto.ReattemptRequestDTO;
import com.abacus.franchise.dto.StudentExamResultDTO;
import com.abacus.franchise.response.SuccessResponse;

@Service
public interface ExamService {

	public SuccessResponse submitQuiz(Long examId, List<AnswerDTO> answers, Long studentId,String examStartTime, String examSubmissionTime);

	SuccessResponse getExamDetailsByStudent(Long studentId);

	public SuccessResponse getTheAllExam();

	public SuccessResponse getTheExamUsingTheCourseId(Long course_id);

	public SuccessResponse getTheExamUsingTheId(Long id);

//	public SuccessResponse examCreate(Long courseId, Long questionCount, String examTime, String examName);

	public SuccessResponse submitPracticeTest(Long studentId, Long courseId,String startTime, String submissionTime , List<AnswerDTO> answers);

//	public SuccessResponse assignExamToTheStudents(Long franchiseId, Long courseId, Long examId);

	public SuccessResponse getTheExamByCourseIdAndExamName(Long courseId, String ExamaName);

//	public SuccessResponse assignExam(Long courseId, Long questionCount, String examTime, String examName);

//	public SuccessResponse assignExamToTheStudents(Long examId);

	SuccessResponse assignExamToTheSelectedStudents(AssignExamDTO examDTO);

	public SuccessResponse getTheExamUsingTheFranchiseId(Long franchiseId);

	SuccessResponse examCreate(Long courseId, Long questionCount, String examTime, String examName,
			int percentageForImgQues);

	public SuccessResponse getStudentsByExam(Long examId,Long franchiseId);

	public StudentExamResultDTO getExamResult(Long studentId, Long examId);

	public SuccessResponse getExamAssignedStudents(Long examId, Long franchiseId);

//	public SuccessResponse setExamTimeToperticularStudents(Long examId, List<Long> studentIds);

	public SuccessResponse updateExamTime(Long franchiseId, ExamTimeUpdateRequest request);

	public SuccessResponse requestReattempt(ReattemptRequestDTO reattemptRequestDTO);

	public SuccessResponse approveOrRejectReattempt(ReattemptRequestDTO requestDTO);

	public SuccessResponse getAllOnnlineOfflineExams();
	
	public SuccessResponse getAllOnnlineOfflineExamsByFranchieseDate(Long franchiseId,String startDate,String endDate);
	
	SuccessResponse changeDawnloadStatus(Long id);

	public SuccessResponse getAllOflineExamStudentByFra(Long franchiseId,Long courseId);

	public SuccessResponse getAllOnlineExamStudentByFra(Long franchiseId);

	public SuccessResponse deleteExamById(Long id);



}
