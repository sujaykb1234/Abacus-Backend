package com.abacus.franchise.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.view.AuthRequest;
import com.abacus.franchise.view.KitRequest;
import com.abacus.franchise.view.ViewUser;

import jakarta.servlet.http.HttpServletRequest;

@Service
public interface UsersService {

	public SuccessResponse saveOrUpdateUsers(ViewUser viewUser,MultipartFile profileImage,MultipartFile documentImage,HttpServletRequest request);
	
	public SuccessResponse loginUsers(AuthRequest authRequest);

	public SuccessResponse getUsersById(UUID userId,String roleName);
	
	public SuccessResponse  getStudentByFranchiseId(UUID franchiseId);

	public SuccessResponse  getAllCoursesByFranchiseId(UUID franchiseId);
	
	public SuccessResponse  sendCourseKitRequest(KitRequest kitRequest);


}
