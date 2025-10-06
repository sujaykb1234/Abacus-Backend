package com.abacus.franchise.serviceImpl;

import java.io.IOException;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abacus.franchise.model.SliderImage;
import com.abacus.franchise.model.StoredImages;
import com.abacus.franchise.repo.SliderRepository;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.S3BucketService;
import com.abacus.franchise.service.SliderService;

@Service
public class SliderServiceImple implements SliderService {

	@Autowired
	private SliderRepository sliderRepository;

	@Autowired
	private S3BucketService s3Storage;

	@Autowired
	ModelMapper modelMapper;

	SuccessResponse response = new SuccessResponse();

	@Override
	public SuccessResponse saveSliderImage(SliderImage sliderImage, MultipartFile sliderImageFile) {

		if (sliderImageFile == null || sliderImageFile.isEmpty()) {
			response.imageNotFound();
			return response;
		}
		StoredImages storedImage;
		try {
			 storedImage = s3Storage.storeFile(sliderImageFile.getOriginalFilename(), sliderImageFile.getInputStream(),
					sliderImageFile.getSize(), 1);

			sliderImage.setSlider_image_link(storedImage.getProfile_image_link());
			
			sliderImage.setSlider_image_name(storedImage.getProfile_image_name());
			
		} catch (IOException e) {
			e.printStackTrace();
			return response;
		}

		sliderRepository.save(sliderImage);
		response.imageSave(sliderImage);
		return response;
	}

	@Override
	public SuccessResponse getSliderImageById(Long sliderImageId) {
		Optional<SliderImage> optionalSliderImage = sliderRepository.findById(sliderImageId);
		if (!optionalSliderImage.isPresent()) {
			response.imageNotFound();
			return response;
		}
		SliderImage sliderImage = optionalSliderImage.get();
		response.imageFound(sliderImage);
		return response;
	}

	@Override
	public SuccessResponse getAllSliderImages() {
		List<SliderImage> images = sliderRepository.findAll();
		if (images.isEmpty()) {
			response.imageNotFound();
			return response;
		}

		response.imageFound(images);
		return response;
	}

	@Override
	public SuccessResponse deleteImages(Long sliderImageId) {
		// Check if the image exists
		Optional<SliderImage> optionalSliderImage = sliderRepository.findById(sliderImageId);
		if (optionalSliderImage.isEmpty()) {
			response.imageNotFound();
			return response;
		}

		// Retrieve the image entity
		SliderImage sliderImage = optionalSliderImage.get();

		// Delete the image from S3
		s3Storage.deleteFile(sliderImage.getSlider_image_link());

		// Delete the image record from the database
		sliderRepository.deleteById(sliderImageId);

		response.imageDelete(sliderImage);
		return response;
	}

}
