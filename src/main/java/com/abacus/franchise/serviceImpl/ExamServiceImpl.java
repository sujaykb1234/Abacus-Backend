package com.abacus.franchise.serviceImpl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.abacus.franchise.dto.AnswerDTO;
import com.abacus.franchise.dto.AssignExamDTO;
import com.abacus.franchise.dto.ExamDTO;
import com.abacus.franchise.dto.ExamResponse;
import com.abacus.franchise.dto.ExamTimeUpdateRequest;
import com.abacus.franchise.dto.OfflineOnlineExamDTO;
import com.abacus.franchise.dto.QuestionsDTO;
import com.abacus.franchise.dto.ReattemptRequestDTO;
import com.abacus.franchise.dto.StudentExamDTO;
import com.abacus.franchise.dto.StudentExamResponseDTO;
import com.abacus.franchise.dto.StudentExamResultDTO;
import com.abacus.franchise.model.Answers;
import com.abacus.franchise.model.Course;
import com.abacus.franchise.model.ExamAttempt;
import com.abacus.franchise.model.Franchise;
import com.abacus.franchise.model.Messages;
import com.abacus.franchise.model.Questions;
import com.abacus.franchise.model.Student;
import com.abacus.franchise.model.StudentExam;
import com.abacus.franchise.model.StudentPracticeTests;
import com.abacus.franchise.repo.AnswerRepository;
import com.abacus.franchise.repo.CourseRepo;
import com.abacus.franchise.repo.ExamAttemptRepository;
import com.abacus.franchise.repo.FranchiseRepo;
import com.abacus.franchise.repo.QuestionsRepo;
import com.abacus.franchise.repo.StudentExamRepository;
import com.abacus.franchise.repo.StudentPracticeTestsRepo;
import com.abacus.franchise.repo.StudentRepo;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.ExamService;
import com.abacus.franchise.service.QuestionsService;
import com.abacus.franchise.service.StudentService;
import com.abacus.franchise.utility.ExamStatus;
import com.abacus.franchise.utility.ReattemptStatus;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	ExamAttemptRepository examAttemptRepository;

	@Autowired
	QuestionsRepo questionRepository;

	@Autowired
	StudentRepo studentRepo;

	@Autowired
	CourseRepo courseRepo;

	@Autowired
	StudentPracticeTestsRepo studentPracticeTestsRepo;

	@Autowired
	QuestionsService questionsService;

	@Autowired
	AnswerRepository answerRepository;
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	FranchiseRepo franchiseRepo;

	@Autowired
	StudentExamRepository examRepository;

	@Autowired
	StudentService studentService;

	@Autowired
	ExamAttemptRepository examAttemptRepository2;

	@Autowired
	StudentExamRepository studentExamRepository;

	SuccessResponse response = new SuccessResponse();

	private StudentPracticeTests save;

	@Override
	public SuccessResponse submitQuiz(Long examId, List<AnswerDTO> answers, Long studentId, String examStartTime,
			String examSubmissionTime) {
		System.out.println("Exam ID : " + examId);
		System.out.println("Student ID : " + studentId);
		SuccessResponse response = new SuccessResponse();
		Optional<Student> studentOpt = studentRepo.findById(studentId);
		if (studentOpt.isEmpty()) {
			response.setStatusCode(HttpStatus.NOT_FOUND);
			response.setMessage(Messages.student_not_found);
			return response;
		}
		Student student = studentOpt.get();
		List<StudentExam> examByExamAttemptIdAndStudentId = studentExamRepository
				.findExamByExamAttemptIdAndStudentId(examId, studentId);
		Optional<StudentExam> studentExamAttemptOpt = examByExamAttemptIdAndStudentId.stream()
				.filter(examAttempt -> examAttempt.getStudent().getStudent_id().equals(studentId)).findFirst();
		if (studentExamAttemptOpt.isEmpty()) {
			response.setStatusCode(HttpStatus.FORBIDDEN);
			response.setMessage(Messages.course_not_assign_to_this_students);
			return response;
		}
		StudentExam studentExamAttempt = studentExamAttemptOpt.get();
		int correctAnswers = 0;
		int totalQuestions = studentExamAttempt.getQuestion_count();
		int attendedQuestions = 0;
		for (AnswerDTO answerDTO : answers) {
			Optional<Questions> questionOpt = questionRepository.findById(answerDTO.getQuestionId());
			if (questionOpt.isPresent()) {
				Questions question = questionOpt.get();

				if (question.getCourse().getCourse_id().equals(studentExamAttempt.getCourse_id())) {
					Answers answer = new Answers();
					answer.setQuestion(question);
					answer.setCorrectOption(question.getCorrect_answer());
					answer.setChosenOption(answerDTO.getChosenOption());
					answer.setStudent(student);
					answer.setQuestionType(question.getQuestion_type());
					answer.setCourse(question.getCourse());
					answer.setAttempt(studentExamAttempt.getExamAttempt());

					answerRepository.save(answer);

					if (question.getCorrect_answer().equals(answerDTO.getChosenOption())) {
						correctAnswers++;
					}

					if (answerDTO.getChosenOption() != null) {
						attendedQuestions++;
					}
				}
			}
		}
		studentExamAttempt.setMarks(correctAnswers);
		studentExamAttempt.setExamstatus(ExamStatus.COMPLETED);
		studentExamAttempt.setExamStartTime(examStartTime);
		studentExamAttempt.setExamSubmissionTime(examSubmissionTime);
		student.setExamStatus(ExamStatus.COMPLETED);
		studentExamAttempt.setCompletedAt(Timestamp.from(Instant.now()));
		studentRepo.save(student);
		studentExamRepository.save(studentExamAttempt);
		response.setStatusCode(HttpStatus.OK);
		response.setStatus(true);
		response.setMessage(Messages.exam_submit);
		response.setData(Map.of("totalQuestions", totalQuestions, "attendedQuestions", attendedQuestions, "marks",
				correctAnswers, "studentId", studentId));
		return response;
	}

	@Override
	public SuccessResponse getExamDetailsByStudent(Long studentId) {
		SuccessResponse response = new SuccessResponse();
		Optional<Student> studentOpt = studentRepo.findById(studentId);
		if (studentOpt.isEmpty()) {
			response.setStatusCode(HttpStatus.NOT_FOUND);
			response.setMessage("Student not found.");
			return response;
		}
		List<ExamAttempt> examAttempts = examAttemptRepository.findByStudent_StudentId(studentId);
		if (examAttempts.isEmpty()) {
			response.setStatusCode(HttpStatus.NOT_FOUND);
			response.setMessage("No exams found for the student.");
			return response;
		}
		List<ExamResponse> questionResponses = new ArrayList<>();
		for (ExamAttempt examAttempt : examAttempts) {
			for (Long questionId : examAttempt.getQuestionIds()) {
				Optional<Questions> questionOpt = questionRepository.findById(questionId);
				if (questionOpt.isPresent()) {
					Questions question = questionOpt.get();
					ExamResponse examResponse = new ExamResponse();
					examResponse.setId(question.getQue_id());
					examResponse.setStudentId(studentId);
					if (question.getIsImageQuestion()) {
						examResponse.setQuestionName(question.getQuestion_link());
					} else {
						examResponse.setQuestionName(question.getQue());
					}
					examResponse.setOptions(question.getOptions());
					examResponse.setCorrectOptions(question.getCorrect_answer());
					examResponse.setCourseId(question.getCourse().getCourse_id().toString());

					questionResponses.add(examResponse);
				}
			}
		}
		response.setStatusCode(HttpStatus.OK);
		response.setStatus(true);
		response.setMessage("Exam details retrieved successfully.");
		response.setData(questionResponses);
		return response;
	}

	@Override
	public SuccessResponse getTheAllExam() {

		SuccessResponse response = new SuccessResponse();

		List<ExamAttempt> all = examAttemptRepository.findAll();
		response.getAllExam(all);
		return response;
	}


	@Override
	public SuccessResponse getTheExamUsingTheCourseId(Long course_id) {
	    SuccessResponse response = new SuccessResponse();

	    if (course_id == null) {
	        response.idisNull();
	        return response;
	    }

	    // Find exams for the course
	    List<ExamAttempt> exams = examAttemptRepository.findByCourseId(course_id);
	    if (exams == null || exams.isEmpty()) {
	        response.noExamsFound();
	        return response;
	    }

	    List<ExamDTO> examDTOs = new ArrayList<>();
	    for (ExamAttempt exam : exams) {
	        ExamDTO examDto = new ExamDTO();

	        examDto.setId(exam.getAttemptId());
	        examDto.setCourseName(exam.getCourse().getCourse_name());
	        examDto.setExamName(exam.getExamName());
	        examDto.setExamTime(exam.getExamTime());
	        examDto.setCourse_id(course_id);
	        examDto.setCourse_name(exam.getCourse_name());
	        examDto.setImageQuePercentage(exam.getImageQuePercentage());
	        examDto.setQuestion_count(exam.getQuestion_count());

	        // Retrieve all questions for the course
	        List<Questions> courseQuestions = questionRepository.findByCourse_Id(course_id);
	        if (courseQuestions.isEmpty()) {
	            response.noQuestionsFoundForCourse(course_id);
	            return response;
	        }

	        // Shuffle questions in memory for random order
//	        Collections.shuffle(courseQuestions); // Randomize order in response
	        int questionCount = exam.getQuestion_count();

	        // Limit questions to the exam's question count
	        List<Questions> limitedQuestions = courseQuestions.stream()
	                .limit(questionCount)
	                .collect(Collectors.toList());

	        // Map to QuestionsDTO
	        List<QuestionsDTO> questionDetails = limitedQuestions.stream()
	                .map(this::mapToQuestionDTO)
	                .collect(Collectors.toList());

	        examDto.setQuestions(questionDetails);
	        examDTOs.add(examDto);
	    }

	    response.getSingleExamUsingCourseID(examDTOs);
	    return response;
	}
	
	private QuestionsDTO mapToQuestionDTO(Questions question) {
		QuestionsDTO dto = new QuestionsDTO();
		dto.setQue(question.getQue());
		dto.setQue_id(question.getQue_id());
		dto.setQuestion_name(question.getQuestion_name());
		dto.setQuestion_type(question.getQuestion_type());
		dto.setOptions(question.getOptions());
		dto.setCorrect_answer(question.getCorrect_answer());
		return dto;
	}
 
//-------------------------------------------------------------------------------------------	
	@Override
	public SuccessResponse getTheExamUsingTheId(Long id) {

		SuccessResponse response = new SuccessResponse();

		if (id == null) {
			response.idisNull();
			return response;
		}

		Optional<ExamAttempt> getByid = examAttemptRepository.findById(id);
		if (getByid.isPresent()) {
			ExamAttempt exam = getByid.get();
			response.getTheSingleExam(exam);
			return response;
		} else {
			response.noExamsFound();
			return response;
		}
	}

	@Override
	public SuccessResponse examCreate(Long courseId, Long questionCount, String examTime, String examName,
			int percentageForImgQues) {
		SuccessResponse response = new SuccessResponse();

		// Find the course by courseId
		Course course = courseRepo.findById(courseId).orElse(null);
		if (course == null) {
			response.courseNotFound(courseId);
			return response;
		}

		// Retrieve all questions linked to the course
		List<Questions> courseQuestions = questionRepository.findByCourse_Id(courseId);
		int availableQuestionsCount = courseQuestions.size();

		if (availableQuestionsCount < questionCount) {
			response.setStatusCode(HttpStatus.BAD_REQUEST);
			response.setMessage(
					"Insufficient questions available for the course. Available questions: " + availableQuestionsCount);
			response.setData(Map.of("availableQuestions", availableQuestionsCount));
			return response;
		}
		List<Long> franchises = franchiseRepo.findByCourseId(courseId);
		if (franchises.isEmpty()) {
			response.noFranchisesFoundForCourse(courseId);
			return response;
		}
		ExamAttempt exam = new ExamAttempt();
		exam.setCourse(course);
		exam.setCourse_name(course.getCourse_name());
		exam.setImageQuePercentage(percentageForImgQues);
		exam.setExamName(examName);
		exam.setExamTime(examTime);
		exam.setQuestion_count(questionCount.intValue());
		exam = examAttemptRepository.save(exam);
		
		Map<Long, Long> franchiseExamMap = new HashMap<>();
		for (Long franchiseId : franchises) {
			Optional<Franchise> franchiseOpt = franchiseRepo.findById(franchiseId);
			if (franchiseOpt.isPresent()) {
				Franchise franchise = franchiseOpt.get();
				franchise.getExamAttempts().add(exam);
				franchiseRepo.save(franchise);
				franchiseExamMap.put(franchise.getFranchise_id(), exam.getAttemptId());
			}
		}
		if (franchiseExamMap.isEmpty()) {
			response.noExamsCreatedForCourse(courseId);
			return response;
		}

		response.saveExamForFranchises(franchiseExamMap);
		return response;
	}

//---------------------------------------------------------------------------------------------------------------------------	
	@Override
	public SuccessResponse getTheExamByCourseIdAndExamName(Long courseId, String ExamaName) {

		SuccessResponse response = new SuccessResponse();

		if (courseId == null || ExamaName == null) {
			response.nullData();
			return response;
		}

		Optional<ExamAttempt> exam = examAttemptRepository.findUsingTheCouserseIdAndExamName(courseId, ExamaName);
		if (exam.isPresent()) {

			ExamAttempt examDetails = exam.get();
			response.getTheSingleExam(examDetails);
			return response;
		} else {
			response.ExamNotFound();
			return response;
		}
	}


	@Override
	public SuccessResponse assignExamToTheSelectedStudents(AssignExamDTO examDTO ) {
		SuccessResponse response = new SuccessResponse();
		ExamAttempt exam = examAttemptRepository.findById(examDTO.getExamId()).orElse(null);
		if (exam == null) {
			response.notfound();
			return response;
		}
		Long courseId = exam.getCourse().getCourse_id();
		int questionCount = exam.getQuestion_count();
		String examName = exam.getExamName();
		System.out.println("Course id " + courseId);
		SuccessResponse questionResponse = questionsService.getRandomQuestions(courseId, questionCount,
				exam.getImageQuePercentage());
		if (!questionResponse.getStatus()) {
			response.questionsNotFound();
			return response;
		}
		List<Long> questionIds = new ArrayList<>();
		System.out.println("question response : " + questionResponse);
		@SuppressWarnings("unchecked")
		List<QuestionsDTO> questions = (List<QuestionsDTO>) questionResponse.getResponse();
		for (QuestionsDTO question : questions) {
			questionIds.add(question.getQue_id());
		}
		List<Student> students = studentRepo.findStudentsByIds(examDTO.getStudentIds());
		List<Long> notFoundIds = new ArrayList<>();
		List<Long> notEnrolledInCourseIds = new ArrayList<>();
		List<Long> alreadyAssignedIds = new ArrayList<>();
		List<Long> successfullyAssignedIds = new ArrayList<>();
		for (Long studentId : examDTO.getStudentIds()) {
			Optional<Student> studentOpt = students.stream().filter(s -> s.getStudent_id().equals(studentId))
					.findFirst();
			if (studentOpt.isEmpty()) {
				notFoundIds.add(studentId);
				continue;
			}
			Student student = studentOpt.get();
			boolean isEnrolledInCourse = student.getCourses().stream()
					.anyMatch(course -> course.getCourse_id().equals(courseId));
			if (!isEnrolledInCourse) {
				notEnrolledInCourseIds.add(studentId);
				continue;
			}
			
			StudentExam examAttempt = new StudentExam();

			
			 Optional<StudentExam> existsByStudentId = studentExamRepository.existsByStudentIdAndExamAttemptId(student.getStudent_id(),examDTO.getExamId());
			if (existsByStudentId.isPresent()) {
				alreadyAssignedIds.add(student.getStudent_id());
				examAttempt = existsByStudentId.get();
			}
			student.setExamStatus(ExamStatus.ASSIGNED);
			student.setCurrentExamId(examDTO.getExamId());
			student.setExamType(examDTO.getExamType());
			
			
			
			examAttempt.setExamTime(examDTO.getExamTime());
			examAttempt.setCourse_id(courseId);
			examAttempt.setCourse_name(exam.getCourse_name());
			examAttempt.setExamName(examName);
			examAttempt.setQuestionIds(questionIds);
			examAttempt.setQuestion_count(questionCount);
			examAttempt.setStudent(student);
			examAttempt.setFranchiseId(examDTO.getFranchiseId());
			examAttempt.setExamstatus(ExamStatus.ASSIGNED);
			examAttempt.setExamType(examDTO.getExamType());
			Collections.shuffle(questionIds);
			examAttempt.setExamAttempt(exam);
			examAttempt.setAssignedAt(Timestamp.from(Instant.now()));
			studentExamRepository.save(examAttempt);
			successfullyAssignedIds.add(student.getStudent_id());
		}
		StringBuilder message = new StringBuilder("Exams assigned to:").append(successfullyAssignedIds);
		if (!notFoundIds.isEmpty()) {
			message.append(". The following student IDs not found:").append(notFoundIds);
		}
		if (!notEnrolledInCourseIds.isEmpty()) {
			message.append(". The students are not enrolled in the exam's course:").append(notEnrolledInCourseIds);
		}
		if (!alreadyAssignedIds.isEmpty()) {
			message.append(". The following students were Updated in this exam:").append(alreadyAssignedIds);
		}
		response.setMessage(message.toString());
		response.setStatusCode(HttpStatus.OK);
		response.setStatus(true);
		return response;

	}

//------------------------------------------------------------------------------------------	
	@Override
	public SuccessResponse submitPracticeTest(Long studentId, Long courseId, String startTime, String submissionTime,
			List<AnswerDTO> answers) {
		SuccessResponse response = new SuccessResponse();
		Optional<Student> studentOpt = studentRepo.findById(studentId);
		if (studentOpt.isEmpty()) {
			response.setStatusCode(HttpStatus.NOT_FOUND);
			response.setMessage("Student not found.");
			return response;
		}
		Optional<Course> findById = courseRepo.findById(courseId);
		if (!findById.isPresent()) {
			response.courseNotFound();
			return response;
		}
		studentOpt.get();
		int correctAnswers = 0;
		int totalQuestions = answers.size();

		for (AnswerDTO answerDTO : answers) {
			Optional<Questions> questionOpt = questionRepository.findById(answerDTO.getQuestionId());
			if (questionOpt.isPresent()) {
				Questions question = questionOpt.get();

				// Compare student's answer with the correct answer
				if (question.getCorrect_answer().equals(answerDTO.getChosenOption())) {
					correctAnswers++;
				}
			}
		}
		// Calculate score (percentage of correct answers)
		int marks = correctAnswers;
		System.out.println("total practice marks : " + marks);

		// Save the result in the StudentPracticeTests table
		StudentPracticeTests practiceTest = new StudentPracticeTests();
		int testCount = studentPracticeTestsRepo.countByStudentId(studentId) + 1;
		practiceTest.setStudentId(studentId);
		practiceTest.setStartTime(startTime);
		practiceTest.setSubmissionTime(submissionTime);
		practiceTest.setExamName("PRAC-TEST00" + testCount);
		practiceTest.setCourseId(courseId);
		practiceTest.setTotalQuestion(totalQuestions);
		practiceTest.setScore((double) marks);

		save = studentPracticeTestsRepo.save(practiceTest);

		String examName = save.getExamName();

		// Set response with the score details
		response.setStatusCode(HttpStatus.OK);
		response.setMessage("Practice test submitted successfully.");
		response.setResponse(Map.of("correctAnswers", correctAnswers, "totalQuestions", totalQuestions, "score", marks,
				"ExamName", examName));
		return response;
	}

	@Override
	public SuccessResponse getTheExamUsingTheFranchiseId(Long franchiseId) {
		SuccessResponse response = new SuccessResponse();

		if (franchiseId == null) {
			response.idisNull();
			return response;
		}
		List<ExamAttempt> exams = examAttemptRepository.findByFranchiseId(franchiseId);
		if (exams != null && !exams.isEmpty()) {
			List<ExamDTO> examDTOs = new ArrayList<>();
			for (ExamAttempt exam : exams) {
				ExamDTO examDto = modelMapper.map(exam, ExamDTO.class);
				if (exam.getCourse() != null) {
					examDto.setCourseName(exam.getCourse().getCourse_name());
					examDto.setExamName(exam.getExamName());
					examDto.setExamTime(exam.getExamTime());
					examDto.setCourse_id(exam.getCourse().getCourse_id());
					examDto.setCourse_name(exam.getCourse().getCourse_name());
				}

				examDTOs.add(examDto);
			}
			response.getSingleExamUsingCourseID(examDTOs);
			return response;
		} else {
			response.noExamsFound();
			return response;
		}
	}

	@Override
	public StudentExamResultDTO getExamResult(Long studentId, Long examId) {
		Optional<StudentExam> studentExamOpt = studentExamRepository
				.findExamByExamAttemptIdAndStudentId(examId, studentId).stream().findFirst();
		if (studentExamOpt.isEmpty()) {
			return null;
		}
		StudentExam studentExam = studentExamOpt.get();
		int totalQuestions = studentExam.getQuestion_count();
		List<Answers> studentAnswers = answerRepository.findAllByStudentIdAndExamId(studentId, examId, totalQuestions);
		int correctAnswers = 0;
		List<QuestionsDTO> questionDTOs = new ArrayList<>();
		for (Answers answer : studentAnswers) {
			Questions question = answer.getQuestion();
			QuestionsDTO questionDTO = new QuestionsDTO();
			questionDTO.setQue_id(question.getQue_id());
			questionDTO.setQue(question.getQue());
			questionDTO.setOptions(question.getOptions());
			questionDTO.setCorrect_answer(question.getCorrect_answer());
			questionDTO.setChosenOption(answer.getChosenOption());
			questionDTO.setIsImageQuestion(question.getQuestion_type().equals("IMAGE"));
			questionDTO.setQuestion_type(question.getQuestion_type());
			if (question.getQuestion_type().equals("IMAGE")) {
				questionDTO.setQuestion_link(question.getQuestion_link());
			} else {
				questionDTO.setQuestion_link(null);
			}
			questionDTOs.add(questionDTO);

			if (answer.getChosenOption().equals(question.getCorrect_answer())) {
				correctAnswers++;
			}
		}
		StudentExamResultDTO result = new StudentExamResultDTO();
		result.setId(studentExam.getId());
		result.setStudentName(studentExam.getStudent().getFirst_name());
		result.setExamName(studentExam.getExamAttempt().getExamName());
		result.setExamId(examId);
		result.setTestTime(studentExam.getExamTime());
		result.setCourseName(studentExam.getCourse_name());
		result.setExamStartTime(studentExam.getExamStartTime());
		result.setExamSubmissionTime(studentExam.getExamSubmissionTime());
		result.setTotalQuestions(totalQuestions);
		result.setAttemptedQuestion(studentAnswers.size());
		result.setTotalCorrectAnswers(correctAnswers);
		result.setQuestions(questionDTOs);
		result.setMarks(correctAnswers);
		result.setCourseId(studentExam.getCourse_id());
		result.setStudentId(studentId);
		return result;
	}

	@Override
	public SuccessResponse getExamAssignedStudents(Long examId, Long franchiseId) {
		SuccessResponse response = new SuccessResponse();
		if (examId == null || franchiseId == null) {
			response.nullData();
			return response;
		}
		List<StudentExam> findByExamAndFranchiseId = studentExamRepository.findByExamAndFranchiseId(examId,
				franchiseId);
		if (findByExamAndFranchiseId.isEmpty()) {
			response.ExamNotFound();
			return response;
		}
		response.getallStudentExams(findByExamAndFranchiseId);
		return response;
	}


	@Override
	public SuccessResponse updateExamTime(Long franchiseId, ExamTimeUpdateRequest request) {
		List<Long> studentIds = request.getStudentIds();
		Long examId = request.getExamId();
		String newExamTime = request.getNewTimeInMinutes();
		List<StudentExam> studentExams = studentExamRepository.findByStudentIdsAndExamId(studentIds, examId);
		List<Long> foundStudentIds = studentExams.stream().map(se -> se.getStudent().getStudent_id())
				.collect(Collectors.toList());

		// Find missing student IDs
		List<Long> missingStudentIds = studentIds.stream().filter(id -> !foundStudentIds.contains(id))
				.collect(Collectors.toList());

		if (!missingStudentIds.isEmpty()) {
			SuccessResponse response = new SuccessResponse();
			response.setMessage(
					"The following student IDs were not found for the specified exam: " + missingStudentIds);
			return response;
		}
		for (StudentExam studentExam : studentExams) {
			studentExam.setExamTime(newExamTime);
			studentExamRepository.save(studentExam); // Save updated entity
		}
		List<StudentExamDTO> updatedStudentExamDTOs = studentExams.stream().map(this::mapToStudentExamDTO)
				.collect(Collectors.toList());

		// Prepare response with message and updated data
		SuccessResponse response = new SuccessResponse();
		response.setMessage("Exam time updated successfully for all students.");
		response.setData(updatedStudentExamDTOs); // Return the updated StudentExamDTOs
		return response;
	}

	@Override
	public SuccessResponse getStudentsByExam(Long examId, Long franchiseId) {
		List<StudentExam> studentExams = studentExamRepository.findByExamAndFranchiseId(examId, franchiseId);
		SuccessResponse response = new SuccessResponse();

		if (studentExams.isEmpty()) {
			response.setMessage("No students found for the given exam and franchise ID.");
			return response;
		}

		// Map the list of StudentExam entities to StudentExamDTO
		List<StudentExamDTO> studentExamDTOs = studentExams.stream().map(this::mapToStudentExamDTO)
				.collect(Collectors.toList());

		response.setMessage("Students retrieved successfully for the exam.");
		response.setData(studentExamDTOs);
		return response;
	}

	private StudentExamDTO mapToStudentExamDTO(StudentExam studentExam) {
		StudentExamDTO dto = new StudentExamDTO();
		dto.setId(studentExam.getId());
		dto.setStudentId(studentExam.getStudent().getStudent_id());
		dto.setStudentName(studentExam.getStudent().getFirst_name());
		dto.setExamStatus(studentExam.getExamstatus());
		dto.setExamName(studentExam.getExamName());
		dto.setExamTime(studentExam.getExamTime());
		dto.setExamId(studentExam.getExamAttempt().getAttemptId());
		dto.setCourseId(studentExam.getCourse_id());
		dto.setFranchiseId(studentExam.getFranchiseId());
		dto.setExamType(studentExam.getExamType());
		dto.setMarks(studentExam.getMarks());
		dto.setReattemptStatus(studentExam.getReattemptStatus());
		System.out.println("reqttempt status : " + studentExam.getReattemptStatus());

		return dto;
	}

	@Override
	public SuccessResponse requestReattempt(ReattemptRequestDTO reattemptRequestDTO) {
		SuccessResponse response = new SuccessResponse();
		Optional<StudentExam> optionalStudentExam = studentExamRepository
				.findByStudentIdAndExamId(reattemptRequestDTO.getStudentId(), reattemptRequestDTO.getExamId());

		if (!optionalStudentExam.isPresent()) {
			response.ExamNotFound();
			return response;
		}
		StudentExam studentExam = optionalStudentExam.get();

		studentExam.setReattemptStatus(ReattemptStatus.REQUESTED);
		studentExamRepository.save(studentExam);

		// Use the manual mapping method instead of ModelMapper
		ReattemptRequestDTO mappedDTO = mapStudentExamToReattemptRequestDTO(studentExam);
		response.reAttemptRequestSuccess(mappedDTO);
		return response;
	}

	public ReattemptRequestDTO mapStudentExamToReattemptRequestDTO(StudentExam studentExam) {
		ReattemptRequestDTO dto = new ReattemptRequestDTO();
		dto.setCourseId(studentExam.getCourse_id()); // Convert long to String
		dto.setExamName(studentExam.getExamName());
		dto.setFranchiseId(studentExam.getFranchiseId());
		dto.setExamId(studentExam.getExamAttempt().getAttemptId());
		dto.setReattemptStatus(studentExam.getReattemptStatus());
		dto.setStudentId(studentExam.getStudent().getStudent_id());
		dto.setStudentName(studentExam.getStudent().getFirst_name());
		dto.setExamStatus(studentExam.getExamstatus());
		dto.setExamTime(studentExam.getExamTime());
		return dto;
	}

	@Override
	public SuccessResponse approveOrRejectReattempt(ReattemptRequestDTO reattemptRequestDTO) {
		SuccessResponse response = new SuccessResponse();
		Optional<StudentExam> studentExamOpt = studentExamRepository
				.findByStudentIdAndExamId(reattemptRequestDTO.getStudentId(), reattemptRequestDTO.getExamId());
		if (!studentExamOpt.isPresent()) {
			response.ExamNotFound(); // Set "Exam not found" response
			return response;
		}

		StudentExam studentExam = studentExamOpt.get();
		if (studentExam.getReattemptStatus() != ReattemptStatus.REQUESTED) {
			response.setStatus(false);
			response.setStatusCode(HttpStatus.BAD_REQUEST);
			response.setMessage("Cannot approve or reject reattempt. Reattempt was not requested.");
			return response;
		}
		if (reattemptRequestDTO.getReattemptStatus() == ReattemptStatus.APPROVED) {
			studentExam.setReattemptStatus(ReattemptStatus.APPROVED);
			studentExam.setExamstatus(ExamStatus.ASSIGNED);
			Optional<Student> findByid = studentRepo.findByid(reattemptRequestDTO.getStudentId());
			if (findByid.isPresent()) {
				Student student = findByid.get();
				student.setExamStatus(ExamStatus.ASSIGNED);
				studentRepo.save(student);
			}
			response.setMessage("Reattempt request approved.");
		} else if (reattemptRequestDTO.getReattemptStatus() == ReattemptStatus.REJECTED) {
			studentExam.setReattemptStatus(ReattemptStatus.REJECTED);
			response.setMessage("Reattempt request rejected.");
		}
		studentExamRepository.save(studentExam);
		ReattemptRequestDTO mappedDTO = mapStudentExamToReattemptRequestDTO(studentExam);
		response.setStatus(true);
		response.setStatusCode(HttpStatus.OK);
		response.setResponse(mappedDTO); // Use the mapped DTO
		return response;
	}

	@Override
	public SuccessResponse getAllOnnlineOfflineExams() {
		List<StudentExam> offlineExams = studentExamRepository.findAllExam();
		if (offlineExams.isEmpty()) {
			response.offlineExamNotFound();
			return response;
		}
		List<OfflineOnlineExamDTO> offlineOnlineExamDTOs = new ArrayList<>();
		for (StudentExam exam : offlineExams) {
			OfflineOnlineExamDTO examDTO = modelMapper.map(exam, OfflineOnlineExamDTO.class);
			examDTO.setId(exam.getId());

			Optional<Student> studentOptional = studentRepo.findByid(exam.getStudent().getStudent_id());
			if (studentOptional.isEmpty()) {
				response.studentNotFound();
				return response;
			}

			Student student = studentOptional.get();
			examDTO.setFirst_name(student.getFirst_name());
			examDTO.setLast_name(student.getLast_name());
			examDTO.setMobile_no(student.getMobile_no());
			examDTO.setEmail(student.getEmail());
			examDTO.setName_on_certificate(student.getName_on_certificate());
			examDTO.setStudent_profile_link(student.getProfile_image_link());
			examDTO.setStudent_profile_name(student.getProfile_image_name());
            examDTO.setExamName(exam.getExamName());
            examDTO.setExamDate(null);

            if(exam.getExamSubmissionTime() != null) {
                String datePart = exam.getExamSubmissionTime().split("T")[0];
                examDTO.setExamDate(datePart);
            }
            
			// Franchise details
			if (exam.getFranchiseId() == null) {
				examDTO.setFranchise_id(null);
				examDTO.setFranchise_name(null);
			} else {
				Optional<Franchise> franchiseOptional = franchiseRepo.findById(exam.getFranchiseId());
				if (franchiseOptional.isEmpty()) {
					response.franchiseNotFound();
					return response;
				}
				Franchise franchise = franchiseOptional.get();
				examDTO.setFranchise_id(franchise.getFranchise_id());
				examDTO.setFranchise_name(franchise.getFranchise_name());
				examDTO.setFranchise_owner(franchise.getFranchise_owner());
			}

			// Add each DTO to the list inside the loop
			offlineOnlineExamDTOs.add(examDTO);
		}

		// Return response with collected DTOs
		Map<String, Object> body = new HashMap<>();
		body.put("data", offlineOnlineExamDTOs);
		response.getOfflineExam(body);

		return response;
	}

	@Override
	public SuccessResponse changeDawnloadStatus(Long id) {
		Optional<StudentExam> offlineExams = studentExamRepository.findById(id);
		if (offlineExams.isEmpty()) {
			response.ExamNotFound();
			return response;
		}
		StudentExam offlineExam = offlineExams.get();
		offlineExam.setMark_as_download(true);

		studentExamRepository.save(offlineExam);
		StudentExamResponseDTO examResponseDTO = new StudentExamResponseDTO();
		examResponseDTO.setId(offlineExam.getId());
		examResponseDTO.setCourseId(offlineExam.getCourse_id());
		examResponseDTO.setExamType(offlineExam.getExamType());
		examResponseDTO.setFranchiseId(offlineExam.getFranchiseId());
		examResponseDTO.setStudent(offlineExam.getStudent());
		examResponseDTO.setExamId(offlineExam.getExamAttempt().getAttemptId());
		examResponseDTO.setMark_as_dawnload(offlineExam.getMark_as_download());
		response.changeDawnloadStatus(examResponseDTO);
		return response;

	}



	@Override
	public SuccessResponse getAllOflineExamStudentByFra(Long franchiseId, Long courseId) {
		SuccessResponse response = new SuccessResponse();

		if (franchiseId == null) {
			response.nullData();
			return response;
		}

		List<StudentExam> findByfranchiseAndCourseId = studentExamRepository
				.findOfflineStudentByFranchiseAndCourse(franchiseId, courseId);

		if (findByfranchiseAndCourseId.isEmpty()) {
			response.studentNotFound();
			return response;
		}

		// Manually map each StudentExam to StudentExamDTO
		List<StudentExamDTO> studentExamDTOs = new ArrayList<>();
		for (StudentExam studentExam : findByfranchiseAndCourseId) {
			StudentExamDTO dto = new StudentExamDTO();

			// Set each field manually
			dto.setId(studentExam.getId());
			dto.setStudentId(studentExam.getStudent().getStudent_id());
			dto.setStudentName(studentExam.getStudent().getFirst_name());
			dto.setExamStatus(studentExam.getExamstatus());
			dto.setExamName(studentExam.getExamName());
			dto.setExamTime(studentExam.getExamTime());
			dto.setExamId(studentExam.getExamAttempt().getAttemptId());
			dto.setCourseId(studentExam.getCourse_id());
			dto.setFranchiseId(studentExam.getFranchiseId());
			dto.setReattemptStatus(studentExam.getReattemptStatus());
			dto.setExamType(studentExam.getExamType());

			// Add the DTO to the list
			studentExamDTOs.add(dto);
		}

		// Set the list of DTOs in the response
		response.oflineExamStudents(studentExamDTOs);

		return response;
	}

	@Override
	public SuccessResponse getAllOnlineExamStudentByFra(Long franchiseId) {
		SuccessResponse response = new SuccessResponse();
		if (franchiseId == null) {
			response.nullData();
			return response;
		}
		List<StudentExam> findByfranchiseId = studentExamRepository.findOnlineStudentByFranchise(franchiseId);
		if (findByfranchiseId.isEmpty()) {
			response.franchiesnotfound();
			return response;
		}
		List<StudentExamDTO> studentExamDTOs = new ArrayList<>();
		for (StudentExam studentExam : findByfranchiseId) {
			StudentExamDTO dto = new StudentExamDTO();
			dto.setId(studentExam.getId());
			dto.setStudentId(studentExam.getStudent().getStudent_id());
			dto.setStudentName(studentExam.getStudent().getFirst_name());
			dto.setExamStatus(studentExam.getExamstatus());
			dto.setExamName(studentExam.getExamName());
			dto.setExamTime(studentExam.getExamTime());
			dto.setExamId(studentExam.getExamAttempt().getAttemptId());
			dto.setCourseId(studentExam.getCourse_id());
			dto.setFranchiseId(studentExam.getFranchiseId());
			dto.setReattemptStatus(studentExam.getReattemptStatus());
			dto.setExamType(studentExam.getExamType());
			studentExamDTOs.add(dto);
		}
		response.oflineExamStudents(studentExamDTOs);

		return response;
	}


	@Override
	public SuccessResponse getAllOnnlineOfflineExamsByFranchieseDate(Long franchiseId ,String startDate,String endDate) {
		List<StudentExam> offlineExams = studentExamRepository.findAllExamByFranchieseDate(franchiseId,startDate,endDate);
		if (offlineExams.isEmpty()) {
			response.offlineExamNotFound();
			return response;
		}
		List<OfflineOnlineExamDTO> offlineOnlineExamDTOs = new ArrayList<>();
		for (StudentExam exam : offlineExams) {
			OfflineOnlineExamDTO examDTO = modelMapper.map(exam, OfflineOnlineExamDTO.class);
			examDTO.setId(exam.getId());

			Optional<Student> studentOptional = studentRepo.findByid(exam.getStudent().getStudent_id());
			if (studentOptional.isEmpty()) {
				response.studentNotFound();
				return response;
			}

			Student student = studentOptional.get();
			examDTO.setFirst_name(student.getFirst_name());
			examDTO.setLast_name(student.getLast_name());
			examDTO.setMobile_no(student.getMobile_no());
			examDTO.setEmail(student.getEmail());
			examDTO.setName_on_certificate(student.getName_on_certificate());
			examDTO.setStudent_profile_link(student.getProfile_image_link());
			examDTO.setStudent_profile_name(student.getProfile_image_name());
            examDTO.setExamName(exam.getExamName());
			examDTO.setExamDate(null);

	            if(exam.getExamSubmissionTime() != null) {
	                String datePart = exam.getExamSubmissionTime().split("T")[0];
	                examDTO.setExamDate(datePart);
	            }
	            
			
			// Franchise details
			if (exam.getFranchiseId() == null) {
				examDTO.setFranchise_id(null);
				examDTO.setFranchise_name(null);
			} else {
				Optional<Franchise> franchiseOptional = franchiseRepo.findById(exam.getFranchiseId());
				if (franchiseOptional.isEmpty()) {
					response.franchiseNotFound();
					return response;
				}
				Franchise franchise = franchiseOptional.get();
				examDTO.setFranchise_id(franchise.getFranchise_id());
				examDTO.setFranchise_name(franchise.getFranchise_name());
			}

			// Add each DTO to the list inside the loop
			offlineOnlineExamDTOs.add(examDTO);
		}

		// Return response with collected DTOs
		Map<String, Object> body = new HashMap<>();
		body.put("data", offlineOnlineExamDTOs);
		response.getOfflineExam(body);

		return response;
	}

	@Override
	public SuccessResponse deleteExamById(Long id) {
		
		Optional<ExamAttempt> examById = examAttemptRepository.getExamById(id);
		
		if(examById.isEmpty()) {
			response.ExamNotFound();
			return response;
		}
		
	    examAttemptRepository.deleteExamById(id);
	    response.deleteExamSuccess();
	    return response;

	}

}
