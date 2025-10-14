package com.abacus.franchise.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abacus.franchise.dto.AnswerDTO;
import com.abacus.franchise.dto.AssignExamDTO;
import com.abacus.franchise.dto.ReattemptRequestDTO;
import com.abacus.franchise.dto.StudentExamResultDTO;
import com.abacus.franchise.model.StudentExam;
import com.abacus.franchise.repo.StudentExamRepository;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.ExamService;
import com.abacus.franchise.serviceImpl.CourseserviceImpl;

@RestController
@RequestMapping("abacus/v1/exam")
public class ExamController {

	@Autowired
	ExamService examService;

	@Autowired
	StudentExamRepository examRepository;

	@Autowired
	CourseserviceImpl courseserviceImpl;
//------------------------------------------------------------------------------------------------------------------------------

	@PostMapping("/submit/{examId}/{studentId}/{examStartTime}/{examSubmissionTime}") // SJ
	public SuccessResponse submitQuiz(@PathVariable Long examId, @RequestBody List<AnswerDTO> answers,
			@PathVariable Long studentId, @PathVariable String examStartTime, @PathVariable String examSubmissionTime) {
		System.out.println("exam start time : " + examStartTime);
		System.out.println("exam submission time : " + examSubmissionTime);

		return examService.submitQuiz(examId, answers, studentId, examStartTime, examSubmissionTime);
	}

	@GetMapping("/getAllStudentByExamAndFranchiseId")
	public SuccessResponse getStudentsByExamId(@RequestParam Long examId, @RequestParam Long franchiseId) {
		return examService.getStudentsByExam(examId, franchiseId);
	}
	
//------------------------------------------------------------------------------------------------------------------------------	
	@GetMapping("/student/{studentId}")
	public SuccessResponse getExamDetailsByStudentId(@PathVariable Long studentId) {
		return examService.getExamDetailsByStudent(studentId);
	}
//-------------------------------------------------------------------------------------------------------------------------------	

//get All Exam 
	@GetMapping("getAllExam")
	public SuccessResponse getTheAllExam() {
		return examService.getTheAllExam();
	}
//-----------------------------------------------------------------------------------------------------------------------------

//	get All Exam By course Id
	@GetMapping("getExamBycourseId/{course_id}")
	public SuccessResponse getTheExamUsingTheCourseId(@PathVariable Long course_id) {
		return examService.getTheExamUsingTheCourseId(course_id);
	}
//-----------------------------------------------------------------------------------------------------------------------------

//	get single exam using the Id 
	@GetMapping("getExamByTheID/{id}")
	public SuccessResponse getTheExamUsingTheId(@PathVariable Long id) {
		return examService.getTheExamUsingTheId(id);
	}

//-----------------------------------------------------------------------------------------------------------------------	

	@PostMapping("/create")
	public ResponseEntity<SuccessResponse> createExam(@RequestParam Long courseId, @RequestParam Long questionCount,
			@RequestParam String examTime, @RequestParam String examName, int imgQuestionPrecentage) {
		SuccessResponse response = examService.examCreate(courseId, questionCount, examTime, examName,
				imgQuestionPrecentage);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

//-------------------------------------------------------------------------------------------------------------------------	
	@PostMapping("/submitPracticeTest/{studentId}/{courseId}/{startTime}/{submissionTime}")
	public SuccessResponse submitPracticeTest(@PathVariable Long studentId, @PathVariable Long courseId,
			@PathVariable String startTime, @PathVariable String submissionTime, @RequestBody List<AnswerDTO> answers) {
		return examService.submitPracticeTest(studentId, courseId, startTime, submissionTime, answers);
	}

//------------------------------------------------------------------------------------------------------------------------------	

	@GetMapping("getTheExamByCourseIdAndExamName/{courseId}/{ExamaName}")
	public SuccessResponse getTheExamByCourseIdAndExamName(@PathVariable Long courseId,
			@PathVariable String ExamaName) {
		return examService.getTheExamByCourseIdAndExamName(courseId, ExamaName);
	}

//------------------------------------------------------------------------------------------------------------------------------	

	@PostMapping("/assign")
	public ResponseEntity<SuccessResponse> assignExamToStudents(@RequestBody AssignExamDTO examDTO) {
		SuccessResponse response = examService.assignExamToTheSelectedStudents(examDTO);
//		System.out.println("Exam id : " + examId);
//		for (Object data : studentIds) {
//			System.out.println("student ids  : " + data);
//		}
//		System.out.println("Exam type : " + examType);

		return ResponseEntity.ok(response);
	}
//--------------------------------------------------------------------------------------------------	

	// API to get the final exam result for a student
	@GetMapping("/final-exam-result/{studentId}/{examId}")
	public ResponseEntity<StudentExamResultDTO> getExamResult(@PathVariable Long studentId, @PathVariable Long examId) {
		StudentExamResultDTO result = examService.getExamResult(studentId, examId);
		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getAllExamAssignedStudentByExamId")
	public SuccessResponse getExamAssignedStudentByFranchise(@RequestParam Long examId,
			@RequestParam Long franchiseId) {
		return examService.getExamAssignedStudents(examId, franchiseId);
	}

//	@PostMapping("/updateExamTime/{franchiseId}")
//	public ResponseEntity<?> updateExamTime(@PathVariable Long franchiseId,
//			@RequestBody ExamTimeUpdateRequest request) {
//		try {
//			SuccessResponse response = examService.updateExamTime(franchiseId, request);
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(new SuccessResponse("Failed to update exam time.", null, null, null),
//					HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	@PostMapping("/request-reattempt")
	public SuccessResponse requestReattempt(@RequestBody ReattemptRequestDTO reattemptRequestDTO) {
		return examService.requestReattempt(reattemptRequestDTO);

	}

	@PostMapping("/request-approveOrReject")
	public SuccessResponse handleReattemptRequest(@RequestBody ReattemptRequestDTO requestDTO) {
		return examService.approveOrRejectReattempt(requestDTO);
	}

	@GetMapping("/getAllExamByOfflineOnline")
	public SuccessResponse getAllExamByOfflineOnline() {
		return examService.getAllOnnlineOfflineExams();
	}
	
	@GetMapping("/getAllExamByOfflineOnlineByFranchiseDate")
	public SuccessResponse getAllOnnlineOfflineExamsByFranchieseDate(@RequestParam(required = false) Long franchiseId,@RequestParam(required = false) String startDate,@RequestParam(required = false) String endDate
			) {
		return examService.getAllOnnlineOfflineExamsByFranchieseDate(franchiseId,startDate,endDate);
	}


	
	@GetMapping("/setExamDawnloadStatus/{id}")
	public SuccessResponse setOfflineExamDawnloadStatus(@PathVariable Long id) {
		return examService.changeDawnloadStatus(id);
	}

	@PostMapping("/setOfflineExamDawnloadStatus")
	public SuccessResponse changeDawnloadStatus(@RequestBody StudentExam studentExam) {
		SuccessResponse response = new SuccessResponse();
		Optional<StudentExam> offlineExams = examRepository.findById(studentExam.getId());
		if (offlineExams.isEmpty()) {
			response.ExamNotFound();
			return response;
		}
		StudentExam offlineExam = offlineExams.get();
		offlineExam.setMark_as_download(false);

		examRepository.save(offlineExam);
		response.saveExam(offlineExam);
		return response;
	}

	@GetMapping("/getAllOflineExamAttemptedStudentByFranchise/{franchiseId}/{courseId}")
	public SuccessResponse getAllOflineExamStudentByFranchise(@PathVariable Long franchiseId,
			@PathVariable Long courseId) {
		return examService.getAllOflineExamStudentByFra(franchiseId, courseId);
	}

	@GetMapping("/getAllOnlineExamAttemptedStudentByFranchise/{franchiseId}")
	public SuccessResponse getAllOnlineExamStudentByFranchise(@PathVariable Long franchiseId) {
		return examService.getAllOnlineExamStudentByFra(franchiseId);
	}

	@DeleteMapping("DeleteExamByID")
	public SuccessResponse deleteExamById(@RequestParam Long id) {
		return examService.deleteExamById(id);
	}

}
