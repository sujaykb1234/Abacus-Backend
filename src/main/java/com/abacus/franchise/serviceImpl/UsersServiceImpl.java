package com.abacus.franchise.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.abacus.franchise.dto.CourseDetail;
import com.abacus.franchise.dto.UserAddressDetail;
import com.abacus.franchise.dto.UserDetail;
import com.abacus.franchise.model.Address;
import com.abacus.franchise.model.KitOrderItem;
import com.abacus.franchise.model.KitRequests;
import com.abacus.franchise.model.TokenDetail;
import com.abacus.franchise.model.Users;
import com.abacus.franchise.repo.AddressRepo;
import com.abacus.franchise.repo.CourseRepository;
import com.abacus.franchise.repo.DistrictRepository;
import com.abacus.franchise.repo.KitOrderItemRepository;
import com.abacus.franchise.repo.KitRequestsRepository;
import com.abacus.franchise.repo.RolesRepo;
import com.abacus.franchise.repo.StateRepository;
import com.abacus.franchise.repo.TokenDetailRepo;
import com.abacus.franchise.repo.UsersRepository;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.security.JwtUtil;
import com.abacus.franchise.service.UsersService;
import com.abacus.franchise.utility.ImageStoreProcess;
import com.abacus.franchise.viewModels.AuthRequest;
import com.abacus.franchise.viewModels.KitRequest;
import com.abacus.franchise.viewModels.UserViewModel;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	RolesRepo rolesRepo;

	@Autowired
	StateRepository stateRepository;

	@Autowired
	DistrictRepository districtRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AddressRepo addressRepo;

	@Autowired
	TokenDetailRepo tokenDetailRepo;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	KitOrderItemRepository kitOrderItemRepository;

	@Autowired
	KitRequestsRepository kitRequestsRepository;

	@Override
	public SuccessResponse saveOrUpdateUsers(UserViewModel viewUser, MultipartFile profileImage,
			MultipartFile documentImage, HttpServletRequest request) {

		SuccessResponse response = new SuccessResponse();

		// SAVE

		if (viewUser == null || viewUser.getEmail() == null || viewUser.getMobile() == null |
				viewUser.getFirstName() == null || viewUser.getMiddleName() == null || viewUser.getDateOfBirth() == null
				||
				viewUser.getLastName() == null || viewUser.getPasswordHash() == null || viewUser.getRoleId() == null) {
			response.basicDetailsIsNull();
			return response;
		}

		if (viewUser.getLine1() == null || viewUser.getLandmark() == null || viewUser.getCity() == null ||
				viewUser.getDistrictId() == null || viewUser.getStateId() == null || viewUser.getPincode() == null) {
			response.addressDetailIsNull();
			return response;
		}

		String checkRoleIdPresentOrNot = null;

		if (viewUser.getRoleId() != null) {
			checkRoleIdPresentOrNot = rolesRepo.checkRoleIdPresentOrNot(viewUser.getRoleId().toString());

			if (checkRoleIdPresentOrNot == null) {
				response.rolesNotFound();
				return response;
			}
		} else {
			response.rolesNotFound();
			return response;
		}

		if (viewUser.getStateId() == null
				|| stateRepository.checkStateIdPresentOrNot(viewUser.getStateId().toString()) == 0) {
			response.stateNotFound();
			return response;
		}

		if (viewUser.getDistrictId() == null
				|| districtRepository.checkDistrictIdPresentOrNot(viewUser.getDistrictId().toString()) == 0) {
			response.districtNotFound();
			return response;
		}

		UUID checkMobileNoIsExistOrNot = usersRepository.checkMobileNoIsExistOrNot(viewUser.getMobile());

		if (checkMobileNoIsExistOrNot != null) {
			response.mobileAlreadyExist();
			return response;
		}

		UUID checkEmailIsExistOrNot = usersRepository.checkEmailIsExistOrNot(viewUser.getEmail());

		if (checkEmailIsExistOrNot != null) {
			response.emailAlreadyExist();
			return response;
		}

		Users users = new Users();
		users.setFirstName(viewUser.getFirstName());
		users.setMiddleName(viewUser.getMiddleName());
		users.setLastName(viewUser.getLastName());
		users.setEmail(viewUser.getEmail());
		users.setMobile(viewUser.getMobile());
		users.setPasswordHash(passwordEncoder.encode(viewUser.getPasswordHash()));
		users.setRoleId(viewUser.getRoleId());
		users.setDateOfBirth(viewUser.getDateOfBirth());

		if (viewUser.getFranchiseId() != null) {
			UUID checkFranchiseIdIsExistOrNot = usersRepository
					.checkFranchiseIdIsExistOrNot(viewUser.getFranchiseId().toString());

			if (checkFranchiseIdIsExistOrNot != null) {
				users.setFranchiseId(checkFranchiseIdIsExistOrNot);
			}
		}

		if (viewUser.getFranchiseName() != null) {
			users.setFranchiseName(viewUser.getFranchiseName());
		}

		if (profileImage != null) {
			List<String> saveProfileImage = ImageStoreProcess.saveFile(profileImage, request);

			if (saveProfileImage != null) {
				users.setProfileLink(saveProfileImage.get(1));
				users.setProfileName(saveProfileImage.get(0));
			}
		}

		if (documentImage != null) {
			List<String> saveDocumentImage = ImageStoreProcess.saveFile(documentImage, request);

			if (saveDocumentImage != null) {
				users.setDocumentLink(saveDocumentImage.get(1));
				users.setDocumentName(saveDocumentImage.get(0));
			}
		}

		Users saveUsers = usersRepository.save(users);

		Address address = new Address();
		address.setUser_id(saveUsers.getUserId());
		address.setLine1(viewUser.getLine1());
		address.setLandmark(viewUser.getLandmark());
		address.setCity(viewUser.getCity());
		address.setStateId(viewUser.getStateId());
		address.setDistrictId(viewUser.getDistrictId());
		address.setPincode(viewUser.getPincode());

		addressRepo.save(address);

		String accessToken = jwtUtil.generateAccessToken(
				viewUser.getMobile(),
				checkRoleIdPresentOrNot);

		TokenDetail tokenEntity = new TokenDetail();
		tokenEntity.setAccessTokenHash(accessToken);
		tokenEntity.setUserId(saveUsers.getUserId());
		tokenDetailRepo.save(tokenEntity);

		response.saveUserResponse(accessToken);
		return response;

	}

	@Override
	public SuccessResponse loginUsers(AuthRequest authRequest) {

		SuccessResponse response = new SuccessResponse();

		if (authRequest.getUsername() == null || authRequest.getPassword() == null) {
			response.loginCredentialIsNull();
			return response;
		}

		UserDetail checkMobileNoIsPresentOrNot = usersRepository.checkMobileNoIsPresentOrNot(authRequest.getUsername());

		if (checkMobileNoIsPresentOrNot == null) {
			response.usernameIncorrect();
			return response;
		}

		if (checkMobileNoIsPresentOrNot.getIsActive() == false) {
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

	@Override
	public SuccessResponse getUsersById(UUID userId, String roleName) {

		SuccessResponse response = new SuccessResponse();

		UserAddressDetail userAddressDetailByUserId = usersRepository.getUserAddressDetailByUserId(userId.toString(),
				roleName);

		if (userAddressDetailByUserId != null) {
			response.userFoundResponse(userAddressDetailByUserId);
			return response;
		}

		response.userNotFound();
		return response;
	}

	@Override
	public SuccessResponse getStudentByFranchiseId(UUID franchiseId) {
		SuccessResponse response = new SuccessResponse();

		List<UserAddressDetail> studentDetailByFranchiseId = usersRepository
				.getStudentDetailByFranchiseId(franchiseId.toString());

		if (studentDetailByFranchiseId != null) {
			response.userFoundResponse(studentDetailByFranchiseId);
			return response;
		}

		response.userNotFound();
		return response;
	}

	@Override
	public SuccessResponse getAllCoursesByFranchiseId(UUID franchiseId) {

		SuccessResponse response = new SuccessResponse();

		List<CourseDetail> allCoursesByFranchiseId = courseRepository
				.getAllCoursesByFranchiseId(franchiseId.toString());

		if (allCoursesByFranchiseId.isEmpty()) {
			response.courseNotFound("");
			return response;
		}

		response.courseFound(allCoursesByFranchiseId);
		return response;

	}

	@Override
	public SuccessResponse sendCourseKitRequest(KitRequest kitRequest) {
		SuccessResponse response = new SuccessResponse();

		KitRequests kitRequests = new KitRequests();

		if (kitRequest.getFranchiseId() != null) {
			UUID checkFranchiseIdIsExistOrNot = usersRepository
					.checkFranchiseIdIsExistOrNot(kitRequest.getFranchiseId().toString());

			if (checkFranchiseIdIsExistOrNot == null) {
				response.franchiesnotfound();
				return response;
			}

			kitRequests.setFranchiseId(checkFranchiseIdIsExistOrNot);
		}

		if (kitRequest.getAddressId() != null) {
			UUID addressById = addressRepo.getAddressById(kitRequest.getAddressId().toString());

			if (addressById == null) {
				response.addressNotFound();
				return response;
			}

			kitRequests.setAddressId(addressById);

		} else {
			if (kitRequest.getStateId() == null
					|| stateRepository.checkStateIdPresentOrNot(kitRequest.getStateId().toString()) == 0) {
				response.stateNotFound();
				return response;
			}

			if (kitRequest.getDistrictId() == null
					|| districtRepository.checkDistrictIdPresentOrNot(kitRequest.getDistrictId().toString()) == 0) {
				response.districtNotFound();
				return response;
			}

			Address address = new Address();
			address.setUser_id(kitRequest.getFranchiseId());
			address.setLine1(kitRequest.getLine1());
			address.setLandmark(kitRequest.getLandmark());
			address.setCity(kitRequest.getCity());
			address.setStateId(kitRequest.getStateId());
			address.setDistrictId(kitRequest.getDistrictId());
			address.setPincode(kitRequest.getPincode());

			Address saveAddress = addressRepo.save(address);
			kitRequests.setAddressId(saveAddress.getAddressId());

		}

		KitRequests save_kitrequest = kitRequestsRepository.save(kitRequests);

		if (kitRequest.getKitOrderItems() != null) {

			for (KitOrderItem item : kitRequest.getKitOrderItems()) {
				UUID checkCourseIdIsExistOrNot = courseRepository
						.checkCourseIdIsExistOrNot(item.getCourseId().toString());

				if (checkCourseIdIsExistOrNot == null) {
					response.courseNotFound(item.getCourseId().toString());
					return response;
				}

				item.setKitRequestId(save_kitrequest.getKitRequestId());
			}
			kitOrderItemRepository.saveAll(kitRequest.getKitOrderItems());
		}

		response.kitsSentSuccessfully(null);
		return response;
	}
}
