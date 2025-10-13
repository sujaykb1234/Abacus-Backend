package com.abacus.franchise.serviceImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.abacus.franchise.dto.CourseDTO;
import com.abacus.franchise.dto.StudentDTO;
import com.abacus.franchise.dto.StudentResponseDTO;
import com.abacus.franchise.dto.stdDTO;
import com.abacus.franchise.model.Address;
import com.abacus.franchise.model.Course;
import com.abacus.franchise.model.ExamAttempt;
import com.abacus.franchise.model.Franchise;
import com.abacus.franchise.model.FranchiseKitRequest;
import com.abacus.franchise.model.PracticeStudent;
import com.abacus.franchise.model.Questions;
import com.abacus.franchise.model.StoredImages;
import com.abacus.franchise.model.Student;
import com.abacus.franchise.model.StudentExam;
import com.abacus.franchise.model.StudentPracticeTests;
import com.abacus.franchise.repo.CourseRepo;
import com.abacus.franchise.repo.ExamAttemptRepository;
import com.abacus.franchise.repo.FranchiseKitRequestRepo;
import com.abacus.franchise.repo.FranchiseRepo;
import com.abacus.franchise.repo.PracticeStudentRepo;
import com.abacus.franchise.repo.QuestionsRepo;
import com.abacus.franchise.repo.StudentEnrollmentRepo;
import com.abacus.franchise.repo.StudentExamRepository;
import com.abacus.franchise.repo.StudentPracticeTestsRepo;
import com.abacus.franchise.repo.StudentRepo;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.S3BucketService;
import com.abacus.franchise.service.StudentService;
import com.abacus.franchise.utility.CourseType;
import com.abacus.franchise.utility.ExamStatus;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Component
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepo studentRepo;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	CourseRepo courseRepo;

	@Autowired
	S3BucketService s3BucketService;

	@Autowired
	EntityManager entityManager;

	@Autowired
	FranchiseRepo franchiseRepo;

	@Autowired
	StudentEnrollmentRepo enrollmentRepo;

	@Autowired
	QuestionsRepo questionsRepo;

	@Autowired
	ExamAttemptRepository attemptRepository;

	@Autowired
	StudentPracticeTestsRepo practiceTestsRepo;

	@Autowired
	FranchiseKitRequestRepo franchiseKitRequestRepo;

	@Autowired
	StudentExamRepository examRepository;

	@Autowired
	StudentExamRepository studentExamRepo;

	@Autowired
	StudentRepo studentRepo2;

	@Override
	public SuccessResponse registerAndUpdateTheStudent(Student student, MultipartFile studentPhoto) throws IOException {
		System.out.println("student district : " + student.getAddress().getDistrict());
		SuccessResponse response = new SuccessResponse();
		System.out.println(student.getFirst_name());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		if (student.getDob() == null || student.getFirst_name() == null || student.getLast_name() == null
				|| student.getMobile_no() == null) {
			response.nullDataForStudent(student);
			return response;
		}
		Optional<Student> byEmailID = studentRepo.findByEmail(student.getEmail());
		if (byEmailID.isPresent() && !byEmailID.get().getStudent_id().equals(student.getStudent_id())) {
			response.emailAlreadyExist();
			return response;
		}
		Optional<Student> byMobileNo = studentRepo.findByMobileNumber(student.getMobile_no());
		if (byMobileNo.isPresent() && !byMobileNo.get().getStudent_id().equals(student.getStudent_id())) {
			response.mobileAlreadyExist();
			return response;
		}
		if (student.getFranchise() != null) {
			Optional<Franchise> franchiseOpt = franchiseRepo.findById(student.getFranchise().getFranchise_id());
			if (franchiseOpt.isPresent()) {
				student.setFranchise(franchiseOpt.get());
			} else {
				response.franchiseNotFound();
				return response;
			}
		}
		List<Course> attachedCourses = new ArrayList<>();
		for (Course course : student.getCourses()) {
			if (course.getCourse_id() != null) {
				Course existingCourse = courseRepo.findcourseById(course.getCourse_id()).orElse(null);
				if (existingCourse != null) {
					FranchiseKitRequest kitRequest = franchiseKitRequestRepo.findByFranchiseIdAndCourseId(
							student.getFranchise().getFranchise_id(), existingCourse.getCourse_id()).orElse(null);
					if (kitRequest == null) {
						response.orderKitFirst(existingCourse.getCourse_id());
						return response;
					}
					if (kitRequest.getRemainingStudents() <= 0) {
						response.insufficientKits();
						return response;
					}
					kitRequest.setRemainingStudents(kitRequest.getRemainingStudents() - 1);
					franchiseKitRequestRepo.save(kitRequest);
					System.out.println("new course optional : " + existingCourse.getCourse_name());
					attachedCourses.add(existingCourse);
				} else {
					response.courseNotFound(course.getCourse_id());
					return response;
				}
			}
		}

		if (student.getStudent_id() != null) {
			Optional<Student> existingStudentOpt = studentRepo.findStudentById(student.getStudent_id());
			if (existingStudentOpt.isPresent()) {
				Student existingStudent = existingStudentOpt.get();
				if (student.getFirst_name() != null)
					existingStudent.setFirst_name(student.getFirst_name());
				if (student.getLast_name() != null)
					existingStudent.setLast_name(student.getLast_name());
				if (student.getMobile_no() != null)
					existingStudent.setMobile_no(student.getMobile_no());
				if (student.getEmail() != null)
					existingStudent.setEmail(student.getEmail());
				if (student.getDob() != null)
					existingStudent.setDob(student.getDob());
				if (student.getPassword() != null)
					existingStudent.setPassword(student.getPassword());
				if (student.getFranchise() != null)
					existingStudent.setFranchise(student.getFranchise());
				if (student.getAddress() != null) {
					if (existingStudent.getAddress() == null) {
						existingStudent.setAddress(student.getAddress());
					} else {
						if (student.getAddress().getCity() != null)
							existingStudent.getAddress().setCity(student.getAddress().getCity());
						if (student.getAddress().getState() != null)
							existingStudent.getAddress().setState(student.getAddress().getState());
						if (student.getAddress().getDistrict() != null)
							existingStudent.getAddress().setDistrict(student.getAddress().getDistrict());
					}
				}
				if (studentPhoto != null && !studentPhoto.isEmpty()) {
					if (existingStudent.getProfile_image_name() != null) {
						s3BucketService.deleteFile(existingStudent.getProfile_image_name());
					}
					StoredImages storedImages = s3BucketService.storeFile(studentPhoto.getOriginalFilename(),
							studentPhoto.getInputStream(), studentPhoto.getSize(), 1);
					existingStudent.setProfile_image_link(storedImages.getProfile_image_link());
					existingStudent.setProfile_image_name(storedImages.getProfile_image_name());
				}
				existingStudent.setModification_time(formatter.format(now));
				existingStudent.getCourses().addAll(attachedCourses);
				studentRepo.save(existingStudent);
				StudentDTO studentDto = modelMapper.map(existingStudent, StudentDTO.class);
				if (existingStudent.getFranchise() != null) {
					studentDto.setFranchiseName(existingStudent.getFranchise().getFranchise_name());
				}
				response.studentUpdated(studentDto);
				return response;
			} else {
				response.idNotFound();
				return response;
			}
		}

		if (attachedCourses.isEmpty()) {
			response.courseNotSelected();
			return response;
		}
		if (studentPhoto == null || studentPhoto.isEmpty()) {
			response.nullFile();
			return response;
		}
		try {
			StoredImages storedProfileImage = s3BucketService.storeFile(studentPhoto.getOriginalFilename(),
					studentPhoto.getInputStream(), studentPhoto.getSize(), 1);
			student.setProfile_image_name(storedProfileImage.getProfile_image_name());
			student.setProfile_image_link(storedProfileImage.getProfile_image_link());
		} catch (IOException e) {
			e.printStackTrace();
			response.ExceptionForImg(studentPhoto.getName());
			return response;
		}

		student.setCreation_time(formatter.format(now));
		student.setCourses(attachedCourses);
		student.setCurrentCourseId(attachedCourses.get(0).getCourse_id());
		student.setCurrentCourseName(attachedCourses.get(0).getCourse_name());
		Student savedStudent = studentRepo.save(student);
		StudentDTO studentDto = modelMapper.map(student, StudentDTO.class);
		if (student.getFranchise() != null) {
			studentDto.setFranchiseName(student.getFranchise().getFranchise_name());
		}
		response.saveTheStudent(savedStudent);
		return response;

	}

	@Override
	public SuccessResponse loginTheStudent(Student student) {
		SuccessResponse response = new SuccessResponse();
		System.out.println("Entered Mobile No: " + student.getMobile_no());
		if (student.getMobile_no() == null || student.getPassword() == null) {
			System.out.println("Mobile Number: " + student.getMobile_no());
			System.out.println("Password: " + student.getPassword());
			response.nullAdminnameAndPass();
			return response;
		}

		String studentUsername = student.getMobile_no();
		String studentPassword = student.getPassword();

		System.out.println("student Username: " + studentUsername);
		Optional<Student> byUsername = studentRepo.findByusername(studentUsername);

		if (byUsername.isPresent()) {
			Student studentFromDb = byUsername.get();

			StudentDTO studentDto = modelMapper.map(studentFromDb, StudentDTO.class);
			studentDto.setExamstatus(studentFromDb.getExamStatus());
			System.out.println("student exam status from DB :" + studentFromDb.getExamStatus());
			if (studentPassword.equals(studentFromDb.getPassword())) {
				System.out.println("Exam status : " + studentDto.getExamstatus());
				response.StudentloginSuccessfully(studentDto);
			} else {
				response.wrongPassword();
			}
		} else {
			response.userNotFound();
		}

		return response;
	}

	@Override
	public SuccessResponse saveTheNewPasswordFotTheStudent(Student student) {

		SuccessResponse response = new SuccessResponse();
		if (student.getEmail() == null) {
			response.emailNotFound();
			return response;

		}
		String loginusername = student.getEmail();
		String loginpassword = student.getPassword();
		Optional<Student> byUserName = studentRepo.findByusernameForPassword(loginusername);
		if (byUserName.isPresent()) {
			if (loginpassword == null) {
				response.passwordNull();
				return response;
			}
			if (loginpassword.length() < 6) {
				response.lengofpassword();
				return response;
			}
			Student student2 = byUserName.get();
			student2.setPassword(loginpassword);
			student2.setCreation_time(byUserName.get().getCreation_time());
			studentRepo.save(student2);
			StudentDTO newPassword = modelMapper.map(student2, StudentDTO.class);
			response.passwordUpdateSuccesfully(newPassword);
			return response;

		} else {
			response.incorrectUserName();
		}
		return response;
	}
	
//-------------------------------------------------------------------------------------------------------------------------------	

	public SuccessResponse getTheAllStudent(int pageNo, int pageSize, String sortBy, String orderBy) {
		SuccessResponse response = new SuccessResponse();
		Sort newSort;

		if (orderBy.equalsIgnoreCase("ASC")) {
			newSort = Sort.by(sortBy).ascending();
		} else {
			newSort = Sort.by(sortBy).descending();
		}

		PageRequest page = PageRequest.of(pageNo, pageSize, newSort);

		Page<Student> studentPage = studentRepo.getAllStudent(page);

		List<Student> students = studentPage.getContent();

		if (!students.isEmpty()) {
			List<StudentDTO> studentDTOs = new ArrayList<>();
			for (Student student : students) {
				StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);

				if (student.getFranchise() != null) {
					studentDTO.setFranchiseName(student.getFranchise().getFranchise_name());
				}

				if (student.getAddress() != null) {
					Address addressDTO = modelMapper.map(student.getAddress(), Address.class);
					studentDTO.setAddress(addressDTO);
				}

				if (student.getCourses() != null) {
					List<Course> courseDTOs = new ArrayList<>();
					for (Course course : student.getCourses()) {
						Course courseDTO = modelMapper.map(course, Course.class);
						courseDTOs.add(courseDTO);
					}
					studentDTO.setCourses(courseDTOs);
				}

				studentDTOs.add(studentDTO);
			}

			Map<String, Object> body = new HashMap<>();
			body.put("data", studentDTOs);
			body.put("totalPages", studentPage.getTotalPages());
			body.put("totalRecords", studentPage.getTotalElements());

//	        response.setResponse(body);
			response.studentGetSuccesfully(body);
		} else {
			response.emailNotFound();
		}

		return response;
	}

//---------------------------------------------------------------------------------------------------------------------------	

	@Override
	public SuccessResponse getTheStudentUsingTheID(Long id) {
		SuccessResponse response = new SuccessResponse();
		if (id != null) {
			Optional<Student> byId = studentRepo.findbystudentUsingId(id);
			if (byId.isPresent()) {
				Student student = byId.get();
				System.out.println("student exam status 425 : " + byId.get().getExamStatus());
				stdDTO studentDto = modelMapper.map(student, stdDTO.class);
				System.out.println("student exam status 427 : " + studentDto.getExamstatus());

				if (student.getFranchise() != null) {
					String franchiseName = student.getFranchise().getFranchise_name();
					String franchiseUserName = student.getFranchise().getMobile_no();
					studentDto.setFranchiseName(franchiseName);
					studentDto.setUserName(franchiseUserName);
				} else {
					studentDto.setFranchiseName("No Franchise Assigned");
				}
				List<Course> registeredCourses = student.getCourses();
				Map<Long, CourseDTO> courseMap = new HashMap<>();
				if (registeredCourses != null && !registeredCourses.isEmpty()) {
					for (Course course : registeredCourses) {
						CourseDTO courseDTO = new CourseDTO();
						courseDTO.setCourse_id(course.getCourse_id());
						courseDTO.setCourse_duration(course.getCourse_duration());
						courseDTO.setCourse_name(course.getCourse_name());
						courseDTO.setCourseType(course.getCourseType());
						courseDTO.setExamstatus(ExamStatus.PENDING);
						System.out.println("Course dto exam status 446: " + courseDTO.getExamstatus());
						courseDTO.setNo_of_books(course.getNo_of_books());
						courseMap.put(course.getCourse_id(), courseDTO);
					}
				}
				
				List<StudentExam> studentExams = studentExamRepo.findByStudentId(id);
				if (!studentExams.isEmpty()) {
					StudentExam latestExam = studentExams.get(studentExams.size() - 1);
					System.out.println("Letest course exam status : " + latestExam.getExamstatus());

					studentDto.setExamstatus(latestExam.getExamstatus());
					studentDto.setExamType(latestExam.getExamType());
					for (StudentExam studentExam : studentExams) {
						Long courseId = studentExam.getCourse_id();
						CourseDTO courseDTO = courseMap.getOrDefault(courseId, new CourseDTO());
						courseDTO.setCourse_id(courseId);
						courseDTO.setCourse_name(studentExam.getCourse_name());
						courseDTO.setExamstatus(studentExam.getExamstatus());
						System.out.println("464" + courseDTO.getExamstatus());
						courseDTO.setExamType(studentExam.getExamType());
						courseMap.put(courseId, courseDTO);
					}
				} else {
					studentDto.setExamstatus(ExamStatus.PENDING);
					studentDto.setExamType(null);
				}
				studentDto.setCourses(new ArrayList<>(courseMap.values()));
				response.getSingleStudent(studentDto);
			} else {
				response.notfound();
			}
		} else {
			response.idNotFound();
		}
		return response;
	}

//-------------------------------------------------------------------------------------------------------------------------------

	@Override
	public SuccessResponse getTheStudentUsingTheName(String name, Long franhce_id) {
		SuccessResponse response = new SuccessResponse();

		if (name == null || name.isEmpty()) {
			response.nameIsNull();
			return response;
		}
		if (franhce_id == null) {
			response.idisNull();
			return response;
		}

		Optional<Student> franhcies = studentRepo.findById(franhce_id);

		if (franhcies == null || franhcies.isEmpty()) {
			response.franchiesnotfound();
			return response;
		}

		List<Student> students = studentRepo.findTheStudentUsigTheName(name, franhce_id);

		if (students == null || students.isEmpty()) {
//			response.notFoundOrIncorrectName();
			response.studentNotFound();
		} else {
			List<StudentDTO> studentDTOs = new ArrayList<>();
			for (Student student : students) {
				StudentDTO studentDto = modelMapper.map(student, StudentDTO.class);

				if (student.getFranchise() != null) {
					studentDto.setFranchiseName(student.getFranchise().getFranchise_name());
				}

				studentDTOs.add(studentDto);
			}
			response.getUisngTheName(studentDTOs);
		}

		return response;
	}

//-------------------------------------------------------------------------------------------------------------------------	

	public SuccessResponse getStudentsByFranchiseId(Long franchise_id, int pageNo, int pageSize, String sortBy,
			String orderBy) {
		SuccessResponse response = new SuccessResponse();

		if (franchise_id == null) {
			response.nullData();
			return response;
		}

		Optional<Franchise> franchiseOpt = franchiseRepo.findById(franchise_id);
		if (!franchiseOpt.isPresent()) {
			response.franchiseNotFound();
			return response;
		}

		Sort newSort;
		if (orderBy.equalsIgnoreCase("ASC")) {
			newSort = Sort.by(sortBy).ascending();
		} else {
			newSort = Sort.by(sortBy).descending();
		}

		PageRequest pageRequest = PageRequest.of(pageNo, pageSize, newSort);

		Page<Student> studentPage = studentRepo.findByFranchiseId(franchise_id, pageRequest);
		List<Student> students = studentPage.getContent();

		if (students.isEmpty()) {
			response.noStudentsFound();
			return response;
		}
		List<StudentDTO> studentDTOs = new ArrayList<>();
		for (Student student : students) {
			StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);

			if (student.getFranchise() != null) {
				studentDTO.setFranchiseName(student.getFranchise().getFranchise_name());
			}

			if (student.getAddress() != null) {
				Address addressDTO = modelMapper.map(student.getAddress(), Address.class);
				studentDTO.setAddress(addressDTO);
			}

			if (student.getCourses() != null) {
				List<Course> courseDTOs = new ArrayList<>();
				for (Course course : student.getCourses()) {
					Course courseDTO = modelMapper.map(course, Course.class);
					courseDTOs.add(courseDTO);
				}
				studentDTO.setCourses(courseDTOs);
			}

			studentDTOs.add(studentDTO);
		}

		Map<String, Object> body = new HashMap<>();
		body.put("data", studentDTOs);
		body.put("totalPages", studentPage.getTotalPages());
		body.put("totalRecords", studentPage.getTotalElements());

//	    response.setResponse(body);
		response.studentGetSuccesfully(body);
		return response;
	}

//-----------------------------------------------------------------------------------------------------------------------------------	

	@Override
	public SuccessResponse deleteTheStudentUsingTheId(Long id) {
		SuccessResponse response = new SuccessResponse();
		if (id == null) {
			response.idisNull();
			return response;
		}
		Optional<Student> getByid = studentRepo.findbystudentUsingId(id);
		if (getByid.isPresent()) {
			Student student = getByid.get();
			student.setStatus(false);
			studentRepo.save(student);
			response.studentdeleteSuccesfully();
			return response;
		} else {
			response.noStudentsFound();
			return response;
		}
	}

//-------------------------------------------------------------------------------------------------------------------------	

	@Override
	public SuccessResponse getTheStudentUsingTheCourseID(Long id) {
		SuccessResponse response = new SuccessResponse();

		if (id == null) {
			response.idisNull();
			return response;
		}

		List<Student> students = studentRepo.getBYstudentUsingTheCourseID(id);
		if (students != null && !students.isEmpty()) {
			List<StudentDTO> studentDTOs = new ArrayList<>();

			for (Student student : students) {
				System.out.println("Processing student with ID: " + student.getStudent_id());

				StudentDTO studentDto = modelMapper.map(student, StudentDTO.class);

				List<StudentExam> studentExams = studentExamRepo.findByStudentId(student.getStudent_id());

				if (studentExams != null && !studentExams.isEmpty()) {
					for (StudentExam studentExam : studentExams) {
						System.out.println("Fetched StudentExam for student ID " + student.getStudent_id() + ":");

//						studentDto.setExamstatus(studentExam.getExamstatus());
						studentDto.setExamstatus(studentExam.getExamstatus());

						studentDto.setExamType(studentExam.getExamType());
					}
				}

				studentDTOs.add(studentDto);
			}

			System.out.println("Number of students processed: " + studentDTOs.size());

			response.getSingleStudentUsingCourseID(studentDTOs);
		} else {
			response.noStudentsFound();
		}

		return response;
	}

//------------------------------------------------------------------------------------------------------------------	
	@Override
	public SuccessResponse getPractiseExams(Long studentId, Long courseId) {
		SuccessResponse response = new SuccessResponse();
		if (studentId == null || courseId == null) {
			response.nullData();
			return response;
		}
		List<StudentPracticeTests> tests = practiceTestsRepo.findByStudentAndCourse(studentId, courseId);
		if (tests.isEmpty()) {
			response.noPracticeTestsFound();
			return response;
		}
		response.practiceTestsForStudent(tests);
		return response;
	}

//-----------------------------------------------------------------------------------------------------------------------
	@Override
	public SuccessResponse getFinalExamForStudent(Long studentId, Long courseId) {
		SuccessResponse response = new SuccessResponse();

		if (studentId == null || courseId == null) {
			response.nullData();
			return response;
		}

		Optional<Student> studentOpt = studentRepo.findById(studentId);
		if (!studentOpt.isPresent()) {
			response.studentNotFound();
			return response;
		}

		List<StudentExam> examAttempts = examRepository.getExamByCourseIdAndStudentId(courseId, studentId);
		if (examAttempts.isEmpty()) {
			response.noExamsFound();
			return response;
		}

		StudentExam latestExamAttempt = examAttempts.get(examAttempts.size() - 1);

		List<Questions> questions = new ArrayList<>();
		for (Long questionId : latestExamAttempt.getQuestionIds()) {
			// Assume a Question entity and repository exists
			Optional<Questions> questionOpt = questionsRepo.findById(questionId);
			questionOpt.ifPresent(questions::add);
		}

		Map<String, Object> examDetails = new HashMap<>();
		examDetails.put("examName", latestExamAttempt.getExamName());
		examDetails.put("examTime", latestExamAttempt.getExamTime());
		examDetails.put("questions", questions);
		examDetails.put("totalQuestions", questions.size());
		examDetails.put("studentAnswers", latestExamAttempt.getStudentAnswers());
		examDetails.put("examId", latestExamAttempt.getExamAttempt().getAttemptId());

		response.examForStudentsRetrived(examDetails);
		return response;
	}


	@Override
	public SuccessResponse getFinalExamForStudentInOnline(Long studentId, Long courseId) {
		SuccessResponse response = new SuccessResponse();

		if (studentId == null || courseId == null) {
			response.nullData();
			return response;
		}

		Optional<Student> studentOpt = studentRepo.findById(studentId);
		if (!studentOpt.isPresent()) {
			response.studentNotFound();
			return response; 
		}

		Optional<StudentExam> latestExamOpt = examRepository.findLatestExamByCourseIdAndStudentId(courseId, studentId);
		if (!latestExamOpt.isPresent()) {
			response.noExamsFound();
			return response;
		}

		StudentExam latestExamAttempt = latestExamOpt.get();

		// Fetching questions
		List<Questions> questions = new ArrayList<>();
		for (Long questionId : latestExamAttempt.getQuestionIds()) {
			Optional<Questions> questionOpt = questionsRepo.getQuestionsById(questionId);
			questionOpt.ifPresent(questions::add);
		}

		// Preparing response data
		Map<String, Object> examDetails = new HashMap<>();
		examDetails.put("examName", latestExamAttempt.getExamName());
		examDetails.put("examTime", latestExamAttempt.getExamTime());
		examDetails.put("questions", questions);
		examDetails.put("totalQuestions", questions.size());
		examDetails.put("studentAnswers", latestExamAttempt.getStudentAnswers());
		examDetails.put("examId", latestExamAttempt.getExamAttempt().getAttemptId());

		response.examForStudentsRetrived(examDetails);
		return response;
	}

	private String convertImageToBase64(String imageUrl) {
		try {
			URL url = new URL(imageUrl);
			try (InputStream is = url.openStream(); ByteArrayOutputStream os = new ByteArrayOutputStream()) {
				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = is.read(buffer)) != -1) {
					os.write(buffer, 0, bytesRead);
				}
				byte[] imageBytes = os.toByteArray();
				return Base64.getEncoder().encodeToString(imageBytes);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null; // Handle error appropriately in a real scenario
		}
	}

	@Override
	public SuccessResponse getAllStudentCount() {
		SuccessResponse response = new SuccessResponse();
		Long allStudentsCounts = studentRepo.getAllStudentsCounts();
		response.studentCount(allStudentsCounts);
		return response;
	}



	@Override
	public SuccessResponse getTheStudentUsingByFranchiesIdAndCourseId(Long franchies_id, Long course_id) {
		SuccessResponse response = new SuccessResponse();

		if (franchies_id == null || course_id == null) {
			response.nullData();
			return response;
		}

		// Fetch students from the database
		List<Student> allStudents = studentRepo.getAllStudentByFranchiseIdAndCourseId(franchies_id, course_id);

		if (allStudents != null && !allStudents.isEmpty()) {
			List<StudentResponseDTO> studentResponseList = new ArrayList<>();
			for (Student student : allStudents) {
				StudentResponseDTO dto = convertStudentToDTO(student);
				// Set the courseExamStatus
				dto.setProfile_image_link(student.getProfile_image_link());
				dto.setCourseExamStatus(determineCourseExamStatus(student, franchies_id, course_id));
				studentResponseList.add(dto);
			}
			response.getAllStudent(studentResponseList);
		} else {
			response.studentNotFound();
		}
		return response;
	}

	private String determineCourseExamStatus(Student student, Long franchiseId, Long courseId) {
		// Fetch the StudentExam record
		Optional<StudentExam> studentExamOpt = studentExamRepo
				.existsByStudentIdAndFranchiseIdAndCourseId(student.getStudent_id(), franchiseId, courseId);

		if (studentExamOpt.isPresent()) {
			StudentExam studentExam = studentExamOpt.get();
			// Check the `examStatus` and return appropriate status
			if (studentExam.getExamstatus().equals(ExamStatus.ASSIGNED)) {
				return "ASSIGNED";
			} else if (studentExam.getExamstatus().equals(ExamStatus.COMPLETED)) {
				return "COMPLETED";
			}
		}
		return "PENDING";
	}

	private StudentResponseDTO convertStudentToDTO(Student student) {
		StudentResponseDTO dto = new StudentResponseDTO();
		dto.setId(student.getStudent_id());
		dto.setStudent_id(student.getStudent_id());
		dto.setFranchiseName(student.getFranchise().getFranchise_name());
		dto.setCurrentCourseName(student.getCurrentCourseName());
		dto.setCurrentCourseId(student.getCurrentCourseId());
		dto.setCreation_time(student.getCreation_time());
		dto.setDob(student.getDob());
		dto.setMobile_no(student.getMobile_no());
		dto.setEmail(student.getEmail());
		dto.setFranchise_owner(student.getFranchise().getFranchise_owner());
		dto.setFirst_name(student.getFirst_name());
		dto.setLast_name(student.getLast_name());
		dto.setPassword(student.getPassword());
		return dto;
	}

//--------------------------------------------------------------------------------------------------------------------------

	@Transactional
	@Override
	public SuccessResponse assignTheAnotherCourseToStudent(Long courseId, Long studentId) {
		SuccessResponse response = new SuccessResponse();

		if (courseId == null || studentId == null) {
			response.nullData();
			return response;
		}

		Optional<Course> courseOptional = courseRepo.findcourseById(courseId);
		if (!courseOptional.isPresent()) {
			response.courseNotFound(courseId);
			return response;
		}
		Optional<Student> studentOptional = studentRepo.findById(studentId);
		if (!studentOptional.isPresent()) {
			response.studentNotFound();
			return response;
		}
		Course course = courseOptional.get();
		Student student = studentOptional.get();
		List<StudentExam> studentExams = studentExamRepo.findAllExamsByStudent(studentId);
		for (StudentExam studentExam : studentExams) {
			System.out.println("STUDENT EXAM ID : " + studentExam.getId());
			System.out.println("EXAM STATUS : " + studentExam.getExamstatus());
			if (!studentExam.getExamstatus().equals(ExamStatus.COMPLETED)) {
				response.examNotCompleted();
				return response;
			}
		}
		Long cnt = studentRepo.getCourseAssignCountBystudentAndCourseId(studentId, courseId);
		if (cnt > 0) {
			response.alredyInTheCourse();
			return response;
		}
		if (student.getCourses().contains(course)) {
			response.studentAlreadyEnrolledCourse();
			return response;
		}
		student.addCourse(course);
		studentRepo.save(student);
		ExamAttempt newExamAttempt = new ExamAttempt();
		attemptRepository.save(newExamAttempt);
		StudentExam newStudentExam = new StudentExam();
		newStudentExam.setStudent(student);
		newStudentExam.setCourse_id(course.getCourse_id());
		newStudentExam.setCourse_name(course.getCourse_name());
		newStudentExam.setExamstatus(ExamStatus.PENDING);
		newStudentExam.setAssignedAt(new Timestamp(System.currentTimeMillis()));
		newStudentExam.setExamAttempt(newExamAttempt);
		newStudentExam.setExamType(studentExams.get(0).getExamType());
		studentExamRepo.save(newStudentExam);
		response.courseAssign(student);
		return response;
	}



	@Override
	@Transactional
	public SuccessResponse switchCourse(Long newCourseId, Long studentId) {
		SuccessResponse response = new SuccessResponse();
		
		System.out.println("C : "+newCourseId+" S:"+studentId);
		
		Optional<Student> studentOpt = studentRepo.findById(studentId);

		if (!studentOpt.isPresent()) {
			response.idNotFound();
			return response;
		}

		Student student = studentOpt.get();
		Long cnt = studentRepo.getCourseAssignCountBystudentAndCourseId(studentId, newCourseId);

		if (cnt > 0) {
			response.alredyInTheCourse();
			return response;
		}

		Optional<Course> newCourseOpt = courseRepo.findById(newCourseId);

		if (!newCourseOpt.isPresent()) {
			response.courseNotFound(newCourseId);
			return response;
		}

		Course newCourse = newCourseOpt.get();

		// Check course type and handle logic
		if (newCourse.getCourseType() == CourseType.ABACUS) {
			// Ensure the current course is completed for ABACUS type courses
			if (!student.getExamStatus().equals(ExamStatus.COMPLETED)) {
				response.examNotCompletedForCurrentCourse();
				return response;
			}

			// Update student's current course and exam status for ABACUS courses
			FranchiseKitRequest kitRequest = franchiseKitRequestRepo
					.findByFranchiseIdAndCourseId(student.getFranchise().getFranchise_id(), newCourseId).orElse(null);

			if (kitRequest == null || kitRequest.getRemainingStudents() <= 0) {
				response.insufficientKits();
				return response;
			}

			kitRequest.setRemainingStudents(kitRequest.getRemainingStudents() - 1);
			franchiseKitRequestRepo.save(kitRequest);
			student.setCurrentCourseId(newCourse.getCourse_id());
			student.setCurrentCourseName(newCourse.getCourse_name());
			student.setExamStatus(ExamStatus.PENDING);
			student.setName_on_certificate(null);
			student.setExamType(null);
		} else if (newCourse.getCourseType() == CourseType.OTHER) {
			System.out.println("Enrollment in OTHER course type allowed without changing current course or exam.");
		}
		student.addCourse(newCourse);
		Student savedStudent = studentRepo.save(student);
		// Prepare and return response
		StudentDTO studentDto = modelMapper.map(savedStudent, StudentDTO.class);
		studentDto.setCurrentCourseId(student.getCurrentCourseId());
		studentDto.setCurrentCourseName(student.getCurrentCourseName());
		response.swichCourseSuccessfully(studentDto);
		return response;
	}

	@Autowired
	PracticeStudentRepo practiceStudentRepo;

	@Override
	public SuccessResponse registerAndUpdateTheStudentDemo(PracticeStudent student, MultipartFile studentPhoto)
			throws IOException {
		System.out.println("student obj : " + student);
		SuccessResponse response = new SuccessResponse();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		if (student.getDob() == null || student.getName() == null || student.getMobile_no() == null) {
			response.nullDataForStudent(student);
			return response;
		}
		Optional<PracticeStudent> byEmailID = practiceStudentRepo.findByEmail(student.getEmail());
		if (byEmailID.isPresent() && !byEmailID.get().getStudent_id().equals(student.getStudent_id())) {
			response.emailAlreadyExist();
			return response;
		}
		Optional<PracticeStudent> byMobileNo = practiceStudentRepo.findByMobileNumber(student.getMobile_no());
		if (byMobileNo.isPresent() && !byMobileNo.get().getStudent_id().equals(student.getStudent_id())) {
			response.mobileAlreadyExist();
			return response;
		}
		if (student.getStudent_id() != null) {
			Optional<PracticeStudent> existingStudentOpt = practiceStudentRepo.findStudentById(student.getStudent_id());
			if (existingStudentOpt.isPresent()) {
				PracticeStudent existingStudent = existingStudentOpt.get();

				if (student.getName() != null)
					existingStudent.setName(student.getName());
				if (student.getGender() != null)
					existingStudent.setGender(student.getGender());
				if (student.getMobile_no() != null)
					existingStudent.setMobile_no(student.getMobile_no());
				if (student.getEmail() != null)
					existingStudent.setEmail(student.getEmail());
				if (student.getDob() != null)
					existingStudent.setDob(student.getDob());
				if (student.getPassword() != null)
					existingStudent.setPassword(student.getPassword());
				if (student.getAddress() != null) {
					if (existingStudent.getAddress() == null) {
						existingStudent.setAddress(student.getAddress());
					} else {
						if (student.getAddress().getCity() != null)
							existingStudent.getAddress().setCity(student.getAddress().getCity());
						if (student.getAddress().getState() != null)
							existingStudent.getAddress().setState(student.getAddress().getState());
						if (student.getAddress().getDistrict() != null)
							existingStudent.getAddress().setDistrict(student.getAddress().getDistrict());
					}
				}
				if (studentPhoto != null && !studentPhoto.isEmpty()) {
					if (existingStudent.getProfile_image_name() != null) {
						s3BucketService.deleteFile(existingStudent.getProfile_image_name());
					}
					StoredImages storedImages = s3BucketService.storeFile(studentPhoto.getOriginalFilename(),
							studentPhoto.getInputStream(), studentPhoto.getSize(), 1);
					existingStudent.setProfile_image_link(storedImages.getProfile_image_link());
					existingStudent.setProfile_image_name(storedImages.getProfile_image_name());
				}
				existingStudent.setModification_time(formatter.format(now));
				practiceStudentRepo.save(existingStudent);
				StudentDTO studentDto = modelMapper.map(existingStudent, StudentDTO.class);
				response.studentUpdated(studentDto);
				return response;
			} else {
				response.idNotFound();
				return response;
			}
		}

		if (studentPhoto == null || studentPhoto.isEmpty()) {
			response.nullFile();
			return response;
		}
		try {
			StoredImages storedProfileImage = s3BucketService.storeFile(studentPhoto.getOriginalFilename(),
					studentPhoto.getInputStream(), studentPhoto.getSize(), 1);
			student.setProfile_image_name(storedProfileImage.getProfile_image_name());
			student.setProfile_image_link(storedProfileImage.getProfile_image_link());
		} catch (IOException e) {
			e.printStackTrace();
			response.ExceptionForImg(studentPhoto.getName());
			return response;
		}

		student.setCreation_time(formatter.format(now));
		PracticeStudent savedStudent = practiceStudentRepo.save(student);
		response.saveTheStudent(savedStudent);
		return response;
	}

	@Override
	public SuccessResponse getDemoStudentById(Long studentId) {
		SuccessResponse response = new SuccessResponse();
		if (studentId == null) {
			response.nullData();
			return response;
		}

		Optional<PracticeStudent> findById = practiceStudentRepo.findById(studentId);
		if (!findById.isPresent()) {
			response.studentNotFound();
			return response;
		}
		response.studentsgetSucccesfully(findById);
		return response;
	}

}
