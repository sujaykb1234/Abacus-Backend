package com.abacus.franchise.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abacus.franchise.model.CourseImage;
import com.abacus.franchise.model.StoredImages;
import com.abacus.franchise.repo.CourseImageRepository;
import com.abacus.franchise.repo.CourseRepo;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.CourseImageService;
import com.abacus.franchise.utility.ImageStoreProcess;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CourseImageServiceImple implements CourseImageService {

	@Autowired
	private CourseImageRepository sliderRepository;

	
	@Autowired
	private CourseRepo courseRepo;
	
//	@Autowired
//	private S3BucketService s3Storage;

	@Autowired
	ModelMapper modelMapper;

	SuccessResponse response = new SuccessResponse();

	@Override
	public SuccessResponse saveImage(CourseImage sliderImage, MultipartFile imageFile,HttpServletRequest request) {

		if (imageFile == null || imageFile.isEmpty()) {
			response.imageNotFound();
			return response;
		}
		if (sliderImage.getCourse_id() == null) {
			response.idNotFound();
			return response;
		}
//		Optional<Course> byId = courseRepo.findById(sliderImage.getCourse_id());
//		if (!byId.isPresent()) {
//			response.idNotFound();
//			return response;
//		}
		
		StoredImages storedImage;
//		try {
//			 storedImage = s3Storage.storeFile(imageFile.getOriginalFilename(), imageFile.getInputStream(),
//					 imageFile.getSize(), 7);
//
//			sliderImage.setImage_link(storedImage.getPdfLink());
//			sliderImage.setImage_name(storedImage.getPdfName());
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//			return response;
//		}
		
		if(!imageFile.isEmpty()) {
			List<String> saveFile = ImageStoreProcess.saveFile(imageFile,request);
			
			if(saveFile != null) {
				sliderImage.setImage_link(saveFile.get(1));
				sliderImage.setImage_name(saveFile.get(0));
			}
		}

		sliderRepository.save(sliderImage);
		response.imageSave(sliderImage);
		return response;
	}

	@Override
	public SuccessResponse getImageById(Long sliderImageId) {
		Optional<CourseImage> optionalSliderImage = sliderRepository.findById(sliderImageId);
		if (!optionalSliderImage.isPresent()) {
			response.imageNotFound();
			return response;
		}
		CourseImage sliderImage = optionalSliderImage.get();
		response.imageFound(sliderImage);
		return response;
	}

	@Override
	public SuccessResponse getAllImages() {
		List<CourseImage> images = sliderRepository.findAll();
		if (images.isEmpty()) {
			response.imageNotFound();
			return response;
		}

		response.imageFound(images);
		return response;
	}

	@Override
	public SuccessResponse getAllImagesByCourseId(Long course_id) {
		List<CourseImage> images = sliderRepository.findByCourseId(course_id);
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
		Optional<CourseImage> optionalSliderImage = sliderRepository.findById(sliderImageId);
		if (optionalSliderImage.isEmpty()) {
			response.imageNotFound();
			return response;
		}
		CourseImage sliderImage = optionalSliderImage.get();
//		s3Storage.deleteFile(sliderImage.getImage_link());
		ImageStoreProcess.deleteFile(sliderImage.getImage_link(), sliderImage.getImage_name());
		sliderRepository.deleteById(sliderImageId);
		response.imageDelete(sliderImage);
		return response;
	}

}
