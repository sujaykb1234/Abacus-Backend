package com.abacus.franchise.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.abacus.franchise.model.SliderImage;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.SliderService;

@RestController
@RequestMapping("abacus/v1/sliderImage")
public class SliderController {

	@Autowired
	private SliderService sliderService;
	
	@PostMapping("/saveSliderImage")
	public SuccessResponse saveSliderImage(@ModelAttribute SliderImage sliderImage,
			@RequestParam("sliderImage") MultipartFile sliderImages) {
		return sliderService.saveSliderImage(sliderImage, sliderImages);
	}

	@GetMapping("/getSliderImageById/{sliderImageId}")
	public SuccessResponse getSliderImagesById(@PathVariable Long sliderImageId) {
		return sliderService.getSliderImageById(sliderImageId);
	}

	@GetMapping("/getAllSliderImages")
	public SuccessResponse getAllSliderImages() {
		return sliderService.getAllSliderImages();
	}

	@DeleteMapping("deleteImage/{sliderImageId}")
	public SuccessResponse deleteImage(@PathVariable Long sliderImageId) {
		return sliderService.deleteImages(sliderImageId);
	}


}
