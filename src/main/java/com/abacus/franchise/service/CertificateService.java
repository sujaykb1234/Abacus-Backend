package com.abacus.franchise.service;

import org.springframework.stereotype.Service;

import com.abacus.franchise.dto.CertificateDTO;
import com.abacus.franchise.response.SuccessResponse;

@Service
public interface CertificateService {

	public SuccessResponse saveCertificate(CertificateDTO certificate);

	public SuccessResponse getAllCertificate();

	public SuccessResponse getCertificateById(Long id);

	public SuccessResponse deleteCertificateById(Long id);

	String generateCertificateNumber();

	public SuccessResponse findByStudentId(Long studentId, Long courseid);

}
