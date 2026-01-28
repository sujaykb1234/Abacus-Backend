package com.abacus.franchise.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abacus.franchise.dto.QuestionsDTO;
import com.abacus.franchise.response.SuccessResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Service
public interface QuestionsService {

	SuccessResponse saveTextQuestion(QuestionsDTO questionsDTO);

	public SuccessResponse saveImageQuestion(QuestionsDTO questionsDTO, MultipartFile que_img,HttpServletRequest request);

	public SuccessResponse getQuestionByQueId(Long id);

	public SuccessResponse getQuestionByCourseId(Long id);

	SuccessResponse getRandomQuestions(Long courseId, int count, int imageQuestionPercentage);

	public SuccessResponse getRandomQuestions(Long courseId, int count);

	SuccessResponse updateQuestion(Long id, @Valid QuestionsDTO questionsDTO);

	SuccessResponse deleteQuestion(Long id);
	
	public SuccessResponse updateQuestion(Long id, QuestionsDTO questionsDTO, MultipartFile newImageFile,HttpServletRequest request);

}
