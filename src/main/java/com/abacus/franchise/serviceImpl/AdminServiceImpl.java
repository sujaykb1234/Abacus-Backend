package com.abacus.franchise.serviceImpl;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abacus.franchise.dto.AdminDto;
import com.abacus.franchise.dto.AdminProductDispachedDTO;
import com.abacus.franchise.dto.FranchiseUserPassResponse;
import com.abacus.franchise.dto.ProductDTO;
import com.abacus.franchise.dto.ProductDispatchDetails;
import com.abacus.franchise.dto.ReattemptRequestDTO;
import com.abacus.franchise.dto.StudentDTO;
import com.abacus.franchise.model.Admin;
import com.abacus.franchise.model.Course;
import com.abacus.franchise.model.Franchise;
import com.abacus.franchise.model.FranchiseKitRequest;
import com.abacus.franchise.model.FranchiseOrder;
import com.abacus.franchise.model.OfflineExamUpload;
import com.abacus.franchise.model.ProductOrderRequest;
import com.abacus.franchise.model.Products;
import com.abacus.franchise.model.Student;
import com.abacus.franchise.model.StudentExam;
import com.abacus.franchise.repo.AdminNotificationRepo;
import com.abacus.franchise.repo.AdminRepo;
import com.abacus.franchise.repo.CourseRepo;
import com.abacus.franchise.repo.FranchiseKitRequestRepo;
import com.abacus.franchise.repo.FranchiseOrderRepo;
import com.abacus.franchise.repo.FranchiseRepo;
import com.abacus.franchise.repo.OfflineExamUploadRepo;
import com.abacus.franchise.repo.ProductOrderRequestRepo;
import com.abacus.franchise.repo.ProductsRepo;
import com.abacus.franchise.repo.StoredImagesRepo;
import com.abacus.franchise.repo.StudentExamRepository;
import com.abacus.franchise.repo.StudentRepo;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.AdminService;
import com.abacus.franchise.utility.FranchiseStatus;
import com.abacus.franchise.utility.ImageStoreProcess;
import com.abacus.franchise.utility.KitOrderStatus;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepo adminRepo;

	@Autowired
	StudentRepo studentRepo;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	FranchiseRepo franchiseRepo;

	@Autowired
	FranchiseKitRequestRepo franchiseKitRequestRepo;

	@Autowired
	AdminNotificationRepo adminNotificationRepo;

	@Autowired
	CourseRepo courseRepo;

	@Autowired
	ProductOrderRequestRepo productOrderRequestRepo;

	@Autowired
	ProductsRepo productsRepo;

	@Autowired
	FranchiseOrderRepo franchiseOrderRepo;

	@Autowired
	StudentExamRepository studentExamRepository;

	@Autowired
	StoredImagesRepo imagesRepo;

	@Autowired
	OfflineExamUploadRepo offlineExamUploadRepo;
	
	

	@Override
	public SuccessResponse loginTheAdmin(Admin admin) {
		SuccessResponse response = new SuccessResponse();
		System.out.println("Entered Mobile No: " + admin.getAdmin_mobileNo());

		if (admin.getAdmin_mobileNo() == null || admin.getAdmin_password() == null) {
			System.out.println("Mobile Number: " + admin.getAdmin_mobileNo());
			System.out.println("Password: " + admin.getAdmin_password());
			response.nullAdminnameAndPass();
			return response;
		}
		String adminUsername = admin.getAdmin_mobileNo();
		String adminPassword = admin.getAdmin_password();

		System.out.println("Admin Username: " + adminUsername);
		Optional<Admin> byUsername = adminRepo.findByusername(adminUsername);

		if (byUsername.isPresent()) {
			Admin adminFromDb = byUsername.get();

			AdminDto adminDto = modelMapper.map(adminFromDb, AdminDto.class);

			if (adminPassword.equals(adminFromDb.getAdmin_password())) {
				response.loginSuccessfully(adminDto);
			} else {
				response.wrongPassword();
			}
		} else {
			response.userNotFound();
		}

		return response;
	}

//-----------------------------------------------------------------------------------------------------------------------------------	

	@Override
	public SuccessResponse setTheNewPassword(Admin admin) {

		SuccessResponse response = new SuccessResponse();
		if (admin.getAdmin_emailId() == null) {
			response.emailNotFound();
			return response;

		}
		String loginusername = admin.getAdmin_emailId();
		String loginpassword = admin.getAdmin_password();
		Optional<Admin> byUserName = adminRepo.findByusernameForPassword(loginusername);
		if (byUserName.isPresent()) {
			if (loginpassword == null) {
				response.passwordNull();
				return response;
			}
			if (loginpassword.length() < 6) {
				response.lengofpassword();
				return response;
			}
			Admin admin2 = byUserName.get();
			admin2.setAdmin_password(loginpassword);
			admin2.setCreation_time(byUserName.get().getCreation_time());
			adminRepo.save(admin2);
			AdminDto newPassword = modelMapper.map(admin2, AdminDto.class);
			response.passwordUpdateSuccesfully();
			return response;

		} else {
			response.incorrectUserName();
		}
		return response;
	}

	@Override
	public SuccessResponse acceptOrRejectRequest(Long franchiseId, FranchiseStatus franchiseStatus) {
		SuccessResponse response = new SuccessResponse();

		if (franchiseId == null || franchiseStatus == null) {
			response.nullData();
			return response;
		}

		Optional<Franchise> optionalFranchise = franchiseRepo.findById(franchiseId);
		if (optionalFranchise.isEmpty()) {
			response.franchiseNotFound();
			return response;
		}

		Franchise franchise = optionalFranchise.get();
		franchise.setFranchiseStatus(franchiseStatus);
		if (franchiseStatus == FranchiseStatus.ACCEPTED) {
			SuccessResponse userPassResponse = generateUniqueUserAndPass(franchiseId);

			if (userPassResponse != null) {
//				franchise.setUserName(franchiseUsername);
				franchise.setFranchise_password(frachisePassword);
			}
		}
		franchiseRepo.save(franchise);
		FranchiseUserPassResponse map = modelMapper.map(franchise, FranchiseUserPassResponse.class);
		String message = franchiseStatus == FranchiseStatus.ACCEPTED ? "FRANCHISE ACCEPTED SUCCESSFULLY..!"
				: "FRANCHISE REJECTED SUCCESSFULLY..!";
		response.success(message, map);
		System.out.println(map.getFranchise_name() + franchiseUsername);
		return response;
	}

//-----------------------------------------------------------------------------------------------------------------------	
	@Override
	public SuccessResponse getKitRequests() {
		SuccessResponse response = new SuccessResponse();
		List<FranchiseKitRequest> activeKitRequests = franchiseKitRequestRepo.findAllKitRequests();
		
		if (activeKitRequests.isEmpty()) {
			response.noKitReq();
		} else {
			response.kitRequests(activeKitRequests);
		}
		return response;
	}

	private String franchiseUsername;
	private String frachisePassword;

	@Override
	public SuccessResponse generateUniqueUserAndPass(Long franchiseId) {
		SuccessResponse response = new SuccessResponse();

		Optional<Franchise> optionalFranchise = franchiseRepo.findById(franchiseId);
		if (optionalFranchise.isEmpty()) {
			response.franchiseNotFound();
			return response;
		}

		Franchise franchise = optionalFranchise.get();

		// Generate unique username
//		franchiseUsername = franchise.getFranchise_name().toLowerCase().replaceAll("\\s+", "") + "_" + franchiseId;
		frachisePassword = generateRandomPassword(6);
		franchiseUsername = optionalFranchise.get().getMobile_no();

		franchiseRepo.save(franchise);

		response.setMessage("Username and password generated successfully.");
		return response;
	}

	String generateRandomPassword(int length) {
		SecureRandom random = new SecureRandom();
		StringBuilder password = new StringBuilder(length);
		String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < length; i++) {
			password.append(characters.charAt(random.nextInt(characters.length())));
		}

		return password.toString();
	}

	@Override
	@Transactional
	public SuccessResponse sendKitsToFranch(FranchiseKitRequest franchiseKitRequest) {
		SuccessResponse response = new SuccessResponse();
		if (franchiseKitRequest.getCourseId() == null || franchiseKitRequest.getFranchise_id() == null
				|| franchiseKitRequest.getProvidedKitsCountToFranchise() == null) {
			response.nullData();
			return response;
		}
		Optional<Course> courseOpt = courseRepo.findById(franchiseKitRequest.getCourseId());
		if (!courseOpt.isPresent()) {
			response.courseNotFound(null);
			return response;
		}
		Optional<Franchise> franchiseOpt = franchiseRepo.findById(franchiseKitRequest.getFranchise_id());
		if (!franchiseOpt.isPresent()) {
			response.franchiseNotFound();
			return response;
		}
		Optional<FranchiseKitRequest> franchiseKitRequestOpt = franchiseKitRequestRepo
				.findByFranchiseIdAndCourseId(franchiseKitRequest.getFranchise_id(), franchiseKitRequest.getCourseId());
		if (!franchiseKitRequestOpt.isPresent()) {
			response.noFranchiseKitRequestFound();
			return response;
		}
		FranchiseKitRequest existingRequest = franchiseKitRequestOpt.get();
		Course course = courseOpt.get();
		int requestedKits = franchiseKitRequest.getProvidedKitsCountToFranchise();
		int totalKitsProvidedSoFar = existingRequest.getProvidedKitsCountToFranchise();
		int totalKitsToBeProvided = totalKitsProvidedSoFar + requestedKits;
		if (totalKitsToBeProvided > existingRequest.getOrderedKits()) {
			response.setMessage("You cannot send more kits than ordered. Remaining kits to send: "
					+ (existingRequest.getOrderedKits() - totalKitsProvidedSoFar));
			response.setStatus(true);
			response.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
			return response;
		}
		if (course.getNo_of_books() < requestedKits) {
			response.notEnoughKitsAvailable();
			return response;
		}
		int updatedBookCount = course.getNo_of_books() - requestedKits;
		course.setNo_of_books(updatedBookCount);
		courseRepo.save(course);
		existingRequest.setProvidedKitsCountToFranchise(totalKitsToBeProvided);
		int remainingKits = (int) (existingRequest.getOrderedKits() - totalKitsToBeProvided);
		existingRequest.setRemainingKitsSendToFranchise(remainingKits);
		existingRequest.setAvelableTotalKits(updatedBookCount);
		existingRequest.setTrackingNumber(franchiseKitRequest.getTrackingNumber());
		if (existingRequest.getRemainingKitsSendToFranchise() < 1) {
			existingRequest.setKitOrderStatus(KitOrderStatus.PROVIDED);
		} else {
			existingRequest.setKitOrderStatus(KitOrderStatus.REMAINING);
		}
		
		FranchiseKitRequest savedRequest = franchiseKitRequestRepo.save(existingRequest);
		response.kitsSentSuccessfully(savedRequest);
		return response;
	}

	@Override
	public SuccessResponse getAllReattemptRequests() {
		SuccessResponse response = new SuccessResponse();
		List<StudentExam> students = studentExamRepository.findAllRequestForReAttempt();
		if (students.isEmpty()) {
			response.noStudentsFound();
			return response;
		}
		List<ReattemptRequestDTO> studentExamDTOs = students.stream().map(this::mapStudentExamToReattemptRequestDTO)
				.collect(Collectors.toList());
		response.setMessage("STUDENT REQUESTS RETRIVED SUCCESSFULLY..!");
		response.setResponse(studentExamDTOs);
		response.setStatus(true);
		return response;
	}

	public ReattemptRequestDTO mapStudentExamToReattemptRequestDTO(StudentExam studentExam) {

		String findFranchiseName = franchiseRepo.findFranchiseName(studentExam.getFranchiseId());
		Optional<Student> findByid2 = studentRepo.findById(studentExam.getStudent().getStudent_id());
		ReattemptRequestDTO dto = new ReattemptRequestDTO();
		dto.setCourseId(studentExam.getCourse_id());
		dto.setExamName(studentExam.getExamName());
		dto.setFranchiseName(findFranchiseName);
		dto.setMobileNumber(findByid2.get().getMobile_no());
		dto.setFranchiseId(studentExam.getFranchiseId());
		dto.setExamId(studentExam.getExamAttempt().getAttemptId());
		dto.setReattemptStatus(studentExam.getReattemptStatus());
		dto.setStudentId(studentExam.getStudent().getStudent_id());
		dto.setStudentName(studentExam.getStudent().getFirst_name());
		dto.setExamStatus(studentExam.getExamstatus());
		dto.setExamTime(studentExam.getExamTime());
		return dto;
	}

	@Override
	public SuccessResponse getExamCompletedStudents(Long franchiseId) {
		SuccessResponse response = new SuccessResponse();
		if (franchiseId == null) {
			response.nullData();
			return response;
		}
		List<Student> findAllExamCompletedStudentByFranchise = studentRepo
				.findAllExamCompletedStudentByFranchise(franchiseId);
		if (findAllExamCompletedStudentByFranchise.isEmpty()) {
			response.noStudentsFound();
			return response;
		}
		response.examForStudentsRetrived(findAllExamCompletedStudentByFranchise);
		return response;
	}



	@Override
	public SuccessResponse uploadOfflineExamPDF(Long courseId, MultipartFile questionPaper,HttpServletRequest request) {
		SuccessResponse response = new SuccessResponse();
		try {
			if (courseId == null || questionPaper == null || questionPaper.isEmpty()) {
				response.nullData();
				return response;
			}
			Optional<Course> findByCourseId = courseRepo.findById(courseId);
			if (!findByCourseId.isPresent()) {
				response.courseNotFound(courseId);
				return response;
			}
			Optional<OfflineExamUpload> existingExam = offlineExamUploadRepo.findByCourseId(courseId);
			if (existingExam.isPresent()) {
				response.setMessage(
						"You cannot upload two exams for one course. Please delete the existing exam before uploading a new one.");
				response.setStatus(false);
				response.setStatusCode(HttpStatus.ALREADY_REPORTED);
				return response;
			}
//			StoredImages storedQuestionPaper = bucketService.storeFile(questionPaper.getOriginalFilename(),
//					questionPaper.getInputStream(), questionPaper.getSize(), 6);
			
//			System.out.println("pdf link : " + storedQuestionPaper.getPdfLink());
			
    	    OfflineExamUpload examUpload = new OfflineExamUpload();

			 if(questionPaper != null && !questionPaper.isEmpty()) {
				 
				 List<String> saveFile = ImageStoreProcess.saveFile(questionPaper, request);
				 
				 if(!saveFile.isEmpty()) {
				 
		  			examUpload.setCourseId(courseId);
		    	      
		  			if(saveFile != null) {
			    	  	examUpload.setPdfImageName(saveFile.get(0));
						examUpload.setPdfImageLink(saveFile.get(1));
		  			}
					examUpload.setCreatinTime(new Date());
					offlineExamUploadRepo.save(examUpload);

				 }else {
					 System.out.println("File Not Save");
				 }  
			
			    }else {
				  response.imageNotFound();
				  return response;
			    }
			
		

			// Prepare the response
			response.setMessage("Offline exam PDF uploaded successfully.");
			response.setStatus(true);
			response.setStatusCode(HttpStatus.OK);
			response.setData(examUpload);
			return response;

		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage("An error occurred while uploading the offline exam PDF.");
			response.setStatus(false);
			response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@Override
	public SuccessResponse getOfflineExamPdf(Long courseId) {
		SuccessResponse response = new SuccessResponse();
		if (courseId == null) {
			response.nullData();
			return response;
		}
		Optional<Course> findById = courseRepo.findById(courseId);
		if (!findById.isPresent()) {
			response.courseNotFound(courseId);
			return response;
		}
		Optional<OfflineExamUpload> findByCourseId = offlineExamUploadRepo.findByCourseId(courseId);
		if (!findByCourseId.isPresent()) {
			response.ExamNotFound();
			return response;
		}
		response.examFound(findByCourseId);
		return response;
	}

	@Override
	public SuccessResponse deleteFinalPaper(Long paperId) {
		SuccessResponse response = new SuccessResponse();
		if (paperId == null) {
			response.nullData();
			return response;
		}

		Optional<OfflineExamUpload> findById = offlineExamUploadRepo.findById(paperId);
		if (!findById.isPresent()) {
			response.questionPaperNotFound();
			return response;
		}
		OfflineExamUpload offlineExamUpload = findById.get();
		
//		bucketService.deleteFile(offlineExamUpload.getPdfImageName());
		
		ImageStoreProcess.deleteFile(offlineExamUpload.getPdfImageLink(), offlineExamUpload.getPdfImageName());
		
		offlineExamUploadRepo.deleteById(paperId);
		response.paperDeleted(offlineExamUpload);
		return response;
	}

	@Override
	public SuccessResponse getAllStudentsForAdmin() {
		SuccessResponse response = new SuccessResponse();
		List<Student> findAllActive = studentRepo.findAllActive();
		if (findAllActive.isEmpty()) {
			response.studentNotFound();
			return response;
		}
		List<StudentDTO> studentDTOList = findAllActive.stream().map(student -> {
			// Map the student entity to the StudentDTO
			StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
			if (student.getFranchise() != null) {
				studentDTO.setFranchiseName(student.getFranchise().getFranchise_name());
			}
			return studentDTO;
		}).collect(Collectors.toList());
		response.setMessage("Students fetched successfully");
		response.setStatus(true);
		response.setResponse(studentDTOList);
		return response;

	}

	@Override
	public SuccessResponse saveOrUpdateProduct(Products products) {
		SuccessResponse response = new SuccessResponse();
		if (products == null || products.getProductName() == null || products.getQuantity() == null) {
			response.nullData();
			return response;
		}

		if (products.getId() != null) { // Check if it's an update
			Optional<Products> existingProductOpt = productsRepo.findById(products.getId());
			if (existingProductOpt.isEmpty()) {
				response.notFound("Product not found with id: " + products.getId());
				return response;
			}

			Products existingProduct = existingProductOpt.get();
			if (products.getProductName() != null) {
				existingProduct.setProductName(products.getProductName());
			}
			if (products.getQuantity() != null && products.getQuantity() > 0) {
				existingProduct.setQuantity(products.getQuantity());
			}
			existingProduct.setUpdationTime(new Date());
			Products savedProduct = productsRepo.save(existingProduct);

			// Update totalProductQuantity in ProductOrderRequest
			List<ProductOrderRequest> orderRequests = productOrderRequestRepo.findAllByProductId(products.getId());
			for (ProductOrderRequest orderRequest : orderRequests) {
				orderRequest.setTotalProductQuantity(products.getQuantity());
				orderRequest.setUpdateTime(new Date());
			}
			productOrderRequestRepo.saveAll(orderRequests);

			response.success("Product updated successfully!", savedProduct);
		} else { // Save as a new product
			products.setCreatingTime(new Date());
			Products savedProduct = productsRepo.save(products);
			response.productSaved(savedProduct);
		}

		return response;
	}

	@Override
	public SuccessResponse deleteProduct(Long id) {
		SuccessResponse response = new SuccessResponse();

		if (id == null) {
			response.nullData();
			return response;
		}
		Optional<Products> product = productsRepo.findById(id);
		if (product.isEmpty()) {
			response.notFound("Product not found with id: " + id);
			return response;
		}
		Products products = product.get();
		products.setStatus(false);
		productsRepo.save(products);
		response.success("Product deleted successfully!");
		return response;
	}

	@Override
	public SuccessResponse getAllProducts() {
		SuccessResponse response = new SuccessResponse();

		List<Products> products = productsRepo.findAllActiveProduct();
		if (products == null || products.isEmpty()) {
			response.notFound("No products found!");
			return response;
		}
		response.success("Products retrieved successfully!", products);
		return response;
	}

	@Override
	public SuccessResponse getAllProductOrders() {
		SuccessResponse response = new SuccessResponse();
		List<ProductOrderRequest> productOrderRequests = productOrderRequestRepo.findAll();

		if (productOrderRequests.isEmpty()) {
			response.productRequestNotFound();
			return response;
		}

		// Grouping ProductOrderRequests by FranchiseOrder
		Map<Long, List<ProductOrderRequest>> groupedOrders = productOrderRequests.stream()
				.collect(Collectors.groupingBy(order -> order.getFranchiseOrder().getId()));

		// Mapping grouped data to AdminProductDispachedDTO
		List<AdminProductDispachedDTO> adminProductDispachedDTOs = groupedOrders.entrySet().stream().map(entry -> {
			Long franchiseOrderId = entry.getKey();
			List<ProductOrderRequest> orders = entry.getValue();

			AdminProductDispachedDTO dto = new AdminProductDispachedDTO();
			dto.setFranchiseOrderId(franchiseOrderId);
			dto.setOrderTime(orders.get(0).getFranchiseOrder().getOrderTime());
			dto.setFranchiseName(orders.get(0).getFranchise().getFranchise_name());
			dto.setTrackingNumber(orders.get(0).getTrackingNumber());
			dto.setFranchiseMobile(orders.get(0).getFranchise().getMobile_no());

			// Map products
			List<ProductDTO> products = orders.stream().map(order -> {
				ProductDTO productDTO = new ProductDTO();
				productDTO.setProductId(order.getProduct().getId());
				productDTO.setProductName(order.getProduct().getProductName());
				productDTO.setTotalProductQuantity(order.getProduct().getQuantity());
				productDTO.setRemainingQuantity(order.getRemainingQuantity());
				productDTO.setSentQuantity(order.getSentQuantity());
				productDTO.setRequestedQuantity(order.getRequestedQuantity());
				productDTO.setUpdateTime(order.getUpdateTime());

				return productDTO;
			}).collect(Collectors.toList());

			dto.setProducts(products);
			return dto;
		}).collect(Collectors.toList());

		response.setMessage("All product Requests..");
		response.setStatusCode(HttpStatus.OK);
		response.setStatus(true);
		response.setData(adminProductDispachedDTOs);
		return response;
	}



	@Override
	public SuccessResponse dispatchProducts(AdminProductDispachedDTO dispatchDTO) {
		SuccessResponse response = new SuccessResponse();

		System.out.println("Dispatched DTO response: " + dispatchDTO);

		// Retrieve the FranchiseOrder
		FranchiseOrder franchiseOrder = franchiseOrderRepo.findById(dispatchDTO.getFranchiseOrderId()).orElse(null);
		if (franchiseOrder == null) {
			response.setMessage("Franchise order not found.");
			response.setStatus(false);
			response.setStatusCode(HttpStatus.NOT_FOUND);
			return response;
		}

		List<ProductDTO> productRequests = dispatchDTO.getProducts();
		if (productRequests == null) {
			response.setMessage("Product list is empty.");
			response.setStatus(false);
			response.setStatusCode(HttpStatus.BAD_REQUEST);
			return response;
		}

		List<String> unavailableProducts = new ArrayList<>();
		List<ProductDispatchDetails> dispatchedProducts = new ArrayList<>();

		// Validate product availability before processing dispatch
		for (ProductDTO productRequest : productRequests) {
			Products product = productsRepo.findById(productRequest.getProductId()).orElse(null);

			if (product == null) {
				unavailableProducts.add("Product with ID " + productRequest.getProductId() + " not found.");
				continue;
			}

			ProductOrderRequest orderRequest = productOrderRequestRepo
					.findByFranchiseOrderAndProduct(franchiseOrder.getId(), product.getId()).orElse(null);

			System.out.println("franchise order id :" + orderRequest.getFranchiseOrder().getId());
			System.out.println("product id : " + productRequest.getProductId());
			System.out.println("product requested quentity : " + productRequest.getRequestedQuantity());
			if (orderRequest == null || productRequest.getRequestedQuantity() > product.getQuantity()
					|| productRequest.getRequestedQuantity() > orderRequest.getRemainingQuantity()) {
				unavailableProducts.add(product.getProductName());
				continue;
			}

			// Process product dispatch
			int availableQuantity = product.getQuantity();
			int quantityToSend = productRequest.getRequestedQuantity();

			// Deduct the sent quantity from inventory and update order request
			product.setQuantity(availableQuantity - quantityToSend);
			productsRepo.save(product);

			orderRequest.setTrackingNumber(dispatchDTO.getTrackingNumber());
			orderRequest.setSentQuantity(orderRequest.getSentQuantity() + quantityToSend);
			orderRequest.setRemainingQuantity(orderRequest.getRemainingQuantity() - quantityToSend);
			orderRequest.setUpdateTime(new Date());
			productOrderRequestRepo.save(orderRequest);

			// Add to dispatched product details
			dispatchedProducts.add(new ProductDispatchDetails(product.getProductName(), quantityToSend,
					product.getQuantity(), dispatchDTO.getTrackingNumber()));
		}

		if (!unavailableProducts.isEmpty()) {
			response.setMessage(
					"Insufficient stock for the following products: " + String.join(", ", unavailableProducts));
			response.setStatus(false);
			response.setStatusCode(HttpStatus.BAD_REQUEST);
			return response;
		}

		response.setMessage("All products dispatched successfully.");
		response.setStatus(true);
		response.setStatusCode(HttpStatus.OK);
		response.setData(dispatchedProducts); // Include the dispatched product details in the response

		return response;
	}

}