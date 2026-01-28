package com.abacus.franchise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.abacus.franchise.model.Franchise;
import com.abacus.franchise.model.Student;
import com.abacus.franchise.model.Users;
import com.abacus.franchise.repo.UsersRepository;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.FranchiseService;
import com.abacus.franchise.service.StudentService;
import com.abacus.franchise.service.UsersService;
import com.abacus.franchise.view.ViewUser;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("abacus/v1/auth/")
public class AuthController {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	UsersRepository usersRepository;
	
	@PostMapping("saveOrUpdateUser")
	public SuccessResponse saveOrUpdateUsers(@ModelAttribute ViewUser viewUser,
			@RequestParam(required = false) MultipartFile profileImage,
			@RequestParam(required = false) MultipartFile documentImage,
			HttpServletRequest request) {
		return usersService.saveOrUpdateUsers(viewUser,profileImage,documentImage,request);
	}
	
	@GetMapping("getAll")
	public List<Users> getAll() {
		return usersRepository.getAllList();
	}

	
//	@PostMapping("registerAndUpdateFranchies")
//	public SuccessResponse registerAndUpdateTheFrinchise(@ModelAttribute Franchise franchise,
//			@RequestParam(value = "franchies_owner_pic", required = false) MultipartFile franchies_owner_pic,
//			@RequestParam(value = "franchies_owner_id_photo", required = false) MultipartFile franchies_owner_id_photo
//			,HttpServletRequest request) {
//		return franchiseService.registerAndUpdateTheFrinchise(franchise, franchies_owner_pic, franchies_owner_id_photo,request);
//	}
	


	
	


}
