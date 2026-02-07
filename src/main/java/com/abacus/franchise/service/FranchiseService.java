package com.abacus.franchise.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abacus.franchise.dto.FranchiseOrderDTO;
import com.abacus.franchise.exception.DataNotValidException;
import com.abacus.franchise.model.Franchise;
import com.abacus.franchise.model.FranchiseDiffrentAddKitReq;
import com.abacus.franchise.model.KitRequest;
import com.abacus.franchise.model.Student;
import com.abacus.franchise.response.SuccessResponse;

import jakarta.servlet.http.HttpServletRequest;

@Service
public interface FranchiseService {

	public SuccessResponse registerAndUpdateTheFrinchise(Franchise franchise, MultipartFile franchies_owner_pic,
			MultipartFile franchies_owner_id_photo,HttpServletRequest request);

	SuccessResponse loginTheFranchise(Franchise franchise);

	public SuccessResponse setTheNewPasswordForFranchies(Franchise franchise);

	public SuccessResponse getThefranchiesUsingTheID(Long id);

	public SuccessResponse getTheAllFranchies(int pageNo, int pageSize, String sortBy, String orderBy);

	public SuccessResponse deleteTheFranchiesUsingTheID(Long id);

	public SuccessResponse getAllFranchiseRequests();

	public SuccessResponse getTheAllFranchises();

	public SuccessResponse assignCoursesToFranchise(Long franchiseId, Set<Long> courseIds);

	public SuccessResponse removeCourseFromFranchise(Long franchiseId, Long courseId);

	public SuccessResponse getCourseUsingFranchiseId(Long franchise_id);

	public SuccessResponse getKitCountForFranchiseAndCourse(Long franchiseId, Long courseId);

	public SuccessResponse getTheFranchiesReports(Long franchies_id, Long course_id, String startDate, String endDate);

	public SuccessResponse getAllStudentUsingDate(String startDate, String endDate, Long franchies_id);

	public SuccessResponse getAllFranchiesByDate(String startDate, String endDate);

	public SuccessResponse sendDiffrentAddressKitRequest(FranchiseDiffrentAddKitReq diffrentAddKitReq);

	public SuccessResponse sendDiffrentAddressKitByAdmin(FranchiseDiffrentAddKitReq diffrentAddKitReq);

	public SuccessResponse getAllDifrentAddressKitReq();

	public SuccessResponse getAllKitRequestsUsingDate(String startDate, String endDate, Long franchiseId);

//	public SuccessResponse sendKitRequests(Long franchiseId, List<KitRequest> kitRequests);
																							
	public SuccessResponse createOrder(FranchiseOrderDTO orderDTO);

	public SuccessResponse getOrdersByFranchise(Long franchiseId);

	public SuccessResponse addStudentWithKitRequestOnDiffrentAdd(Student student, MultipartFile studentPhoto,HttpServletRequest request)
			throws DataNotValidException, IOException;// MultipartFile studentDocPhoto	

} 
