 package com.abacus.franchise.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.abacus.franchise.exception.DataNotValidException;
import com.abacus.franchise.model.Mail;
import com.abacus.franchise.model.PracticeStudent;
import com.abacus.franchise.model.Student;
import com.abacus.franchise.repo.StudentRepo;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.MailService;
import com.abacus.franchise.service.StudentService;

@RestController
@RequestMapping("abacus/v1/student/")
public class StudentController {

	@Autowired
	StudentService studentService;

	@Autowired
	StudentRepo studentRepo;
	@Autowired
	MailService mailService;

	@PostMapping("registerAndUpdateTheStudent")
	public ResponseEntity<SuccessResponse> registerAndUpdateTheStudent(@ModelAttribute Student student,
			@RequestParam(value = "studentPhoto", required = false) MultipartFile studentPhoto,
			@RequestParam(value = "studentDocPhoto", required = false) MultipartFile studentDocPhoto)
			throws DataNotValidException, IOException {
		SuccessResponse response = studentService.registerAndUpdateTheStudent(student, studentPhoto); // studentDocPhoto
		return ResponseEntity.ok(response);
	}

	@PostMapping("registerAndUpdateTheStudentDemo") // ON mayur sir dimand 20/12
	public ResponseEntity<SuccessResponse> registerAndUpdateTheStudentDemo(@ModelAttribute PracticeStudent student,
			@RequestParam(value = "studentPhoto", required = false) MultipartFile studentPhoto,
			@RequestParam(value = "studentDocPhoto", required = false) MultipartFile studentDocPhoto)
			throws DataNotValidException, IOException {
		SuccessResponse response = studentService.registerAndUpdateTheStudentDemo(student, studentPhoto); // studentDocPhoto
		return ResponseEntity.ok(response);
	}

	@GetMapping("/getDemoStudentById/{studentId}") 
	public SuccessResponse getDemoStudentById(@PathVariable Long studentId) {
		return studentService.getDemoStudentById(studentId); 
	}
//------------------------------------------------------------------------------------------------------------------------------

//login the student using Email   //DB
	@PostMapping("loginTheStudent")
	public SuccessResponse loginTheStudnet(@RequestBody Student student) {
		System.out.println("student " + student.getFirst_name());

		System.out.println("mbl " + student.getMobile_no());

		System.out.println("pass " + student.getPassword());

		return studentService.loginTheStudent(student);
	}

//-------------------------------------------------------------------------------------------------------------------------------
	// send the Otp //DB
	@PostMapping("/sendEmailForStudent")
	public SuccessResponse sendEmail(@RequestBody Mail info) {
		Mail mail = new Mail();

		SuccessResponse response = new SuccessResponse();
		Optional<Student> byEmail = studentRepo.findByEmail(info.getMailTo());
		if (byEmail.isPresent()) {
			int randomNumber = (int) (Math.random() * 900000) + 100000;
			mail.setMailFrom("infotechgenius42@gmail.com");
			mail.setMailTo(info.getMailTo());
			mail.setMailSubject("OTP for forget Password");
			mail.setMailContent("Hello Mail send Successfully. Your OTP is : " + randomNumber);
			mailService.sendEmail(mail);
			response.sendEmailSuccessfully(randomNumber);
			return response;
		} else {

			response.emailNotSend();
			return response;
		}
	}

//-----------------------------------------------------------------------------------------------------------------------
//	Save the new Password   //DB
	@PostMapping("saveTheNewPasswordForTheStudent")
	public SuccessResponse saveTheNewPasswordFotTheStudent(@RequestBody Student student) {
		return studentService.saveTheNewPasswordFotTheStudent(student);
	}

//------------------------------------------------------------------------------------------------------------------------------

//Get All student        //DB
	@GetMapping("getTheAllStudent")
	public SuccessResponse getTheAllStudent(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "creation_time", required = false) String sortBy,
			@RequestParam(value = "orderBy", defaultValue = "DESC", required = false) String orderBy) {
		return studentService.getTheAllStudent(pageNo, pageSize, sortBy, orderBy);
	}

//------------------------------------------------------------------------------------------------------------------------------	
//Get Student using The ID      //DB
	@GetMapping("getTheStudentUsingTheID/{id}")
	public SuccessResponse getTheStudentUsingTheID(@PathVariable Long id) {
		System.out.println("Student Id Is " + id);
		return studentService.getTheStudentUsingTheID(id);
	}

//----------------------------------------------------------------------------------------------------------------------------	
//Get the student using the Name       //DB
	@GetMapping("getTheStudentUsingTheName/{name}/{franhce_id}")
	public SuccessResponse getTheStudentUsingTheName(@PathVariable String name, @PathVariable Long franhce_id) {
		return studentService.getTheStudentUsingTheName(name, franhce_id);
	}

//-------------------------------------------------------------------------------------------------------------------------------	
//	get the  student using thefranchies ID      //DB
	@GetMapping("gettheStudentUsingFrnchiesID")
	public SuccessResponse getStudentsByFranchiseId(
			@RequestParam(value = "franchise_id", required = false) Long franchise_id,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "creation_time", required = false) String sortBy,
			@RequestParam(value = "orderBy", defaultValue = "ASC", required = false) String orderBy) {
		return studentService.getStudentsByFranchiseId(franchise_id, pageNo, pageSize, sortBy, orderBy);
	}

//----------------------------------------------------------------------------------------------------------------------

//	Get All student using the course ID    //DB
	@GetMapping("GetTheStudentUsingTheCourseId/{id}")
	public SuccessResponse getTheStudentUsingTheCourseID(@PathVariable Long id) {
		return studentService.getTheStudentUsingTheCourseID(id);
	}

//-----------------------------------------------------------------------------------------------------------------------

//	delete the student   //DB
	@DeleteMapping("deleteTheStudentUsingTheID/{id}")
	public SuccessResponse deleteTheStudentUsingTheId(@PathVariable Long id) {
		return studentService.deleteTheStudentUsingTheId(id);
	}

//-------------------------------------------------------------------------------------------------------------------	
//	Get The Practice Exam for Student
	@GetMapping("/getPracticeExamsForStudent/{studentId}/{courseId}")
	public SuccessResponse getPractiseTestsForStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
		System.out.println(studentId + "..." + courseId);
		return studentService.getPractiseExams(studentId, courseId);
	}

//---------------------------------------------------------------------------------------------------------------------	
//	Get The Final exam for student
	@GetMapping("/getExamForStudent/{studentId}/{courseId}")
	public SuccessResponse getFinalExamForStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
		System.out.println("student id ..............................: " + studentId);
		System.out.println("course id  ..............................:" + courseId);

		return studentService.getFinalExamForStudent(studentId, courseId);
	}
//------------------------------------------------------------------------------------------------------------------------

//Get The Student using franchies Id and course Id
	@GetMapping("GetTheStudentInfoByFranchiesIdAndCourseId/{franchies_id}/{course_id}")
	public SuccessResponse getTheStudentUsingByFranchiesIdAndCourseId(@PathVariable Long franchies_id,
			@PathVariable Long course_id) {
		return studentService.getTheStudentUsingByFranchiesIdAndCourseId(franchies_id, course_id);
	}

//--------------------------------------------------------------------------------------------------------------------------s	
	@GetMapping("/getAllStudentsCounts")
	public SuccessResponse getStudentsCount() {
		return studentService.getAllStudentCount();

	}

	@GetMapping("/getOnlineExamForStudent/{studentId}/{courseId}")
	public SuccessResponse getFinalExamForOnlineStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
		return studentService.getFinalExamForStudentInOnline(studentId, courseId);
	}

	// Endpoint to switch course for a student
	
	@PostMapping("assignTheAnotherCourseToStudent")
	public SuccessResponse switchCourse(@RequestParam Long courseId, @RequestParam Long studentId) {
		return studentService.switchCourse(courseId, studentId);
	}

}
