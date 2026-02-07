//package com.abacus.franchise.serviceImpl;
//
//import java.io.IOException;
//import java.security.SecureRandom;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.abacus.franchise.dto.FranchiseDTO;
//import com.abacus.franchise.dto.FranchiseOrderDTO;
//import com.abacus.franchise.dto.KitOrderResponseDTO;
//import com.abacus.franchise.dto.ProductRequestDTO;
//import com.abacus.franchise.dto.StudentDTO;
//import com.abacus.franchise.dto.StudentResponseDTO;
//import com.abacus.franchise.model.Address;
//import com.abacus.franchise.model.Course;
//import com.abacus.franchise.model.Franchise;
//import com.abacus.franchise.model.FranchiseDiffrentAddKitReq;
//import com.abacus.franchise.model.FranchiseKitRequest;
//import com.abacus.franchise.model.FranchiseKitRequestsLogs;
//import com.abacus.franchise.model.FranchiseOrder;
//import com.abacus.franchise.model.KitRequest;
//import com.abacus.franchise.model.ProductOrderRequest;
//import com.abacus.franchise.model.Products;
//import com.abacus.franchise.model.Student;
//import com.abacus.franchise.model.TokenDetail;
//import com.abacus.franchise.repo.CourseRepo;
//import com.abacus.franchise.repo.DiffrentAddKitReqRepo;
//import com.abacus.franchise.repo.FranchiseKitRequestRepo;
//import com.abacus.franchise.repo.FranchiseKitRequestsLogsRepo;
//import com.abacus.franchise.repo.FranchiseOrderRepo;
//import com.abacus.franchise.repo.FranchiseRepo;
//import com.abacus.franchise.repo.ProductOrderRequestRepo;
//import com.abacus.franchise.repo.ProductsRepo;
//import com.abacus.franchise.repo.StudentRepo;
//import com.abacus.franchise.repo.TokenDetailRepo;
//import com.abacus.franchise.response.SuccessResponse;
//import com.abacus.franchise.security.JwtUtil;
//import com.abacus.franchise.service.FranchiseService;
//import com.abacus.franchise.utility.FranchiseStatus;
//import com.abacus.franchise.utility.ImageStoreProcess;
//import com.abacus.franchise.utility.KitOrderStatus;
//import com.abacus.franchise.utility.TokenOnly;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.transaction.Transactional;
//
//@Service
//public class FranchiseServiceImpl implements FranchiseService {
//
//	@Autowired
//	ModelMapper modelMapper;
//
/// /	@Autowired
/// /	S3BucketService s3BucketService;
//
//	@Autowired
//	StudentRepo studentRepo;
//
//	@Autowired
//	FranchiseRepo franchiseRepo;
//
//	@Autowired
//	CourseRepo courseRepo;
//
//	@Autowired
//	private ProductOrderRequestRepo productOrderRequestRepo;
//
//	@Autowired
//	private ProductsRepo productsRepo;
//
//	@Autowired
//	private FranchiseOrderRepo franchiseOrderRepo;
//
//	@Autowired
//	DiffrentAddKitReqRepo diffrentAddKitReqRepo;
//
//	@Autowired
//	NotificationsServiceImple notificationsServiceImple;
//
//	@Autowired
//	FranchiseKitRequestRepo franchiseKitRequestRepo;
//
//	@Autowired
//	FranchiseKitRequestsLogsRepo requestsLogsRepo;
//
//	@Autowired
//	PasswordEncoder passwordEncoder;
//
//    @Autowired
//    JwtUtil jwtUtil;
//
//	@Autowired
//    TokenDetailRepo tokenDetailRepo;
//
//	// Method to generate and assign a unique franchise number
//	public String generateUniqueFranchiseNumber() {
//		String prefix = "FRID";
//		String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMM"));
//		String franchiseNumber;
//		int sequence = 1;
//		List<String> lastFranchiseNumbers = franchiseRepo.findLastFranchiseNumber(prefix + datePart + "%");
//		if (!lastFranchiseNumbers.isEmpty()) {
//			String lastSequenceStr = lastFranchiseNumbers.get(0).substring(8); // Extract the sequence part
//			sequence = Integer.parseInt(lastSequenceStr) + 1;
//		}
//		franchiseNumber = prefix + datePart + String.format("%04d", sequence);
//		return franchiseNumber;
//	}
//
////-------------------------------------------------------------------------------------------------------------------------
//	@Override
//	public SuccessResponse registerAndUpdateTheFrinchise(Franchise franchise, MultipartFile franchiseOwnerPic,
//			MultipartFile franchiseOwnerIdPhoto,HttpServletRequest request) {
//		SuccessResponse response = new SuccessResponse();
//
//		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//		try {
//			if (franchise.getFranchise_name() == null || franchise.getFranchise_owner() == null
//					|| franchise.getFranchise_email() == null
//					|| !franchise.getFranchise_email()
//							.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|org|in|edu|gov|net)")
//                 ) {
//				response.missingFieldResponse(franchise);
//				return response;
//			}
//
//
//			Optional<Franchise> existingFranchiseWithEmail = franchiseRepo.findByEmail(franchise.getFranchise_email());
//			if (existingFranchiseWithEmail.isPresent()
//					&& !existingFranchiseWithEmail.get().getFranchise_id().equals(franchise.getFranchise_id())) {
//				response.emailAlreadyExist();
//				return response;
//			}
//
//			Optional<Franchise> existfranchiesWithmobileNo = franchiseRepo.findByMobile(franchise.getMobile_no());
//			if (existfranchiesWithmobileNo.isPresent()
//					&& !existfranchiesWithmobileNo.get().getFranchise_id().equals(franchise.getFranchise_id())) {
//				response.mobileAlreadyExist();
//				return response;
//			}
//
//			if (franchise.getFranchise_id() != null) {
//				Optional<Franchise> existingFranchiseOpt = franchiseRepo.findById(franchise.getFranchise_id());
//				if (existingFranchiseOpt.isPresent()) {
//					Franchise franchiseToUpdate = existingFranchiseOpt.get();
//					FranchiseStatus oldStatus = franchiseToUpdate.getFranchiseStatus();
//					franchise.setFranchiseStatus(oldStatus);
//					String franchiesNo = franchiseToUpdate.getFranchise_number();
//					franchise.setFranchise_number(franchiesNo);
//					franchise.setCreation_time(franchiseToUpdate.getCreation_time());
//					franchise.setModification_time(sdf.format(date));
//
//					if (franchiseOwnerPic != null) {
////						StoredImages storedOwnerPic = s3BucketService.storeFile(franchiseOwnerPic.getOriginalFilename(),
////								franchiseOwnerPic.getInputStream(), franchiseOwnerPic.getSize(), 3);
////
////						franchise.setProfile_image_name(storedOwnerPic.getProfile_image_name());
////						franchise.setProfile_image_link(storedOwnerPic.getProfile_image_link());
//
//						List<String> saveFile = ImageStoreProcess.saveFile(franchiseOwnerPic, request);
//
//						if(saveFile != null) {
//							franchise.setProfile_image_name(saveFile.get(0));
//							franchise.setProfile_image_link(saveFile.get(1));
//						}
//					} else {
//						franchise.setProfile_image_name(franchiseToUpdate.getProfile_image_name());
//						franchise.setProfile_image_link(franchiseToUpdate.getProfile_image_link());
//					}
//					if (franchiseOwnerIdPhoto != null) {
////						StoredImages storedOwnerIdPhoto = s3BucketService.storeFile(
////								franchiseOwnerIdPhoto.getOriginalFilename(), franchiseOwnerIdPhoto.getInputStream(),
////								franchiseOwnerIdPhoto.getSize(), 4);
////						franchise.setDocument_image_name(storedOwnerIdPhoto.getId_proof_image_link());
////						franchise.setDocument_image_link(storedOwnerIdPhoto.getId_proof_image_link());
//
//						List<String> saveFile = ImageStoreProcess.saveFile(franchiseOwnerIdPhoto, request);
//						if(saveFile != null) {
//								franchise.setDocument_image_name(saveFile.get(0));
//								franchise.setDocument_image_link(saveFile.get(1));
//						}
//					} else {
//						franchise.setDocument_image_name(franchiseToUpdate.getDocument_image_name());
//						franchise.setDocument_image_link(franchiseToUpdate.getDocument_image_link());
//					}
//					modelMapper.map(franchise, franchiseToUpdate);
//
//					franchiseRepo.save(franchiseToUpdate);
////					FranchiseDTO franchiseDto = modelMapper.map(franchiseToUpdate, FranchiseDTO.class);
//					response.franchiseUpdated(null);
//					return response;
//				} else {
//					response.idNotFound();
//					return response;
//				}
//			} else {
//				// Create new franchise
//				if (franchiseOwnerPic == null || franchiseOwnerIdPhoto == null) {
//					response.nullFile();
//					return response;
//				}
//				// Store owner picture
////				StoredImages storedOwnerPic = s3BucketService.storeFile(franchiseOwnerPic.getOriginalFilename(),
////						franchiseOwnerPic.getInputStream(), franchiseOwnerPic.getSize(), 3);
//
//				List<String> saveFile = ImageStoreProcess.saveFile(franchiseOwnerPic, request);
//				if(saveFile != null) {
//					franchise.setProfile_image_name(saveFile.get(0));
//					franchise.setProfile_image_link(saveFile.get(1));
//				}
//
//				notificationsServiceImple.generateNotificationAfterAddFranchise(franchise);
//				franchise.setFranchise_number(generateUniqueFranchiseNumber());
//				franchise.setFranchise_password(null);
//
////				StoredImages storedOwnerIdPhoto = s3BucketService.storeFile(franchiseOwnerIdPhoto.getOriginalFilename(),
////						franchiseOwnerIdPhoto.getInputStream(), franchiseOwnerIdPhoto.getSize(), 4);
//
//				List<String> saveFile2 = ImageStoreProcess.saveFile(franchiseOwnerIdPhoto, request);
//
//				if(saveFile2 != null) {
//
//					franchise.setDocument_image_name(saveFile2.get(0));
//					franchise.setDocument_image_link(saveFile2.get(1));
//				}
//
//				franchise.setCreation_time(sdf.format(date));
//				franchiseRepo.save(franchise);
//
////				FranchiseDTO franchiseDto = modelMapper.map(franchise, FranchiseDTO.class);
//				response.saveTheFranchise(null);
//				return response;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			response.ExceptionSMS(response);
//		}
//		return response;
//	}
//
//	// Password generation method
//	public String generateUniquepass(Long franchiseId) {
//		Optional<Franchise> optionalFranchise = franchiseRepo.findById(franchiseId);
//		if (optionalFranchise.isEmpty()) {
//			return "Franchise not found";
//		}
//		Franchise franchise = optionalFranchise.get();
//		String frachisePassword = generateRandomPassword(6);
//		franchise.setFranchise_password(frachisePassword);
//		franchiseRepo.save(franchise);
//		return frachisePassword;
//	}
//
//	String generateRandomPassword(int length) {
//		SecureRandom random = new SecureRandom();
//		StringBuilder password = new StringBuilder(length);
//		String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
//		for (int i = 0; i < length; i++) {
//			password.append(characters.charAt(random.nextInt(characters.length())));
//		}
//		return password.toString();
//	}
//
//	@Override
//	public SuccessResponse loginTheFranchise(Franchise franchise) {
//		SuccessResponse response = new SuccessResponse();
//		String username = franchise.getMobile_no();
//		String password = franchise.getFranchise_password();
//
////		System.out.println("mobile no " + username);
////		System.out.println("Password " + password);
//
//		if (username == null || password == null) {
//			response.nullAdminnameAndPass();
//			return response;
//		}
//		Optional<Franchise> optionalFranchise = franchiseRepo.findByusername(username);
//
//		if (optionalFranchise.isPresent()) {
//
//			Franchise franchiseFromDb = optionalFranchise.get();
//
//
//
//		    if (!passwordEncoder.matches(password, franchiseFromDb.getFranchise_password())) {
//				response.wrongPassword();
//				return response;
//			}
//
//		    Optional<TokenOnly> byMobileNoAndRoles = tokenDetailRepo.findByMobileNoAndRoles(franchiseFromDb.getMobile_no(), franchiseFromDb.getRoles());
//
//
//			response.frinchiesLoginSuccessfully(byMobileNoAndRoles.get().getToken());
//
//		} else {
//			response.userNotFound();
//		}
//		return response;
//	}
////--------------------------------------------------------------------------------------------------------------------------
//
//	@Override
//	public SuccessResponse setTheNewPasswordForFranchies(Franchise franchise) {
//		SuccessResponse response = new SuccessResponse();
//		if (franchise.getFranchise_email() == null) {
//			response.emailNotFound();
//			return response;
//		}
//
//		String franchiesUsername = franchise.getFranchise_email();
//		String franchiesPassword = franchise.getFranchise_password();
//		Optional<Franchise> byUsername = franchiseRepo.getbyuserName(franchiesUsername);
//
//		if (byUsername.isPresent()) {
//			if (franchiesPassword == null) {
//				response.passwordNull();
//				return response;
//			}
//			if (franchiesPassword.length() < 6) {
//				response.lengofpassword();
//				return response;
//			}
//			Franchise getByusername = byUsername.get();
//
//
//
//			getByusername.setFranchise_password(passwordEncoder.encode(franchiesPassword));
//			getByusername.setCreation_time(byUsername.get().getCreation_time());
//
//			franchiseRepo.save(getByusername);
//
//			 String accessToken = jwtUtil.generateAccessToken(
//					 getByusername.getMobile_no(),
//		                getByusername.getRoles().toString()
//		        );
//
//		    TokenDetail tokenEntity = new TokenDetail();
//		    tokenEntity.setToken(accessToken);
//		    tokenEntity.setCreatedAt(LocalDateTime.now());
//		    tokenEntity.setRoles(getByusername.getRoles());
//		    tokenEntity.setMobileNo(getByusername.getMobile_no());
//
//		    tokenDetailRepo.save(tokenEntity);
//
//
//			response.passwordUpdateSuccesfully();
//			return response;
//		} else {
//			response.incorrectUserName();
//			return response;
//		}
//	}
//
////--------------------------------------------------------------------------------------------------------------------------
//
//	@Override
//	public SuccessResponse getThefranchiesUsingTheID(Long id) {
//		SuccessResponse response = new SuccessResponse();
//		if (id == null) {
//			response.idisNull();
//			return response;
//		}
//		try {
//			Optional<Franchise> franchiseOpt = franchiseRepo.getbyetheFranchiesUsingTheid(id);
//			if (franchiseOpt.isPresent()) {
//				Franchise franchise = franchiseOpt.get();
//				FranchiseDTO franchiseDto = modelMapper.map(franchise, FranchiseDTO.class);
//				response.getSingleFranchise(franchiseDto);
//				return response;
//			} else {
//				response.notfound();
//				return response;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			response.ExceptionSMS(response);
//			return response;
//		}
//	}
////-----------------------------------------------------------------------------------------------------------------------------
//
//	@Override
//	public SuccessResponse getTheAllFranchies(int pageNo, int pageSize, String sortBy, String orderBy) {
//		SuccessResponse response = new SuccessResponse();
//		Sort newSort;
//		if (orderBy.equalsIgnoreCase("ASC")) {
//			newSort = Sort.by(sortBy).ascending();
//		} else {
//			newSort = Sort.by(sortBy).descending();
//		}
//
//		PageRequest page = PageRequest.of(pageNo, pageSize, newSort);
//
//		Page<Franchise> franchisePage = franchiseRepo.getAllFranchies(page);
//
//		List<Franchise> franchises = franchisePage.getContent();
//
//		if (!franchises.isEmpty()) {
//			List<FranchiseDTO> franchiseDTOs = new ArrayList<>();
//
//			for (Franchise franchise : franchises) {
//				FranchiseDTO franchiseDTO = modelMapper.map(franchise, FranchiseDTO.class);
//
//				if (franchise.getFranchise_address() != null) {
//					Address addressDTO = modelMapper.map(franchise.getFranchise_address(), Address.class);
//					franchiseDTO.setFranchise_address(addressDTO);
//				}
//
//				franchiseDTOs.add(franchiseDTO);
//			}
//
//			Map<String, Object> body = new HashMap<>();
//			body.put("data", franchiseDTOs);
//			body.put("totalPages", franchisePage.getTotalPages());
//			body.put("totalRecords", franchisePage.getTotalElements());
//
//			response.getAllFranchies(body);
//			return response;
//		} else {
//			response.franchiseNotFound();
//		}
//
//		return response;
//	}
//
////----------------------------------------------------------------------------------------------------------------
//
//	@Transactional
//	public SuccessResponse deleteTheFranchiesUsingTheID(Long id) {
//		SuccessResponse response = new SuccessResponse();
//
//		if (id == null) {
//			response.idisNull();
//			return response;
//		}
//
//		Optional<Franchise> franchiseOptional = franchiseRepo.findById(id);
//
//		if (franchiseOptional.isPresent()) {
//			long studentCount = studentRepo.countByFranchiseId(id);
//
//			if (studentCount > 0) {
//
//				response.gretherThanCountStudent();
//				return response;
//			}
//
//			studentRepo.deleteByFranchiseId(id);
//
//			franchiseRepo.deleteByIdForFranchise(id);
//
//			response.franchiesDeleteSuccesfully();
//			return response;
//		} else {
//			response.franchiseNotFound();
//			return response;
//		}
//	}
//
////------------------------------------------------------------------------------------------------------------------------
//	@Override
//	public SuccessResponse getAllFranchiseRequests() {
//		SuccessResponse response = new SuccessResponse();
//		List<Franchise> findRequests = franchiseRepo.findAllRequests();
//		if (findRequests.isEmpty()) {
//			response.franchiseNotFound();
//			return response;
//		}
//		List<FranchiseDTO> franchiseDTOs = findRequests.stream()
//				.map(franchise -> modelMapper.map(franchise, FranchiseDTO.class)).toList();
//		response.franchiseRequests(franchiseDTOs);
//		return response;
//	}
//
//
//	@Override
//	public SuccessResponse getTheAllFranchises() {
//
//		SuccessResponse response = new SuccessResponse();
//		List<Franchise> allFranchise = franchiseRepo.findAllFranchise();
//		response.getAllFranchies(allFranchise);
//		return response;
//	}
//
//
//	@Transactional
//	public SuccessResponse assignCoursesToFranchise(Long franchiseId, Set<Long> courseIds) {
//		SuccessResponse response = new SuccessResponse();
//		Optional<Franchise> franchiseOpt = franchiseRepo.findById(franchiseId);
//		if (!franchiseOpt.isPresent()) {
//			response.franchiseNotFound();
//			return response;
//		}
//		Franchise franchise = franchiseOpt.get();
//		int alreadyAssignedCount = franchiseRepo.checkCourseAssignedToAnotherFranchise(franchiseId, courseIds);
//		if (alreadyAssignedCount > 0) {
//			response.alredyAssignThecourse();
//			return response;
//		}
//		for (Long courseId : courseIds) {
//			Optional<Course> courseOpt = courseRepo.findById(courseId);
//			if (!courseOpt.isPresent()) {
//				response.courseNotFound(courseId); // If course not found, respond accordingly
//				return response;
//			}
//			Course course = courseOpt.get();
//			franchise.addCourse(course);
//			FranchiseKitRequest franchiseKitRequest = new FranchiseKitRequest();
//			franchiseKitRequest.setCourseId(course.getCourse_id());
//			franchiseKitRequest.setFranchise_id(franchiseId);
//			System.out.println("Course name : " + franchise.getFranchise_name());
//			franchiseKitRequest.setFranchise_name(franchise.getFranchise_name());
//			franchiseKitRequest.setFranchise_number(franchise.getFranchise_number());
//			franchiseKitRequest.setAvelableTotalKits(course.getNo_of_books());
//			franchiseKitRequest.setKitOrderStatus(KitOrderStatus.PENDING);
//			franchiseKitRequest.setOrderedKits(0);
//			franchiseKitRequest.setRemainingStudents(0);
//			franchiseKitRequestRepo.save(franchiseKitRequest);
//		}
//		franchiseRepo.save(franchise);
//		response.courseAssign(franchise);
//		return response;
//	}
//
////-------------------------------------------------------------------------------------------------------------------------------
//	public SuccessResponse removeCourseFromFranchise(Long franchiseId, Long courseId) {
//		SuccessResponse response = new SuccessResponse();
//		Optional<Franchise> franchiseOpt = franchiseRepo.findById(franchiseId);
//		if (!franchiseOpt.isPresent()) {
//			response.franchiseNotFound();
//			return response;
//		}
//		Franchise franchise = franchiseOpt.get();
//		Optional<Course> courseOpt = courseRepo.findById(courseId);
//		if (!courseOpt.isPresent()) {
//			response.notfoundCourse();
//			return response;
//		}
//		Course course = courseOpt.get();
//		franchise.removeCourse(course);
//
//		franchiseRepo.save(franchise);
//		response.courseRemoved(course);
//		return response;
//	}
//
////------------------------------------------------------------------------------------------------------------------------------
//	@Override
//	public SuccessResponse getCourseUsingFranchiseId(Long franchise_id) {
//		SuccessResponse response = new SuccessResponse();
//		Optional<Franchise> franchiseOpt = franchiseRepo.findById(franchise_id);
//		if (!franchiseOpt.isPresent()) {
//			response.franchiseNotFound();
//			return response;
//		}
//		Franchise franchise = franchiseOpt.get();
//		Set<Course> courses = franchise.getCourses();
//		response.courseRetrieved(courses);
//		return response;
//	}
//
////--------------------------------------------------------------------------------------------------------------------------
//
//	@Override
//	public SuccessResponse getKitCountForFranchiseAndCourse(Long franchiseId, Long courseId) {
//		SuccessResponse response = new SuccessResponse();
//		Optional<FranchiseKitRequest> findByFranchiseIdAndCourseId = franchiseKitRequestRepo
//				.findByFranchiseIdAndCourseId(franchiseId, courseId);
//		if (!findByFranchiseIdAndCourseId.isPresent()) {
//			response.noKitReq();
//			return response;
//		}
//		FranchiseKitRequest franchiseKitRequest = findByFranchiseIdAndCourseId.get();
//		response.kitRequests(franchiseKitRequest);
//		return response;
//	}
//
//
//	@Override
//	public SuccessResponse getTheFranchiesReports(Long franchies_id, Long course_id, String startDate, String endDate) {
//		SuccessResponse response = new SuccessResponse();
//
//		if (franchies_id == null) {
//			response.nullData();
//			return response;
//		}
//
//		List<Franchise> franchiseList = franchiseRepo.findDetailsUsingFidAndCid(franchies_id, course_id, startDate,
//				endDate);
//
//		if (!franchiseList.isEmpty()) {
//			List<FranchiseDTO> franchiseDTOList = franchiseList.stream()
//					.map(franchise -> modelMapper.map(franchise, FranchiseDTO.class)).collect(Collectors.toList());
//
//			response.getAllFranchies(franchiseDTOList);
//
//			return response;
//		} else {
//			response.franchiesnotfound();
//			return response;
//		}
//	}
//
////------------------------------------------------------------------------------------------------------------------------------
//
//	@Override
//	public SuccessResponse getAllStudentUsingDate(String startDate, String endDate, Long franchies_id) {
//		SuccessResponse response = new SuccessResponse();
//
//		if (startDate == null || endDate == null) {
//			response.nullData();
//			return response;
//		}
//
//		LocalDate start = LocalDate.parse(startDate);
//		LocalDate end = LocalDate.parse(endDate);
//
//		LocalDateTime startDateTime = start.atStartOfDay(); // 00:00:00
//		LocalDateTime endDateTime = end.atTime(23, 59, 59); // 23:59:59
//
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		String formattedStartDateTime = startDateTime.format(formatter);
//		String formattedEndDateTime = endDateTime.format(formatter);
//
//		List<Student> allStudents;
////		= studentRepo.getAllStudentUsingDate(formattedStartDateTime, formattedEndDateTime, franchies_id);
//
//		if (franchies_id != null) {
//			allStudents = studentRepo.getAllStudentUsingDateWithFranchise(formattedStartDateTime, formattedEndDateTime,
//					franchies_id);
//		} else {
//			allStudents = studentRepo.getAllStudentUsingDateWithoutFranchise(formattedStartDateTime,
//					formattedEndDateTime);
//		}
//
//		if (allStudents.isEmpty()) {
//
//			response.studentNotFound();
//			return response;
//		} else {
//
//			List<StudentResponseDTO> studentDTOs = allStudents.stream().map(this::mapStudentToDTO)
//					.collect(Collectors.toList());
//			response.getAllStudent(studentDTOs);
//			return response;
//		}
//
//	}
//
//	private StudentResponseDTO mapStudentToDTO(Student student) {
//		StudentResponseDTO dto = new StudentResponseDTO();
//		dto.setStudent_id(student.getStudent_id());
//		dto.setFranchiseName(student.getFranchise().getFranchise_name());
//		dto.setCurrentCourseName(student.getCurrentCourseName());
//		dto.setCurrentCourseId(student.getCurrentCourseId());
//		dto.setCreation_time(student.getCreation_time().toString()); // Adjust date format if necessary
//		dto.setDob(student.getDob() != null ? student.getDob().toString() : null);
//		dto.setPassword(student.getPassword());
//		dto.setMobile_no(student.getMobile_no());
//		dto.setEmail(student.getEmail());
//		dto.setFirst_name(student.getFirst_name());
//		dto.setLast_name(student.getLast_name());
//		dto.setFranchise_owner(student.getFranchise().getFranchise_owner());
//		return dto;
//	}
//
////---------------------------------------------------------------------------------------------------------------------------
//
//	@Override
//	public SuccessResponse getAllFranchiesByDate(String startDate, String endDate) {
//
//		SuccessResponse response = new SuccessResponse();
//		if (startDate == null || endDate == null) {
//			response.nullData();
//			return response;
//		}
//		LocalDate start = LocalDate.parse(startDate);
//		LocalDate end = LocalDate.parse(endDate);
//
//		LocalDateTime startDateTime = start.atStartOfDay(); // 00:00:00
//		LocalDateTime endDateTime = end.atTime(23, 59, 59); // 23:59:59
//
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		String formattedStartDateTime = startDateTime.format(formatter);
//		String formattedEndDateTime = endDateTime.format(formatter);
//
//		List<Franchise> allfranhcies = franchiseRepo.getAllFranchiesUsingDate(formattedStartDateTime,
//				formattedEndDateTime);
//
//		if (allfranhcies.isEmpty()) {
//			response.franchiseNotFound();
//			return response;
//		} else {
//			response.getAllFranchies(allfranhcies);
//			return response;
//		}
//
//	}
//
//	@Override
//	@Transactional
//	public SuccessResponse sendDiffrentAddressKitRequest(FranchiseDiffrentAddKitReq diffrentAddKitReq) {
//		SuccessResponse response = new SuccessResponse();
//		if (diffrentAddKitReq.getFranchiseId() == null || diffrentAddKitReq.getAddress() == null
//				|| diffrentAddKitReq.getStudentId() == null || diffrentAddKitReq.getCourseId() == null) {
//			response.setStatus(false);
//			response.setMessage("Franchise ID, Kit Address, Student ID, and Course ID are required.");
//			return response;
//		}
//		Optional<Student> studentOpt = studentRepo.findById(diffrentAddKitReq.getStudentId());
//		if (!studentOpt.isPresent()) {
//			response.setStatus(false);
//			response.setMessage("Student not found with ID: " + diffrentAddKitReq.getStudentId());
//			return response;
//		}
//		Student student = studentOpt.get();
//		Optional<Course> courseOpt = courseRepo.findById(diffrentAddKitReq.getCourseId());
//		if (!courseOpt.isPresent()) {
//			response.setStatus(false);
//			response.setMessage("Course not found with ID: " + diffrentAddKitReq.getCourseId());
//			return response;
//		}
//		Optional<FranchiseKitRequest> findByFranchiseIdAndCourseId = franchiseKitRequestRepo
//				.findByFranchiseIdAndCourseId(diffrentAddKitReq.getFranchiseId(), diffrentAddKitReq.getCourseId());
//		FranchiseKitRequest franchiseKitRequest = findByFranchiseIdAndCourseId.get();
//		franchiseKitRequest.setOrderedKits(franchiseKitRequest.getOrderedKits() + 1);
//		franchiseKitRequest.setRemainingKitsSendToFranchise(franchiseKitRequest.getRemainingKitsSendToFranchise() + 1);
//		franchiseKitRequest.setRemainingStudents(franchiseKitRequest.getRemainingStudents() + 1);
//		franchiseKitRequestRepo.save(franchiseKitRequest);
//		Course course = courseOpt.get();
//		Optional<FranchiseDiffrentAddKitReq> existingRequestOpt = diffrentAddKitReqRepo
//				.findByStudentIdAndCourseId(diffrentAddKitReq.getStudentId(), diffrentAddKitReq.getCourseId());
//		if (existingRequestOpt.isPresent()) {
//			response.setStatus(false);
//			response.setMessage("A kit request for this student has already been made. Only one reattempt is allowed.");
//			return response;
//		}
//		Optional<Franchise> findById = franchiseRepo.findById(diffrentAddKitReq.getFranchiseId());
//		Franchise franchise = findById.get();
//		diffrentAddKitReq.setStudentName(student.getFirst_name() + " " + student.getLast_name());
//		diffrentAddKitReq.setCourseName(course.getCourse_name());
//		diffrentAddKitReq.setFranchiseName(franchise.getFranchise_name());
//		diffrentAddKitReq.setFranchiseMobile(franchise.getMobile_no());
//		diffrentAddKitReq.setKitOrderStatus(KitOrderStatus.ORDERED);
//		FranchiseDiffrentAddKitReq savedRequest = diffrentAddKitReqRepo.save(diffrentAddKitReq);
//		response.setStatus(true);
//		response.setMessage("Kit request sent successfully to the different address.");
//		response.setData(savedRequest);
//		return response;
//	}
//
//	@Override
//	@Transactional
//	public SuccessResponse sendDiffrentAddressKitByAdmin(FranchiseDiffrentAddKitReq diffrentAddKitReq) {
//		System.out.println("Tracking number : " + diffrentAddKitReq.getTrackingNumber());
//		System.out.println("Franchise and course :" + diffrentAddKitReq.getStudentId() + " course "
//				+ diffrentAddKitReq.getCourseId());
//		SuccessResponse response = new SuccessResponse();
//
//		Optional<FranchiseDiffrentAddKitReq> findById = diffrentAddKitReqRepo
//				.findByFranchiseAndCourse(diffrentAddKitReq.getStudentId(), diffrentAddKitReq.getCourseId());
//
//		if (!findById.isPresent()) {
//			response.noFranchiseKitRequestFound();
//			return response;
//		}
//
//		FranchiseDiffrentAddKitReq franchiseDiffrentAddKitReq = findById.get();
//
//		Optional<FranchiseKitRequest> franchiseKitRequestOpt = franchiseKitRequestRepo.findByFranchiseIdAndCourseId(
//				franchiseDiffrentAddKitReq.getFranchiseId(), franchiseDiffrentAddKitReq.getCourseId());
//
//		if (!franchiseKitRequestOpt.isPresent()) {
//			response.setStatus(false);
//			response.setMessage("No Franchise Kit Request found for the provided Franchise ID and Course ID.");
//			return response;
//		}
//
//		FranchiseKitRequest franchiseKitRequest = franchiseKitRequestOpt.get();
//		Integer availableTotalKits = franchiseKitRequest.getAvelableTotalKits();
//
//		if (availableTotalKits == null || availableTotalKits <= 0) {
//			response.setStatus(false);
//			response.setMessage("No kits available to send.");
//			return response;
//		}
//
//		franchiseKitRequest.setProvidedKitsCountToFranchise(franchiseKitRequest.getProvidedKitsCountToFranchise() + 1);
//		franchiseKitRequest.setAvelableTotalKits(availableTotalKits - 1);
//		franchiseKitRequest.setRemainingKitsSendToFranchise(franchiseKitRequest.getRemainingKitsSendToFranchise() - 1);
//		franchiseKitRequest.setRemainingStudents(franchiseKitRequest.getRemainingStudents() - 1);
//		franchiseKitRequestRepo.save(franchiseKitRequest);
//
//		Optional<Course> courseOpt = courseRepo.findById(franchiseKitRequest.getCourseId());
//		if (courseOpt.isPresent()) {
//			Course course = courseOpt.get();
//			Integer no_of_books = course.getNo_of_books();
//
//			if (no_of_books != null && no_of_books > 0) {
//				course.setNo_of_books(no_of_books - 1);
//				courseRepo.save(course);
//			} else {
//				response.setStatus(false);
//				response.setMessage("No more books available for this course.");
//				return response;
//			}
//		} else {
//			response.setStatus(false);
//			response.setMessage("Course not found.");
//			return response;
//		}
//
//		Optional<Franchise> findById2 = franchiseRepo.findById(diffrentAddKitReq.getFranchiseId());
//		franchiseDiffrentAddKitReq.setFranchiseName(findById2.get().getFranchise_name());
//		franchiseDiffrentAddKitReq.setFranchiseMobile(findById2.get().getMobile_no());
//		franchiseDiffrentAddKitReq.setFranchise_owner(findById2.get().getFranchise_owner());
//		franchiseDiffrentAddKitReq.setTrackingNumber(diffrentAddKitReq.getTrackingNumber());
//		franchiseDiffrentAddKitReq.setKitOrderStatus(KitOrderStatus.PROVIDED);
//
//		FranchiseDiffrentAddKitReq savedRequest = diffrentAddKitReqRepo.save(franchiseDiffrentAddKitReq);
//
//		response.kitsSentSuccessfully(savedRequest);
//		return response;
//	}
//
//	@Override
//	public SuccessResponse getAllDifrentAddressKitReq() {
//		SuccessResponse response = new SuccessResponse();
//		List<FranchiseDiffrentAddKitReq> findAll = diffrentAddKitReqRepo.findAllOrderedRequest();
//		if (findAll.isEmpty()) {
//			response.noFranchiseKitRequestFound();
//			return response;
//		}
//		response.kitRequests(findAll);
//		return response;
//	}
//
//	public static String getCurrentDateString() {
//		// Define the date format (e.g., "yyyy-MM-dd")
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//		// Get the current date and format it
//		return LocalDate.now().format(formatter);
//	}
//
//
//
//	@Override
//	public SuccessResponse sendKitRequests(Long franchiseId, List<KitRequest> kitRequests) {
//		SuccessResponse response = new SuccessResponse();
//		List<KitOrderResponseDTO> kitOrderResponses = new ArrayList<>();
//
//		// Check if franchise exists
//		if (franchiseId == null || kitRequests == null || kitRequests.isEmpty()) {
//			response.nullData();
//			return response;
//		}
//
//		Optional<Franchise> franchiseOpt = franchiseRepo.findById(franchiseId);
//		if (!franchiseOpt.isPresent()) {
//			response.franchiseNotFound();
//			return response;
//		}
//		Franchise franchise = franchiseOpt.get();
//		for (KitRequest kitRequest : kitRequests) {
//			Integer kitCount = kitRequest.getKitCount();
//			Long courseId = kitRequest.getCourseId();
//			// Validation: Kit count should not be null or less than or equal to zero
//			if (kitCount == null || kitCount <= 0) {
//				response.kitCountZero();
//				return response;
//			}
//
//			// Check if the course exists
//			Optional<Course> courseOpt = courseRepo.findById(courseId);
//			if (!courseOpt.isPresent()) {
//				response.courseNotFound(courseId);
//				return response;
//			}
//			Course course = courseOpt.get();
//			System.out.println("Course Name " + course.getCourse_name());
//
//			// Check if the course is assigned to the franchise
//			if (!franchise.getCourses().contains(course)) {
//				response.courseNotAssignedToFranchise(courseId);
//				return response;
//			}
//
//			FranchiseKitRequestsLogs kitRequestsLogs = new FranchiseKitRequestsLogs();
//			kitRequestsLogs.setFranchise_id(franchiseId);
//			kitRequestsLogs.setFranchise_name(franchise.getFranchise_name());
//			kitRequestsLogs.setFranchise_number(franchise.getFranchise_number());
//			kitRequestsLogs.setFranchiseMobile(franchise.getMobile_no());
//			kitRequestsLogs.setCourseId(courseId);
//			kitRequestsLogs.setCourseName(course.getCourse_name());
//			kitRequestsLogs.setOrderedKits(kitCount);
//			kitRequestsLogs.setRequestedDate(getCurrentDateString());
//
//			// Check if there's an existing request for the same course
//			Optional<FranchiseKitRequest> existingRequestOpt = franchiseKitRequestRepo
//					.findByFranchiseIdAndCourseId(franchiseId, courseId);
//			FranchiseKitRequest franchiseKitRequest;
//			if (existingRequestOpt.isPresent()) {
//				franchiseKitRequest = existingRequestOpt.get();
//				franchiseKitRequest.setOrderedKits(franchiseKitRequest.getOrderedKits() + kitCount);
//				franchiseKitRequest.setRemainingStudents(franchiseKitRequest.getRemainingStudents() + kitCount);
//				franchiseKitRequest.setRemainingKitsSendToFranchise(
//						franchiseKitRequest.getRemainingKitsSendToFranchise() + kitCount);
//				franchiseKitRequest.setCourseName(course.getCourse_name());
//				franchiseKitRequest.setFranchiseMobile(franchise.getMobile_no());
//				franchiseKitRequest.setRequestedDate(getCurrentDateString());
//				franchiseKitRequest.setFranchiseMobile(franchise.getMobile_no());
//				franchiseKitRequest.setFranchise_owner(franchise.getFranchise_owner());
//			} else {
//				franchiseKitRequest = new FranchiseKitRequest();
//				franchiseKitRequest.setFranchise_id(franchiseId);
//				franchiseKitRequest.setFranchise_name(franchise.getFranchise_name());
//				franchiseKitRequest.setFranchiseMobile(franchise.getMobile_no());
//				franchiseKitRequest.setOrderedKits(kitCount);
//				franchiseKitRequest.setRemainingStudents(kitCount);
//				franchiseKitRequest.setRemainingKitsSendToFranchise(kitCount);
//				franchiseKitRequest.setCourseId(courseId);
//				franchiseKitRequest.setCourseName(course.getCourse_name());
//				franchiseKitRequest.setRequestedDate(getCurrentDateString());
//				franchiseKitRequest.setFranchise_owner(franchise.getFranchise_owner());
//
//			}
//
//			// Save logs and requests
//			requestsLogsRepo.save(kitRequestsLogs);
//			franchiseKitRequestRepo.save(franchiseKitRequest);
//
//			// Build KitOrderResponse for the current kit request
//			KitOrderResponseDTO kitOrderResponse = new KitOrderResponseDTO();
//			kitOrderResponse.setId(franchiseKitRequest.getId());
//			kitOrderResponse.setFranchise_name(franchise.getFranchise_name());
//			kitOrderResponse.setFranchise_number(franchise.getFranchise_number());
//			kitOrderResponse.setCourseName(course.getCourse_name());
//			kitOrderResponse.setRemainingStudents(franchiseKitRequest.getRemainingStudents());
//			kitOrderResponse.setOrderedKits(franchiseKitRequest.getOrderedKits());
//			kitOrderResponse.setFranchiseMobile(franchise.getMobile_no());
//			kitOrderResponse.setProvidedKitsCountToFranchise(0); // Adjust as per logic
//			kitOrderResponse.setAvelableTotalKits(76); // Adjust as per logic
//			kitOrderResponse.setRemainingKitsSendToFranchise(franchiseKitRequest.getRemainingKitsSendToFranchise());
//			kitOrderResponse.setTrackingNumber(null); // Adjust if applicable
//			kitOrderResponse.setCourseId(courseId);
//			kitOrderResponse.setFranchise_id(franchiseId);
//			kitOrderResponse.setRequestedDate(getCurrentDateString());
//			kitOrderResponse.setKitOrderStatus("ORDERED");
//
//			// Add the response object to the list
//			kitOrderResponses.add(kitOrderResponse);
//
//			// Send notification after each kit request
//			notificationsServiceImple.generateNotificationAfterFranchiseKitRequest(franchise, course.getCourse_name());
//		}
//
//		// Set the kitOrderResponses in the final response
//		response.setData(kitOrderResponses);
//		response.setStatus(true);
//		response.setMessage("KIT ORDER PLACED SUCCESSFULLY..!");
//		response.setStatusCode(HttpStatus.OK);
//		return response;
//	}
//
//
//
//	@Override
//	public SuccessResponse getAllKitRequestsUsingDate(String startDate, String endDate, Long franchiseId) {
//		SuccessResponse response = new SuccessResponse();
//		List<FranchiseKitRequestsLogs> allKitRequests;
//
//		// Case 1: If both dates are null, retrieve all requests based on franchiseId
//		if (startDate == null && endDate == null) {
//			if (franchiseId != null) {
//				allKitRequests = requestsLogsRepo.findAllKitRequestByFranchiseId(franchiseId);
//			} else {
//				allKitRequests = requestsLogsRepo.findAll(); // Retrieve all records when no filter is provided
//			}
//		} else {
//			if (franchiseId != null) {
//				System.out.println("fr id : " + franchiseId);
//				allKitRequests = requestsLogsRepo.findAllByRequestedDateBetweenAndFranchiseId(franchiseId, startDate,
//						endDate);
//			} else {
//				// Query using string start and end date and no franchiseId
//				System.out.println("without fr : ");
//				allKitRequests = requestsLogsRepo.findAllByRequestedDateBetweenWithoutFranchiseId(startDate, endDate);
//			}
//		}
//
//		// Check if no kit requests found and return appropriate response
//		if (allKitRequests == null || allKitRequests.isEmpty()) {
//			response.kitRequestNotFound();
//		} else {
//			response.getAllKitRequests(allKitRequests);
//		}
//
//		return response;
//	}
//
//
//
//	public SuccessResponse createOrder(FranchiseOrderDTO orderDTO) {
//		SuccessResponse response = new SuccessResponse();
//
//		// Validate franchise existence
//		Franchise franchise = franchiseRepo.findById(orderDTO.getFranchiseId())
//				.orElseThrow(() -> new RuntimeException("Franchise not found!"));
//
//		// Create and save FranchiseOrder
//		FranchiseOrder franchiseOrder = new FranchiseOrder();
//		franchiseOrder.setFranchise(franchise);
//		franchiseOrder.setOrderTime(new Date());
//
//		FranchiseOrder savedFranchiseOrder = franchiseOrderRepo.save(franchiseOrder);
//
//		// Create ProductOrderRequest list
//		List<ProductOrderRequest> productRequests = orderDTO.getProductRequests().stream().map(requestDTO -> {
//			Products product = productsRepo.findById(requestDTO.getProductId())
//					.orElseThrow(() -> new RuntimeException("Product not found!"));
//
//			ProductOrderRequest orderRequest = new ProductOrderRequest();
//			orderRequest.setFranchise(franchise);
//			orderRequest.setProduct(product);
//			orderRequest.setFranchiseOrder(savedFranchiseOrder);
//			orderRequest.setRequestedQuantity(requestDTO.getRequestedQuantity());
//			orderRequest.setRemainingQuantity(requestDTO.getRequestedQuantity());
//			orderRequest.setTotalProductQuantity(product.getQuantity()); // Set total quantity from the product table
//			orderRequest.setRequestTime(new Date());
//			orderRequest.setUpdateTime(new Date());
//
//			return orderRequest;
//		}).collect(Collectors.toList());
//
//		// Save all ProductOrderRequests
//		List<ProductOrderRequest> savedRequests = productOrderRequestRepo.saveAll(productRequests);
//
//		// Map saved requests to DTOs
//		List<ProductRequestDTO> saveDTO = savedRequests.stream().map(order -> new ProductRequestDTO(order.getId(),
//				order.getProduct().getId(), order.getProduct().getProductName(), order.getRequestedQuantity(),
//				order.getSentQuantity(), order.getRemainingQuantity(), order.getFranchise().getFranchise_id(),
//				order.getFranchise().getFranchise_name(), order.getRequestTime(), order.getTotalProductQuantity(), // Include
//																													// the
//																													// total
//																													// product
//																													// quantity
//				order.getTrackingNumber())).collect(Collectors.toList());
//
//		// Prepare response
//		response.success("Order created successfully!");
//		response.setResponse(saveDTO);
//
//		return response;
//	}
//
//	@Override
//	public SuccessResponse getOrdersByFranchise(Long franchiseId) {
//		SuccessResponse response = new SuccessResponse();
//
//		// Retrieve all orders
//		List<ProductOrderRequest> orders = productOrderRequestRepo.findAll().stream()
//				.filter(order -> order.getFranchise().getFranchise_id().equals(franchiseId))
//				.collect(Collectors.toList());
//
//		if (orders.isEmpty()) {
//			response.notFound("No orders found for the franchise!");
//			return response;
//		}
//
//		// Map to DTO
//		List<ProductRequestDTO> orderDTOs = orders.stream()
//				.map(order -> new ProductRequestDTO(order.getId(), order.getProduct().getId(),
//						order.getProduct().getProductName(), order.getRequestedQuantity(), order.getSentQuantity(),
//						order.getRemainingQuantity(), order.getFranchise().getFranchise_id(),
//						order.getFranchise().getFranchise_name(), order.getRequestTime(),
//						order.getTotalProductQuantity(), order.getTrackingNumber()))
//				.collect(Collectors.toList());
//		response.success("Orders retrieved successfully!", orderDTOs);
//		return response;
//	}
//
//	@Override
//	public SuccessResponse addStudentWithKitRequestOnDiffrentAdd(Student student, MultipartFile studentPhoto,HttpServletRequest request)
//			throws IOException {
//		SuccessResponse response = new SuccessResponse();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		LocalDateTime now = LocalDateTime.now();
//		if (student.getDob() == null || student.getFirst_name() == null || student.getLast_name() == null
//				|| student.getMobile_no() == null) {
//			response.nullDataForStudent(student);
//			return response;
//		}
//		Optional<Student> byEmailID = studentRepo.findByEmail(student.getEmail());
//		if (byEmailID.isPresent() && !byEmailID.get().getStudent_id().equals(student.getStudent_id())) {
//			response.emailAlreadyExist();
//			return response;
//		}
//		Optional<Student> byMobileNo = studentRepo.findByMobileNumber(student.getMobile_no());
//		if (byMobileNo.isPresent() && !byMobileNo.get().getStudent_id().equals(student.getStudent_id())) {
//			response.mobileAlreadyExist();
//			return response;
//		}
//		if (student.getFranchise() != null) {
//			Optional<Franchise> franchiseOpt = franchiseRepo.findById(student.getFranchise().getFranchise_id());
//			if (franchiseOpt.isPresent()) {
//				student.setFranchise(franchiseOpt.get());
//			} else {
//				response.franchiseNotFound();
//				return response;
//			}
//		}
//		List<Course> attachedCourses = new ArrayList<>();
//		for (Course course : student.getCourses()) {
//			if (course.getCourse_id() != null) {
//				Course existingCourse = courseRepo.findcourseById(course.getCourse_id()).orElse(null);
//				if (existingCourse != null) {
//					FranchiseKitRequest kitRequest = franchiseKitRequestRepo.findByFranchiseIdAndCourseId(
//							student.getFranchise().getFranchise_id(), existingCourse.getCourse_id()).orElse(null);
//					if (kitRequest == null) {
//						response.orderKitFirst(existingCourse.getCourse_id());
//						return response;
//					}
//					if (kitRequest.getRemainingStudents() <= 0) {
//						response.insufficientKits();
//						return response;
//					}
//					kitRequest.setRemainingStudents(kitRequest.getRemainingStudents() - 1);
//					franchiseKitRequestRepo.save(kitRequest);
//					System.out.println("new course optional : " + existingCourse.getCourse_name());
//					attachedCourses.add(existingCourse);
//				} else {
//					response.courseNotFound(course.getCourse_id());
//					return response;
//				}
//			}
//		}
//		if (student.getStudent_id() != null) {
//			Optional<Student> existingStudentOpt = studentRepo.findStudentById(student.getStudent_id());
//			if (existingStudentOpt.isPresent()) {
//				Student existingStudent = existingStudentOpt.get();
//				if (student.getFirst_name() != null)
//					existingStudent.setFirst_name(student.getFirst_name());
//				if (student.getLast_name() != null)
//					existingStudent.setLast_name(student.getLast_name());
//				if (student.getMobile_no() != null)
//					existingStudent.setMobile_no(student.getMobile_no());
//				if (student.getEmail() != null)
//					existingStudent.setEmail(student.getEmail());
//				if (student.getDob() != null)
//					existingStudent.setDob(student.getDob());
//				if (student.getPassword() != null)
//					existingStudent.setPassword(student.getPassword());
//				if (student.getFranchise() != null)
//					existingStudent.setFranchise(student.getFranchise());
//				if (student.getAddress() != null) {
//					if (existingStudent.getAddress() == null) {
//						existingStudent.setAddress(student.getAddress());
//					} else {
//						if (student.getAddress().getCity() != null)
//							existingStudent.getAddress().setCity(student.getAddress().getCity());
//						if (student.getAddress().getState() != null)
//							existingStudent.getAddress().setState(student.getAddress().getState());
//						if (student.getAddress().getDistrict() != null)
//							existingStudent.getAddress().setDistrict(student.getAddress().getDistrict());
//					}
//				}
//				if (studentPhoto != null && !studentPhoto.isEmpty()) {
//					if (existingStudent.getProfile_image_name() != null) {
////						s3BucketService.deleteFile(existingStudent.getProfile_image_name());
//						ImageStoreProcess.deleteFile(existingStudent.getProfile_image_link(), existingStudent.getProfile_image_name());
//
//					}
//
////					StoredImages storedImages = s3BucketService.storeFile(studentPhoto.getOriginalFilename(),
////							studentPhoto.getInputStream(), studentPhoto.getSize(), 1);
////					existingStudent.setProfile_image_link(storedImages.getProfile_image_link());
////					existingStudent.setProfile_image_name(storedImages.getProfile_image_name());
//
//					List<String> saveFile = ImageStoreProcess.saveFile(studentPhoto, request);
//
//					if(saveFile != null) {
//						existingStudent.setProfile_image_link(saveFile.get(1));
//						existingStudent.setProfile_image_name(saveFile.get(0));
//					}
//
//
//
//				}
//				existingStudent.setModification_time(formatter.format(now));
//				existingStudent.getCourses().addAll(attachedCourses);
//				studentRepo.save(existingStudent);
//				StudentDTO studentDto = modelMapper.map(existingStudent, StudentDTO.class);
//				if (existingStudent.getFranchise() != null) {
//					studentDto.setFranchiseName(existingStudent.getFranchise().getFranchise_name());
//				}
//				response.studentUpdated(studentDto);
//				return response;
//			} else {
//				response.idNotFound();
//				return response;
//			}
//		}
//
//		if (attachedCourses.isEmpty()) {
//			response.courseNotSelected();
//			return response;
//		}
//		if (studentPhoto == null || studentPhoto.isEmpty()) {
//			response.nullFile();
//			return response;
//		}
//		List<String> saveFile = ImageStoreProcess.saveFile(studentPhoto, request);
//
//		student.setProfile_image_name(saveFile.get(0));
//		student.setProfile_image_link(saveFile.get(1));
//
//		student.setCreation_time(formatter.format(now));
//		student.setCourses(attachedCourses);
//		student.setCurrentCourseId(attachedCourses.get(0).getCourse_id());
//		student.setCurrentCourseName(attachedCourses.get(0).getCourse_name());
//		Student savedStudent = studentRepo.save(student);
//		FranchiseDiffrentAddKitReq diffrentAddKitReq = new FranchiseDiffrentAddKitReq();
//		diffrentAddKitReq.setAddress(student.getAddress().getLine1() + "," + student.getAddress().getCity() + ","
//				+ student.getAddress().getDistrict() + "," + student.getAddress().getState() + ","
//				+ student.getAddress().getCountry() + "," + student.getAddress().getPincode());
//		diffrentAddKitReq.setCourseId(attachedCourses.get(0).getCourse_id());
//		diffrentAddKitReq.setCourseName(attachedCourses.get(0).getCourse_name());
//		diffrentAddKitReq.setFranchiseId(student.getFranchise().getFranchise_id());
//		diffrentAddKitReq.setFranchiseMobile(student.getFranchise().getMobile_no());
//		diffrentAddKitReq.setFranchiseName(student.getFranchise().getFranchise_name());
//		diffrentAddKitReq.setStudentId(student.getStudent_id());
//		diffrentAddKitReq.setKitOrderStatus(KitOrderStatus.ORDERED);
//		diffrentAddKitReq.setStudentName(student.getFirst_name() + " " + student.getLast_name());
//		diffrentAddKitReqRepo.save(diffrentAddKitReq);
//		StudentDTO studentDto = modelMapper.map(student, StudentDTO.class);
//		if (student.getFranchise() != null) {
//			studentDto.setFranchiseName(student.getFranchise().getFranchise_name());
//		}
//		response.saveTheStudent(savedStudent);
//		return response;
//
//	}
//
//}
