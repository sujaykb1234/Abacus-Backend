package com.abacus.franchise.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abacus.franchise.model.SliderImage;
import com.abacus.franchise.response.SuccessResponse;


@Service
public interface SliderService {
	
	public SuccessResponse saveSliderImage(SliderImage sliderImage, MultipartFile listImages);
	
	public SuccessResponse getAllSliderImages();

	public SuccessResponse deleteImages(Long sliderImageId);

	public SuccessResponse getSliderImageById(Long sliderImageId);

}
