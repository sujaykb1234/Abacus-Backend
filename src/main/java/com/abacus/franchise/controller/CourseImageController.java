package com.abacus.franchise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.abacus.franchise.model.CourseImage;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.CourseImageService;

@RestController
@RequestMapping("abacus/v1/courseImage")
public class CourseImageController {

	@Autowired
	private CourseImageService service;

	@PostMapping("/saveImage")
	public SuccessResponse saveSliderImage(@ModelAttribute CourseImage sliderImage,
			@RequestParam("image") MultipartFile sliderImages) {
		return service.saveImage(sliderImage, sliderImages);
	}

	@GetMapping("/getImageById/{sliderImageId}")
	public SuccessResponse getSliderImagesById(@PathVariable Long sliderImageId) {
		return service.getImageById(sliderImageId);
	}

	@GetMapping("/getAllImages")
	public SuccessResponse getAllImages() {
		return service.getAllImages();
	}

	@GetMapping("/getPracticeQuestionPapersByCourseId/{courseId}")
	public SuccessResponse getAllImagesByCourseId(@PathVariable Long courseId) {
		return service.getAllImagesByCourseId(courseId);
	}

	@DeleteMapping("deletePracticePaperPdf/{pdfId}")
	public SuccessResponse deleteImage(@PathVariable Long pdfId) {
		return service.deleteImages(pdfId);
	}

}
