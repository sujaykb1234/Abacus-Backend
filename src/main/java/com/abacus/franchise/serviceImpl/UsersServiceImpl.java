package com.abacus.franchise.serviceImpl;

import com.abacus.franchise.helperMethods.UserHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;
import com.abacus.franchise.dto.UserDetail;
import com.abacus.franchise.model.Address;
import com.abacus.franchise.model.TokenDetail;
import com.abacus.franchise.model.Users;
import com.abacus.franchise.repo.AddressRepo;
import com.abacus.franchise.repo.DistrictRepository;
import com.abacus.franchise.repo.RolesRepo;
import com.abacus.franchise.repo.StateRepository;
import com.abacus.franchise.repo.TokenDetailRepo;
import com.abacus.franchise.repo.UsersRepository;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.security.JwtUtil;
import com.abacus.franchise.service.UsersService;
import com.abacus.franchise.view.AuthRequest;
import com.abacus.franchise.view.ViewUser;
import jakarta.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UserHelper userHelper;
    private final UsersRepository usersRepository;
    private final RolesRepo rolesRepo;
    private final StateRepository stateRepository;
    private final DistrictRepository districtRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepo addressRepo;
    private final TokenDetailRepo tokenDetailRepo;
    private final JwtUtil jwtUtil;

    @Override
    public SuccessResponse saveOrUpdateUsers(
            ViewUser viewUser,
            MultipartFile profileImage,
            MultipartFile documentImage,
            HttpServletRequest request) {

        SuccessResponse response = new SuccessResponse();

        if (!userHelper.isValidUser(viewUser)) {
            response.basicDetailsIsNull();
            return response;
        }

        if (!userHelper.isValidAddress(viewUser)) {
            response.addressDetailIsNull();
            return response;
        }

        // remaining logic...
        return response;
    }

    @Override
    public SuccessResponse loginUsers(AuthRequest authRequest) {
        SuccessResponse response = new SuccessResponse();

        if (authRequest.getUsername() == null || authRequest.getPassword() == null) {
            response.loginCredentialIsNull();
            return response;
        }

        // Fix: Use injected usersRepository instead of undefined field
        UserDetail checkMobileNoIsPresentOrNot = usersRepository.checkMobileNoIsPresentOrNot(authRequest.getUsername());

        if (checkMobileNoIsPresentOrNot == null) {
            response.usernameIncorrect();
            return response;
        }

        if (!checkMobileNoIsPresentOrNot.getIsActive()) {  // Simplified boolean check
            response.userIsDeactivate();
            return response;
        }

        if (!passwordEncoder.matches(authRequest.getPassword(), checkMobileNoIsPresentOrNot.getPasswordHash())) {
            response.wrongPassword();
            return response;
        }

        response.loginSuccessfully(null);
        return response;
    }
}
