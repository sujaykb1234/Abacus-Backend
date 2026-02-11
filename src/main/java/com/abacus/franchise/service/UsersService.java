package com.abacus.franchise.service;

import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.viewModels.AuthRequestDto;
import com.abacus.franchise.viewModels.KitRequestDto;
import com.abacus.franchise.viewModels.UserRequestDto;
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
