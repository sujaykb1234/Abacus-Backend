package com.abacus.franchise.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.UsersService;
import com.abacus.franchise.view.AuthRequest;
import com.abacus.franchise.view.KitRequest;

@RestController
@RequestMapping("abacus/v1/users/")
@CrossOrigin("*")
public class UsersController {
	
	@Autowired
	UsersService usersService;
	
	@PostMapping("login")
	public SuccessResponse loginUsers(@RequestBody AuthRequest authRequest) {
		return usersService.loginUsers(authRequest);
	}
	
	@GetMapping("/getUsersById/{userId}/{roleName}")
	public SuccessResponse getUsersById(
	        @PathVariable UUID userId,
	        @PathVariable String roleName) {

	    return usersService.getUsersById(userId, roleName);
	}
	
	@GetMapping("/getStudentByFranchiseId/{franchiseId}")
	public SuccessResponse getStudentByFranchiseId(@PathVariable UUID franchiseId) {
		return usersService.getStudentByFranchiseId(franchiseId);
	}

	@GetMapping("/getAllCoursesByFranchiseId/{franchiseId}")
	public SuccessResponse getAllCoursesByFranchiseId( @PathVariable UUID franchiseId) {
		return usersService.getAllCoursesByFranchiseId(franchiseId);
	}

	@PostMapping("/sendCourseKitRequest")
	public SuccessResponse sendCourseKitRequest(@RequestBody KitRequest kitRequest) {
		return usersService.sendCourseKitRequest(kitRequest);
	}




}
