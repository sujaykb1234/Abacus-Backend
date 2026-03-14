package com.abacus.franchise.interfaces;

import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

import com.abacus.franchise.dto.request.AuthRequestDto;
import com.abacus.franchise.dto.request.KitRequestDto;
import com.abacus.franchise.dto.request.UserRequestDto;
import com.abacus.franchise.response.SuccessResponse;

import jakarta.servlet.http.HttpServletRequest;

public interface UsersService {

    public SuccessResponse saveOrUpdateUsers(UserRequestDto userViewModel, MultipartFile profileImage,
            MultipartFile documentImage, HttpServletRequest request);

    public SuccessResponse loginUsers(AuthRequestDto authRequest);

    public SuccessResponse getUsersById(UUID userId, String roleName);

    public SuccessResponse getStudentByFranchiseId(UUID franchiseId);

    public SuccessResponse getAllCoursesByFranchiseId(UUID franchiseId);

    public SuccessResponse sendCourseKitRequest(KitRequestDto kitRequest);

}
