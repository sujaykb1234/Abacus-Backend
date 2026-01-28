package com.abacus.franchise.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abacus.franchise.dto.AdminProductDispachedDTO;
import com.abacus.franchise.model.Admin;
import com.abacus.franchise.model.FranchiseKitRequest;
import com.abacus.franchise.model.Products;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.utility.FranchiseStatus;

import jakarta.servlet.http.HttpServletRequest;

@Service
public interface AdminService {

	public SuccessResponse loginTheAdmin(Admin admin);

	public SuccessResponse setTheNewPassword(Admin admin);

	public SuccessResponse acceptOrRejectRequest(Long franchiseId, FranchiseStatus franchiseStatus);

	public SuccessResponse getKitRequests();

	public SuccessResponse generateUniqueUserAndPass(Long franchiseId);

	public SuccessResponse sendKitsToFranch(FranchiseKitRequest franchiseKitRequest);

	public SuccessResponse getAllReattemptRequests();

	public SuccessResponse getExamCompletedStudents(Long franchiseId);

	public SuccessResponse uploadOfflineExamPDF(Long courseId, MultipartFile questionPaper,HttpServletRequest request);

	public SuccessResponse getOfflineExamPdf(Long courseId);

	public SuccessResponse deleteFinalPaper(Long paperId);

	public SuccessResponse getAllStudentsForAdmin();

	public SuccessResponse getAllProducts();

	public SuccessResponse deleteProduct(Long id);

	public SuccessResponse saveOrUpdateProduct(Products products);

	
	public SuccessResponse getAllProductOrders();

	public SuccessResponse dispatchProducts(AdminProductDispachedDTO dispatchDTO);

}
