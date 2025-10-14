package com.abacus.franchise.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abacus.franchise.model.CourseImage;
import com.abacus.franchise.response.SuccessResponse;


@Service
public interface CourseImageService {
	
	public SuccessResponse saveImage(CourseImage courseImage, MultipartFile listImages);
	
	public SuccessResponse getAllImages();

	public SuccessResponse deleteImages(Long imageId);

	public SuccessResponse getImageById(Long imageId);

	SuccessResponse getAllImagesByCourseId(Long course_id);

}
