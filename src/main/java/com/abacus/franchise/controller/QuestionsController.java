package com.abacus.franchise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.abacus.franchise.dto.QuestionsDTO;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.QuestionsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("abacus/v1/questions")
@Validated
public class QuestionsController {

	@Autowired
	QuestionsService questionsService;

	@GetMapping("test")
	public String testApi() {
		return "Abacus Project Working ....!";
	}

//-------------------------------------------------------------------------------------------------------------------	

	@PostMapping("saveQuestions")
	public SuccessResponse saveQuestions(@Valid @RequestBody QuestionsDTO questionsDTO) {
		return questionsService.saveTextQuestion(questionsDTO);
	}

	@PostMapping("/saveImageQue")
	public SuccessResponse saveImageQuestion(@ModelAttribute QuestionsDTO questionsDTO,
			@RequestParam("que_img") MultipartFile que_img) {
		return questionsService.saveImageQuestion(questionsDTO, que_img);
	}

//----------------------------------------------------------------------------------------------------------------------	
	@GetMapping("getQuestionByQueId/{id}")
	public SuccessResponse getQuestionById(@PathVariable Long id) {
		return questionsService.getQuestionByQueId(id);
	}

//-----------------------------------------------------------------------------------------------------------------------	
	@GetMapping("getQuestionByCourseId/{id}")
	public SuccessResponse getQueByCourseId(@PathVariable Long id) {
		return questionsService.getQuestionByCourseId(id);
	}
	
//-------------------------------------------------------------------------------------------------------------------------	
	@GetMapping("randomquestions")
	public SuccessResponse getRandomQuestions(@RequestParam Long courseId, @RequestParam int count) {
		System.out.println("course id : " + courseId + " count : " + count);
		return questionsService.getRandomQuestions(courseId, count);
	}

	@PostMapping("updateQuestion/{id}")
	public SuccessResponse updateQuestion(@PathVariable Long id, @Valid @RequestBody QuestionsDTO questionsDTO) {
		return questionsService.updateQuestion(id, questionsDTO);
	}

	@DeleteMapping("deleteQuestion/{id}")
	public SuccessResponse deleteQuestion(@PathVariable Long id) {
		return questionsService.deleteQuestion(id);
	}

	@PostMapping("updateImageQuestion/{id}")
	public SuccessResponse updateQuestion(@PathVariable Long id, @Valid @RequestBody QuestionsDTO questionsDTO,
			@RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
		return questionsService.updateQuestion(id, questionsDTO, imageFile);
	}

}
