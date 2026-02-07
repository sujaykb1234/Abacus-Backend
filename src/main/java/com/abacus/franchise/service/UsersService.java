package com.abacus.franchise.service;

import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.viewModels.AuthRequest;
import com.abacus.franchise.viewModels.KitRequest;
import com.abacus.franchise.viewModels.UserViewModel;
import jakarta.servlet.http.HttpServletRequest;

public interface UsersService {

    public SuccessResponse saveOrUpdateUsers(UserViewModel userViewModel, MultipartFile profileImage, MultipartFile documentImage, HttpServletRequest request);

    public SuccessResponse loginUsers(AuthRequest authRequest);

    public SuccessResponse getUsersById(UUID userId, String roleName);

    public SuccessResponse getStudentByFranchiseId(UUID franchiseId);

    public SuccessResponse getAllCoursesByFranchiseId(UUID franchiseId);

    public SuccessResponse sendCourseKitRequest(KitRequest kitRequest);

}
