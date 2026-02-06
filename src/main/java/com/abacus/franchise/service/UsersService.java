package com.abacus.franchise.service;

import org.springframework.web.multipart.MultipartFile;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.view.AuthRequest;
import com.abacus.franchise.view.ViewUser;
import jakarta.servlet.http.HttpServletRequest;

public interface UsersService {

    SuccessResponse saveOrUpdateUsers(
            ViewUser viewUser,
            MultipartFile profileImage,
            MultipartFile documentImage,
            HttpServletRequest request
    );

    SuccessResponse loginUsers(AuthRequest authRequest);
}
