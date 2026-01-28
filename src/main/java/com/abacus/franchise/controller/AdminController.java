 package com.abacus.franchise.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.abacus.franchise.dto.AdminProductDispachedDTO;
import com.abacus.franchise.model.Admin;
import com.abacus.franchise.model.Franchise;
import com.abacus.franchise.model.FranchiseKitRequest;
import com.abacus.franchise.model.Mail;
import com.abacus.franchise.model.Products;
import com.abacus.franchise.repo.AdminRepo;
import com.abacus.franchise.repo.OfflineExamUploadRepo;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.AdminService;
import com.abacus.franchise.service.FranchiseService;
import com.abacus.franchise.service.MailService;
import com.abacus.franchise.utility.FranchiseStatus;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("abacus/v1/Admin/")
public class AdminController {

	@Autowired
	AdminService adminService;

	@Autowired
	AdminRepo adminRepo;

	@Autowired
	MailService mailService;
	
	@Autowired
	OfflineExamUploadRepo examUploadRepo;
	
	@Autowired
	FranchiseService franchiseService;
//-----------------------------------------------------------------------------------------------------	
    @GetMapping("/test")
	public String getMessage() {
    	return "Welcome to Admin";
    }
	
//	Login the admin //DB
	@PostMapping("loginTheAdmin")
	public SuccessResponse loginTheAdmin(@ModelAttribute Admin admin) {
		return adminService.loginTheAdmin(admin);
	}

//--------------------------------------------------------------------------------------------------------------------------	
//	send Email(otp send)  //DB
	@PostMapping("/sendEmailForAdmin")
	public SuccessResponse sendEmail(@RequestBody Mail info) {
		Mail mail = new Mail();
		SuccessResponse response = new SuccessResponse();
		Optional<Admin> byEmail = adminRepo.findByEmail(info.getMailTo());
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

//-------------------------------------------------------------------------------------------------------------------------------	
//Set new forget Password  //DB

	@PostMapping("setTheNewPasswordForAdmin")
	public SuccessResponse setTheNewPassword(@RequestBody Admin admin) {
		return adminService.setTheNewPassword(admin);
	}

//-------------------------------------------------------------------------------------------------------------------------------
	@PostMapping("/acceptOrRejectFranchiseRequest") // SJ
	public SuccessResponse acceptOrRejectFranchiseRequest(@RequestParam Long franchiseId,
			@RequestParam FranchiseStatus franchiseStatus) {
		return adminService.acceptOrRejectRequest(franchiseId, franchiseStatus);
	}

//------------------------------------------------------------------------------------------------------------------------------	
	@GetMapping("/getAllKitRequests")
	public SuccessResponse getAllKitRequests() {
		return adminService.getKitRequests();
	}

	@PostMapping("/sendKitsToFranchise")
	public SuccessResponse sendKitsToFranchise(@RequestBody FranchiseKitRequest franchiseKitRequest) {
		return adminService.sendKitsToFranch(franchiseKitRequest);
	}

	@GetMapping("/getAll-Re-attempt-request")
	public SuccessResponse getAllReattemptRequests() {
		return adminService.getAllReattemptRequests();
	}

	@GetMapping("/getExamCompletedStudentByFranchise/{franchiseId}")
	public SuccessResponse getAllExamAttendedStudent(@PathVariable Long franchiseId) {
		return adminService.getExamCompletedStudents(franchiseId);
	}

	@PostMapping("/uploadQuestionPaperPDF")
	public SuccessResponse uploadExamPeperForCourse(@RequestParam Long courseId,
			@RequestParam MultipartFile questionPaper,HttpServletRequest request) {
		return adminService.uploadOfflineExamPDF(courseId, questionPaper,request);
	}

	@GetMapping("/getPaperPDFByCourse/{courseId}")
	public SuccessResponse getOfflineExamPdtByCourse(@PathVariable Long courseId) {
		return adminService.getOfflineExamPdf(courseId);
	}

	@DeleteMapping("/deleteQuestionPaper/{paperId}")
	public SuccessResponse deleteOfflineQuestionPaper(@PathVariable Long paperId) {
		return adminService.deleteFinalPaper(paperId);
	}

	@GetMapping("/getAllStudentsForAdmin")
	public SuccessResponse getAllStudentsForAdmin() {
		return adminService.getAllStudentsForAdmin();
	}
	
	@PostMapping("/saveOrUpdateProduct")
	public SuccessResponse saveOrUpdateProduct(@RequestBody Products products) {
	    return adminService.saveOrUpdateProduct(products);
	}


	// Delete Product API
	@DeleteMapping("/deleteProduct/{id}")
	public SuccessResponse deleteProduct(@PathVariable Long id) {
		return adminService.deleteProduct(id);
	}

	// Get All Products API
	@GetMapping("/getAllProducts")
	public SuccessResponse getAllProducts() {
		return adminService.getAllProducts();
	}
	
	@PostMapping("/dispatchProducts")
	public SuccessResponse dispatchProducts(@RequestBody AdminProductDispachedDTO dispatchDTO) {
	    return adminService.dispatchProducts(dispatchDTO); 
	}
	
	
	@GetMapping("getOfflineExamPdfByCourseId")
	public SuccessResponse getOfflineExamPdfByCourseId(@RequestParam Long courseId) {
		
		SuccessResponse response = new SuccessResponse();
		
		Optional<String> offlineExamPdfByCourseId = examUploadRepo.getOfflineExamPdfByCourseId(courseId);
		
		if(offlineExamPdfByCourseId.isEmpty()) {
			response.setMessage("PDF NOT FOUND");
			response.setStatus(false);
			response.setStatusCode(HttpStatus.NOT_FOUND );
			response.setResponse(null);
			return response;
		}
		
		response.setMessage("PDF FOUND SUCCESSFULLY");
		response.setStatus(true);
		response.setStatusCode(HttpStatus.FOUND );
		response.setResponse(offlineExamPdfByCourseId);
		return response;
		
	}
	
	
	  @GetMapping("/view/{filename}")
	    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
	        try {
	            Path filePath = Paths.get("C:/Images/").resolve(filename).normalize();
	            Resource resource = new UrlResource(filePath.toUri());

	            if (!resource.exists()) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	            }

	            String contentType = Files.probeContentType(filePath);
	            if (contentType == null) {
	                contentType = "application/octet-stream";
	            }

	            return ResponseEntity.ok()
	                    .contentType(MediaType.parseMediaType(contentType))
	                    .body(resource);

	        } catch (IOException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
	  
		@PostMapping("setTheNewpasswordForFranchies")
		public SuccessResponse setTheNewPasswordForFranchies(@RequestBody Franchise franchise) {
			return franchiseService.setTheNewPasswordForFranchies(franchise);
		}
	
}
