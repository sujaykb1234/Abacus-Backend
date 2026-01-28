package com.abacus.franchise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.UsersService;
import com.abacus.franchise.view.AuthRequest;

@RestController
@RequestMapping("abacus/users/")
@CrossOrigin("*")
public class UsersController {
	
	@Autowired
	UsersService usersService;
	
	@PostMapping("login")
	public SuccessResponse loginUsers(@RequestBody AuthRequest authRequest) {
		return usersService.loginUsers(authRequest);
	}

}
