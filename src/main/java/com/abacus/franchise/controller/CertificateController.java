package com.abacus.franchise.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abacus.franchise.dto.CertificateDTO;
import com.abacus.franchise.model.Certificate;
import com.abacus.franchise.model.CourseImage;
import com.abacus.franchise.model.Franchise;
import com.abacus.franchise.model.OfflineExamUpload;
import com.abacus.franchise.model.SliderImage;
import com.abacus.franchise.model.StoredImages;
import com.abacus.franchise.model.Student;
import com.abacus.franchise.repo.CertificateRepo;
import com.abacus.franchise.repo.CourseImageRepository;
import com.abacus.franchise.repo.FranchiseRepo;
import com.abacus.franchise.repo.OfflineExamUploadRepo;
import com.abacus.franchise.repo.SliderRepository;
import com.abacus.franchise.repo.StoredImagesRepo;
import com.abacus.franchise.repo.StudentRepo;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.CertificateService;
import com.abacus.franchise.utility.ImageDownloader;

@RestController
@RequestMapping("abacus/v1/certificate")
public class CertificateController {

	@Autowired
	private CertificateService certificateService;
	
//	@Autowired
//	StudentRepo studentRepo;
//	StoredImagesRepo imagesRepo;
	
//	SliderRepository repository;
	
//	OfflineExamUploadRepo examUploadRepo;
	
//	FranchiseRepo franchiseRepo;
	
//    CourseImageRepository courseImageRepository;
	
	//	CertificateRepo certificateRepo;
	
//	@GetMapping("GetData")
//	public void getData() throws Exception {
//		
//		List<Student> all = studentRepo.findAll();
//		
//		for(Student certificate: all) {
			
//			if(certificate.getId_proof_image_link() != null) {
//			  ImageDownloader.downloadFile(certificate.getId_proof_image_link(), "C:/my-images/StoredImages/Id");
//			}
			
//			if(certificate.getProfile_image_link() != null) {
//				ImageDownloader.downloadFile(certificate.getProfile_image_link(), "C:/my-images/StudentProfile");
//			}
//			
//			if(certificate.getProfile_image_link() != null) {
//				ImageDownloader.downloadFile(certificate.getProfile_image_link(), "C:/my-images/StoredImages/Profile");
//			}
//		}
//	}
//	

	@PostMapping("/generateCertificate")
	public SuccessResponse saveCertificate(@RequestBody CertificateDTO certificate) {
		return certificateService.saveCertificate(certificate);
	}

	@GetMapping("/getAllCertificate")
	public SuccessResponse getAllCertificates() {
		return certificateService.getAllCertificate();
	}

	@GetMapping("/getCertificateById/{id}")
	public SuccessResponse getCertificateById(@PathVariable Long id) {
		System.out.println("getCertificateById ");
		return certificateService.getCertificateById(id);
	}

	@DeleteMapping("/deleteCertificateById/{id}")
	public SuccessResponse deleteCertificate(@PathVariable Long id) {
		return certificateService.deleteCertificateById(id);
	}

	@GetMapping("/getCertificate/{studentId}/{courseId}")
	public SuccessResponse getCertificateByStudentId(@PathVariable Long studentId, @PathVariable Long courseId) {
		System.out.println("sdfghgfdsdfghgfdfghdfghgfdhfdhejhfdghdh");
		return certificateService.findByStudentId(studentId, courseId);
	}

}
