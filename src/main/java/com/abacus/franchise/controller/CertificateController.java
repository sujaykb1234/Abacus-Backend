package com.abacus.franchise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abacus.franchise.dto.CertificateDTO;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.CertificateService;

@RestController
@RequestMapping("abacus/v1/certificate")
public class CertificateController {

	@Autowired
	private CertificateService certificateService;

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
