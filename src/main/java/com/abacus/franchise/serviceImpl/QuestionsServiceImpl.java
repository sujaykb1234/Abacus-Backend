package com.abacus.franchise.serviceImpl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abacus.franchise.dto.CourseResponseDTO;
import com.abacus.franchise.dto.ExamResponse;
import com.abacus.franchise.dto.QuestionsDTO;
import com.abacus.franchise.model.Questions;
import com.abacus.franchise.model.StoredImages;
import com.abacus.franchise.repo.CourseRepo;
import com.abacus.franchise.repo.QuestionsRepo;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.QuestionsService;
//import com.abacus.franchise.service.S3BucketService;
import com.abacus.franchise.utility.ImageStoreProcess;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class QuestionsServiceImpl implements QuestionsService {

	@Autowired
	QuestionsRepo questionsRepo;

	@Autowired
	CourseRepo courseRepo;

	@Autowired
	ModelMapper mapper;

//	@Autowired
//	S3BucketService s3BucketService;

	SuccessResponse response = new SuccessResponse();

	@Override
	public SuccessResponse saveTextQuestion(QuestionsDTO questionsDTO) {
		SuccessResponse response = new SuccessResponse();

		if (questionsDTO.getQue() == null || questionsDTO.getCorrect_answer() == null
				|| questionsDTO.getOptions() == null) {
			response.nullData();
			return response;
		}

		if (questionsDTO.getQue_id() == null) {
			// Creating a new question
			Questions question = mapper.map(questionsDTO, Questions.class);
			question.setQuestion_type(questionsDTO.getQuestion_type());
			question.setIsImageQuestion(false);
			questionsRepo.save(question);
			response.saveQuestions(question);
		} else {
			// Updating an existing question
			Optional<Questions> existingQuestionOpt = questionsRepo.findById(questionsDTO.getQue_id());
			if (existingQuestionOpt.isPresent()) {
				Questions existingQuestion = existingQuestionOpt.get();
				existingQuestion.setQue(questionsDTO.getQue());
				existingQuestion.setOptions(questionsDTO.getOptions());
				existingQuestion.setCorrect_answer(questionsDTO.getCorrect_answer());
				existingQuestion.setQuestion_type(questionsDTO.getQuestion_type());
				existingQuestion.setIsImageQuestion(false);
				questionsRepo.save(existingQuestion);
				response.updateQuestions(existingQuestion);
			} else {
				response.questionsNotFound();
			}
		}
		return response;
	}

	@Override
	public SuccessResponse getQuestionByQueId(Long id) {
		if (id == null) {
			response.nullData();
		}
		if (id != null) {
			Optional<Questions> byId = questionsRepo.findByQue_Id(id);
			if (byId.isPresent()) {
				Questions questions = byId.get();
				response.questionsFound(questions);
			} else {
				response.questionsNotFound();
			}
		}
		return response;
	}

	@Override
	public SuccessResponse getQuestionByCourseId(Long id) {
		SuccessResponse response = new SuccessResponse();
		if (id == null) {
			response.nullData();
			return response;
		}
		List<Questions> questionsList = questionsRepo.findByCourse_Id(id);
		if (questionsList.isEmpty()) {
			response.questionsNotFound();
			return response;
		}
		List<ExamResponse> examResponseList = questionsList.stream().map(question -> {
			ExamResponse examResponse = new ExamResponse();
			examResponse.setId(question.getQue_id());
			examResponse.setStudentId(null); // Set this based on your requirement
			examResponse.setCourseId(String.valueOf(id));
			if ("TEXT".equalsIgnoreCase(question.getQuestion_type()) || "NUMBER".equals(question.getQuestion_type())) {
				examResponse.setQuestionName(question.getQue());
				examResponse.setCorrectOptions(question.getCorrect_answer());
			} else if ("IMAGE".equalsIgnoreCase(question.getQuestion_type())) {
				examResponse.setQuestionImageLink(question.getQuestion_link());
				examResponse.setCorrectOptions(question.getCorrect_answer());
			}
			examResponse.setOptions(question.getOptions());
			return examResponse;
		}).collect(Collectors.toList());

		// Add the ExamResponse list to the SuccessResponse
		response.questionsFound(examResponseList);
		return response;
	}



	@Override
	public SuccessResponse getRandomQuestions(Long courseId, int count) { // for the practice exam
		SuccessResponse response = new SuccessResponse();
		if (courseId == null || count <= 0) {
			response.nullData();
			return response;
		}
		Integer textCount = (int) Math.ceil(count * 0.8);
		Integer imageCount = count - textCount;
		System.out.println("Text count :" + textCount);
		System.out.println("Image count :" + imageCount);
		List<Questions> textQuestions = questionsRepo.findRandomTextQuestions(courseId, textCount);
		List<Questions> imageQuestions = questionsRepo.findRandomImageQuestions(courseId, imageCount);
		System.out.println("text questions size :" + textQuestions.size());
		System.out.println("Image questions size :" + imageQuestions.size());
		if (imageQuestions.size() < imageCount) {
			int additionalTextCount = imageCount - imageQuestions.size();
			textCount += additionalTextCount;
			List<Questions> additionalTextQuestions = questionsRepo.findRandomTextQuestions(courseId,
					additionalTextCount);
			textQuestions.addAll(additionalTextQuestions); // Combine with the existing text questions
			System.out.println("Additional questions size : " + additionalTextQuestions.size());
		}
		List<QuestionsDTO> textQuestionsDTOList = textQuestions.stream().map(question -> {
			QuestionsDTO questionsDTO = mapper.map(question, QuestionsDTO.class);
			CourseResponseDTO courseDTO = new CourseResponseDTO(question.getCourse().getCourse_id(),
					question.getCourse().getCourse_name());
			questionsDTO.setCourse(courseDTO);
			return questionsDTO;
		}).collect(Collectors.toList());
		List<QuestionsDTO> imageQuestionsDTOList = imageQuestions.stream().map(question -> {
			QuestionsDTO questionsDTO = mapper.map(question, QuestionsDTO.class);
			CourseResponseDTO courseDTO = new CourseResponseDTO(question.getCourse().getCourse_id(),
					question.getCourse().getCourse_name());
			questionsDTO.setCourse(courseDTO);
			return questionsDTO;
		}).collect(Collectors.toList());
		List<QuestionsDTO> combinedQuestionsList = new ArrayList<>();
		combinedQuestionsList.addAll(textQuestionsDTOList);
		combinedQuestionsList.addAll(imageQuestionsDTOList);
		Collections.shuffle(combinedQuestionsList);
		if (combinedQuestionsList.isEmpty()) {
			response.questionsNotFound();
		} else {
			response.questionsFound(combinedQuestionsList);
		}
		return response;
	}

	@Override
	public SuccessResponse getRandomQuestions(Long courseId, int count, int imageQuestionPercentage) { // for the main
																										// exam
		SuccessResponse response = new SuccessResponse();
		if (courseId == null || count <= 0 || imageQuestionPercentage < 0 || imageQuestionPercentage > 100) {
			response.nullData();
			return response;
		}
		int imageCount = (int) Math.ceil(count * (imageQuestionPercentage / 100.0));
		System.out.println("IMAGE questions count" + imageCount);
		int textCount = count - imageCount;
		System.out.println("Text qurstions" + textCount);
		List<Questions> textQuestions = questionsRepo.findRandomTextQuestions(courseId, textCount);
		System.out.println("Text questions size : " + textQuestions.size());
		List<Questions> imageQuestions = questionsRepo.findRandomImageQuestions(courseId, imageCount);
		System.out.println("Image questions size : " + imageQuestions.size());
		List<QuestionsDTO> textQuestionsDTOList = textQuestions.stream().map(question -> {
			QuestionsDTO questionsDTO = mapper.map(question, QuestionsDTO.class);
			CourseResponseDTO courseDTO = new CourseResponseDTO(question.getCourse().getCourse_id(),
					question.getCourse().getCourse_name());
			questionsDTO.setCourse(courseDTO);
			return questionsDTO;
		}).collect(Collectors.toList());

		// Convert image-based questions to DTOs
		List<QuestionsDTO> imageQuestionsDTOList = imageQuestions.stream().map(question -> {
			QuestionsDTO questionsDTO = mapper.map(question, QuestionsDTO.class);
			CourseResponseDTO courseDTO = new CourseResponseDTO(question.getCourse().getCourse_id(),
					question.getCourse().getCourse_name());
			questionsDTO.setCourse(courseDTO);
			return questionsDTO;
		}).collect(Collectors.toList());

		// Combine and shuffle the questions
		List<QuestionsDTO> combinedQuestionsList = new ArrayList<>();
		combinedQuestionsList.addAll(textQuestionsDTOList);
		combinedQuestionsList.addAll(imageQuestionsDTOList);
		Collections.shuffle(combinedQuestionsList);

		// Return the result in the response
		if (combinedQuestionsList.isEmpty()) {
			response.questionsNotFound();
		} else {
			response.questionsFound(combinedQuestionsList);
		}

		return response;
	}

	@Override
	public SuccessResponse saveImageQuestion(QuestionsDTO questionsDTO, MultipartFile que_img,HttpServletRequest request) {
		SuccessResponse response = new SuccessResponse();

		if (questionsDTO.getCorrect_answer() == null || questionsDTO.getOptions() == null || que_img == null) {
			response.nullData();
			return response;
		}
		try {
//			StoredImages storedImage = s3BucketService.storeFile(que_img.getOriginalFilename(),
//					que_img.getInputStream(), que_img.getSize(), 5);

			List<String> saveFile = ImageStoreProcess.saveFile(que_img, request);
			
			if (questionsDTO.getQue_id() == null) {
				// Creating a new image question
				Questions question = mapper.map(questionsDTO, Questions.class);
				question.setQuestion_type("IMAGE");
				question.setIsImageQuestion(true);
				if(saveFile != null) {
					question.setQuestion_name(saveFile.get(0));
					question.setQuestion_link(saveFile.get(1));
				}
				questionsRepo.save(question);
				response.saveQuestions(question);
			} else {
				Optional<Questions> existingQuestionOpt = questionsRepo.findById(questionsDTO.getQue_id());
				if (existingQuestionOpt.isPresent()) {
					Questions existingQuestion = existingQuestionOpt.get();
					existingQuestion.setQue(questionsDTO.getQue());
					existingQuestion.setOptions(questionsDTO.getOptions());
					existingQuestion.setCorrect_answer(questionsDTO.getCorrect_answer());
					existingQuestion.setQuestion_type("IMAGE");
					existingQuestion.setIsImageQuestion(true);
					if(saveFile != null) {
						existingQuestion.setQuestion_name(saveFile.get(0));
						existingQuestion.setQuestion_link(saveFile.get(1));
					}
					questionsRepo.save(existingQuestion);
					response.updateQuestions(existingQuestion);
				} else {
					response.questionsNotFound();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.ExceptionSMS(response);
		}

		return response;
	}

	// Add in QuestionsServiceImpl

	@Override
	public SuccessResponse updateQuestion(Long id, QuestionsDTO questionsDTO) {
		SuccessResponse response = new SuccessResponse();
		System.out.println("question id : " + questionsDTO.getQue_id());

		Optional<Questions> existingQuestionOpt = questionsRepo.findById(id);

		if (existingQuestionOpt.isPresent()) {
			Questions existingQuestion = existingQuestionOpt.get();
			existingQuestion.setQue(questionsDTO.getQue());
			existingQuestion.setOptions(questionsDTO.getOptions());
			existingQuestion.setCorrect_answer(questionsDTO.getCorrect_answer());
			existingQuestion.setIsImageQuestion(questionsDTO.getIsImageQuestion());

			questionsRepo.save(existingQuestion);
			response.updateQuestions(existingQuestion);
		} else {
			response.questionsNotFound();
		}
		return response;
	}

	@Override
	public SuccessResponse deleteQuestion(Long id) {
		SuccessResponse response = new SuccessResponse();
		Optional<Questions> questionOpt = questionsRepo.findById(id);

		if (questionOpt.isPresent()) {
				questionsRepo.deleteQuestionsById(id);
				response.deleteSuccess();
			
		} else {
			response.questionsNotFound();
		}
		return response;
	}

	@Override
	public SuccessResponse updateQuestion(Long id, QuestionsDTO questionsDTO, MultipartFile newImageFile,HttpServletRequest request) {
		SuccessResponse response = new SuccessResponse();
		Optional<Questions> existingQuestionOpt = questionsRepo.findById(id);

		if (existingQuestionOpt.isPresent()) {
			Questions existingQuestion = existingQuestionOpt.get();
			existingQuestion.setQue(questionsDTO.getQue());
			existingQuestion.setOptions(questionsDTO.getOptions());
			existingQuestion.setCorrect_answer(questionsDTO.getCorrect_answer());
			existingQuestion.setQuestion_type(questionsDTO.getQuestion_type());
			existingQuestion.setIsImageQuestion(questionsDTO.getIsImageQuestion());

			if (existingQuestion.getIsImageQuestion() && newImageFile != null && !newImageFile.isEmpty()) {
				if (existingQuestion.getQuestion_link() != null) {
//					s3BucketService.deleteFile(existingQuestion.getQuestion_link());
                    ImageStoreProcess.deleteFile(existingQuestion.getQuestion_link(),existingQuestion.getQuestion_name());
				}

				try (InputStream imageInputStream = newImageFile.getInputStream()) {
//					StoredImages storedImage = s3BucketService.storeFile(newImageFile.getOriginalFilename(),
//							imageInputStream, newImageFile.getSize(), 1 // Assuming recordId 1 is used for questions
//					);
					
					List<String> saveFile = ImageStoreProcess.saveFile(newImageFile, request);
					if(saveFile != null) {
						existingQuestion.setQuestion_name(saveFile.get(0));
						existingQuestion.setQuestion_link(saveFile.get(1));
					}
				} catch (Exception e) {
					response.setMessage("Error updating image for question.");
					return response;
				}
			}

			questionsRepo.save(existingQuestion);
			response.updateQuestions(existingQuestion);
		} else {
			response.questionsNotFound();
		}

		return response;
	}

}
