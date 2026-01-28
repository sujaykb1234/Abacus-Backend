package com.abacus.franchise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.abacus.franchise.dto.OfflineExamDTO;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.OfflineExamService;
import com.abacus.franchise.serviceImpl.OfflineExamServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("abacus/v1/offlineExam/")
public class OfflineExamController {

	@Autowired
	OfflineExamService offlineExamService;
	
	@Autowired
	OfflineExamServiceImpl offlineExamServiceImpl;

	
	@PostMapping("/saveOfflineExam")
	public SuccessResponse saveOfflineExam(@Valid @ModelAttribute OfflineExamDTO offlineExam,
			@RequestParam(value = "pdf", required = false) List<MultipartFile> pdf
			,HttpServletRequest request) {
				return offlineExamService.saveOfflineExams(offlineExam, pdf,request);
	}
	@GetMapping("/getAllTheOfflineExam")
	public SuccessResponse getAllTheOfflineExam() {
		return offlineExamService.getAllTheOfflineExam();
	}
	@GetMapping("/getOfflineExamById/{id}")
	public SuccessResponse getOfflineExamUsingTheId(@PathVariable Long id) {
		return offlineExamService.getOfflineExamUsingTheId(id);
	}
	@GetMapping("/getOfflineExamByStudentId/{id}")
	public SuccessResponse getOfflineExamUsingTheStudentId(@PathVariable Long id) {
		return offlineExamService.getOfflineExamUsingStudentId(id);
	}

	@DeleteMapping("/deleteOfflineExamById/{id}")
	public SuccessResponse deleteOfflineExamById(@PathVariable Long id) {
		return offlineExamService.deleteOfflineExamById(id);
	}
	@GetMapping("/setOfflineExamDawnloadStatus/{id}")
	public SuccessResponse setOfflineExamDawnloadStatus(@PathVariable Long id) {
		return offlineExamService.changeDawnloadStatus(id);
	}
}
