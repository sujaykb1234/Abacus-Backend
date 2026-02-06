package com.abacus.franchise.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import com.abacus.franchise.model.Franchise;
import com.abacus.franchise.model.FranchiseDiffrentAddKitReq;
import com.abacus.franchise.model.KitRequest;
import com.abacus.franchise.model.Mail;
import com.abacus.franchise.model.Student;
import com.abacus.franchise.repo.FranchiseRepo;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.FranchiseService;
import com.abacus.franchise.service.MailService;
import com.abacus.franchise.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("abacus/v1/franchise/")
public class FranchiseController {

	@Autowired
	FranchiseService franchiseService;

	@Autowired
	FranchiseRepo franchiseRepo;

	@Autowired
	MailService mailService;
	
	@Autowired
	StudentService studentService;
	
	@GetMapping("test")
	public String getMessage() {
		return "Welcome To Franchise";
	}


	@PostMapping("loginFranchies")
	public SuccessResponse loginTheFrinchise(@RequestBody Franchise franchise) {
		return franchiseService.loginTheFranchise(franchise);
	}
	
	
//	send Email(otp send)    //DB
	@PostMapping("/sendEmailForFranchies")
	public SuccessResponse sendEmail(@RequestBody Mail info) {
		Mail mail = new Mail();
		SuccessResponse response = new SuccessResponse();
		Optional<Franchise> byEmail = franchiseRepo.findByEmail(info.getMailTo());
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
	
//	get the franchies using the franchies ID   //DB
	@GetMapping("getTheFranchiesUsingTheID/{id}")
	public SuccessResponse getThefranchiesUsingTheID(@PathVariable Long id) {
		return franchiseService.getThefranchiesUsingTheID(id);
	}

//----------------------------------------------------------------------------------------------------------------------------	

//	get the All franchies    //DB
	@GetMapping("getTheAllFranchies")
	public SuccessResponse getTheAllFranchies(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "creation_time", required = false) String sortBy,
			@RequestParam(value = "orderBy", defaultValue = "DESC", required = false) String orderBy) {
		return franchiseService.getTheAllFranchies(pageNo, pageSize, sortBy, orderBy);
	}

//--------------------------------------------------------------------------------------------------------------

	@DeleteMapping("deleteTheFranchiesUsingTheID/{id}")
	public SuccessResponse deleteTheFranchiesUsingTheID(@PathVariable Long id) {
		return franchiseService.deleteTheFranchiesUsingTheID(id);
	}
//--------------------------------------------------------------------------------------------------------------------------------	

	@GetMapping("getAllFranchiseRequest")
	public SuccessResponse getAllFranchiseRequest() {
		return franchiseService.getAllFranchiseRequests();
	}

//----------------------------------------------------------------------------------------------------------------------------	

//	 @PostMapping("sendKitRequestByFranchise")
//	    public SuccessResponse sendKitRequestToAdmin(@RequestParam Long franchiseId, @RequestParam Long courseId) {
//	        return franchiseService.sendKitRequest(franchiseId, courseId);
//	    }

	@GetMapping("getAllFranchise")
	public SuccessResponse getAllFranchise() {
		return franchiseService.getTheAllFranchises();
	}

//---------------------------------------------------------------------------------------------------------------------------
	@PostMapping("/{franchiseId}/assign-courses")
	public SuccessResponse assignCoursesToFranchise(@PathVariable Long franchiseId, @RequestBody Set<Long> courseIds) {
		return franchiseService.assignCoursesToFranchise(franchiseId, courseIds);
	}

//-------------------------------------------------------------------------------------------------------------------------	
	@DeleteMapping("/{franchiseId}/remove-course/{courseId}")
	public SuccessResponse removeCourseFromFranchise(@PathVariable Long franchiseId, @PathVariable Long courseId) {
		return franchiseService.removeCourseFromFranchise(franchiseId, courseId);
	}

//-------------------------------------------------------------------------------------------------------------------------	

	@GetMapping("/courses/{franchiseId}")
	public SuccessResponse getCourseUsingFranchiseId(@PathVariable Long franchiseId) {
		return franchiseService.getCourseUsingFranchiseId(franchiseId);
	}

//------------------------------------------------------------------------------------------------------------------------ 	
	@GetMapping("/getkitsForFranchiseAndCourse")
	public SuccessResponse getKitCountForFranchise(@RequestParam Long franchiseId, Long courseId) {
		return franchiseService.getKitCountForFranchiseAndCourse(franchiseId, courseId);
	}

//----------------------------------------------------------------------------------------------------------------------------

	@PostMapping("sendFranchiesPasswordAndUserNameOnFranchiesEmail")
	public SuccessResponse sendFranchiesPasswordAndUserNameOnEmail(@RequestBody Mail info) {

		Mail mail = new Mail();
		SuccessResponse response = new SuccessResponse();

		Optional<Franchise> franchiseOptional = franchiseRepo.findByEmail(info.getMailTo());

		if (franchiseOptional.isPresent()) {
			Franchise franchise = franchiseOptional.get();
			franchise.getFranchise_password();
			franchise.getMobile_no();

			mail.setMailFrom("infotechgenius42@gmail.com");
			mail.setMailTo(info.getMailTo());
			mail.setMailSubject("Franchise Username and Password");
			mail.setMailContent("Dear Franchise,\n\n" + "Here are your login credentials:\n" + "Username: "
					+ franchise.getMobile_no() + "\n" + "Password: " + franchise.getFranchise_password() + "\n\n"
					+ "Please use these details to log in to your account.\n\n" + "Best regards,\nYour Team");

			mailService.sendEmail(mail);

			response.sendEmailSuccessfullyToFrnachies();
			return response;
		} else {
			response.emailNotSend();
			return response;
		}
	}

//-----------------------------------------------------------------------------------------------------------------------	

//	no
//	get the franchies reports using f id course id and Start date and end date 
	@GetMapping("getFranchiesReports")
	public SuccessResponse getTheFranchiesReports(@RequestParam(required = false) Long franchies_id,
			@RequestParam(required = false) Long course_id, @RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate) {
		return franchiseService.getTheFranchiesReports(franchies_id, course_id, startDate, endDate);
	}

//---------------------------------------------------------------------------------------------------------------------

//	get all student using date 
	@GetMapping("getAllStudentusingTheDate")
	public SuccessResponse getAllStudentUsingDate(@RequestParam String startDate, @RequestParam String endDate,
			@RequestParam(required = false) Long franchies_id) {

		return franchiseService.getAllStudentUsingDate(startDate, endDate, franchies_id);

	}

//--------------------------------------------------------------------------------------------------------------------------	

//	get all franchies 
	@GetMapping("getAllFrnachiesByDate")
	public SuccessResponse getAllFranchiesByDate(@RequestParam String startDate, @RequestParam String endDate) {
		return franchiseService.getAllFranchiesByDate(startDate, endDate);
	}

	@PostMapping("sendDiffrentAddressKitRequest")
	public SuccessResponse sendDiffrentAddressKitRequest(@RequestBody FranchiseDiffrentAddKitReq diffrentAddKitReq) {
		return franchiseService.sendDiffrentAddressKitRequest(diffrentAddKitReq);
	}

	@GetMapping("/getAllDiffrentAddressKitRequests")
	public SuccessResponse getAllDiffrentAddressKitReq() {
		return franchiseService.getAllDifrentAddressKitReq();
	}

	@PostMapping("/sendDiffrentAddressKitsToFranchise")
	public SuccessResponse sendDiffrentAddressKits(@RequestBody FranchiseDiffrentAddKitReq diffrentAddKitReq) {
		System.out.println("controller fr id and course id : " + diffrentAddKitReq.getCourseId() + ""
				+ diffrentAddKitReq.getFranchiseId());
		return franchiseService.sendDiffrentAddressKitByAdmin(diffrentAddKitReq);
	}

//	@GetMapping("getAllKitRequestUsingDate")
//	public SuccessResponse getAllKitRequestUsingDate(@RequestParam String startDate, @RequestParam String endDate,
//			@RequestParam(required = false) Long franchies_id) {
//		return franchiseService.getAllKitRequestsUsingDate(startDate, endDate, franchies_id);
//
//	}

	@PostMapping("sendMultipleKitRequestsByFranchise/{franchiseId}")
	public SuccessResponse sendKitRequestsToAdmin(@PathVariable Long franchiseId,
			@RequestBody List<KitRequest> kitRequests) {
		System.out.println("FranchiseId: " + franchiseId);
		System.out.println("KitRequests: " + kitRequests);
		return franchiseService.sendKitRequests(franchiseId, kitRequests);
	}

	@GetMapping("getAllKitRequestUsingDate")
	public SuccessResponse getAllKitRequestUsingDate(@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate, @RequestParam(required = false) Long franchiseId) {
		System.out.println("franchise id controller : " + franchiseId);
		return franchiseService.getAllKitRequestsUsingDate(startDate, endDate, franchiseId);
	}

//	@PostMapping("diffrentAddressStudentAddWithKitRequest")
//	public ResponseEntity<SuccessResponse> diffrentAddressStudentAddWithKitRequest(@ModelAttribute Student student,
//			@RequestParam(value = "studentPhoto", required = false) MultipartFile studentPhoto,
//			@RequestParam(value = "studentDocPhoto", required = false) MultipartFile studentDocPhoto
//			,HttpServletRequest request)
//			throws DataNotValidException, IOException {
//		//SuccessResponse response = franchiseService.addStudentWithKitRequestOnDiffrentAdd(student, studentPhoto,request); // studentDocPhoto
//		return ResponseEntity.ok();
//	}
	
	@GetMapping("getFranchiseByMobileNo")
	public SuccessResponse getFranchiseByMobileNo(@RequestParam String mobile) {
		 SuccessResponse response = new SuccessResponse();
		
		 List<Franchise> franchiseByMobileNo = franchiseRepo.getFranchiseByMobileNo(mobile);
		 
		 if(franchiseByMobileNo.isEmpty()) {
			 response.franchiesnotfound();
			 return response;
		 }
		 
		 response.getAllFranchies(franchiseByMobileNo);
		 return response;
	}


}
