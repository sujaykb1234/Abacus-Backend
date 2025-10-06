package com.abacus.franchise.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abacus.franchise.dto.OfflineExamDTO;
import com.abacus.franchise.response.SuccessResponse;

@Service
public interface OfflineExamService {

	public SuccessResponse saveOfflineExams(OfflineExamDTO offlineExam, List<MultipartFile> pdf);
	
	public SuccessResponse getAllTheOfflineExam();
	
	public SuccessResponse getOfflineExamUsingTheId(Long id);
	
	public SuccessResponse getOfflineExamUsingStudentId(Long id);
	
	public SuccessResponse deleteOfflineExamById(Long id);

	SuccessResponse changeDawnloadStatus(Long offline_exam_id);




	
	
	
	


}
