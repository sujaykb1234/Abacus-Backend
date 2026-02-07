package com.abacus.franchise.controller;

//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.abacus.franchise.model.Users;
//import com.abacus.franchise.repo.UsersRepository;
//import com.abacus.franchise.response.SuccessResponse;
//import com.abacus.franchise.service.UsersService;
//import com.abacus.franchise.view.ViewUser;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//@RestController
//@RequestMapping("/users")
//@RequiredArgsConstructor
//public class UsersController {
//
//    private final UsersService _usersService;
//
//    public UsersController(UsersService usersService) {
//        _usersService = usersService;
//    }
//
//    @PostMapping("/saveOrUpdateUser")
//    public SuccessResponse saveOrUpdateUser(
//            @ModelAttribute ViewUser viewUser,
//            @RequestParam(required = false) MultipartFile profileImage,
//            @RequestParam(required = false) MultipartFile documentImage,
//            HttpServletRequest request) {
//
//        return usersService.saveOrUpdateUsers(
//                viewUser,
//                profileImage,
//                documentImage,
//                request
//        );
//    }
//
//
/// /    @GetMapping("getAll")
/// /    public List<Users> getAll() {
/// /        return usersRepository.getAllList();
/// /    }
//}

import com.abacus.franchise.model.Users;
import com.abacus.franchise.repo.UsersRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.UsersService;
import com.abacus.franchise.view.ViewUser;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final UsersRepository usersRepository;

    public UsersController(UsersService usersService, UsersRepository usersRepository) {
        this.usersService = usersService;
        this.usersRepository = usersRepository;
    }

    @PostMapping("/saveOrUpdateUser")
    public SuccessResponse saveOrUpdateUser(@ModelAttribute ViewUser viewUser,
                                            @RequestParam(required = false) MultipartFile profileImage,
                                            @RequestParam(required = false) MultipartFile documentImage,
                                            HttpServletRequest request) {
        return usersService.saveOrUpdateUsers(
                viewUser,
                profileImage,
                documentImage,
                request
        );
    }

    @GetMapping("getAll")
    public List<Users> getAll() {
        return usersRepository.getAllList();
    }
}

