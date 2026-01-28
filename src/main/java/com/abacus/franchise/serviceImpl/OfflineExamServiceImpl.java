package com.abacus.franchise.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.abacus.franchise.dto.OfflineExamDTO;
import com.abacus.franchise.model.Franchise;
import com.abacus.franchise.model.OfflineExam;
import com.abacus.franchise.model.StoredImages;
import com.abacus.franchise.model.Student;
import com.abacus.franchise.repo.FranchiseRepo;
import com.abacus.franchise.repo.OfflineExamRepo;
import com.abacus.franchise.repo.StudentExamRepository;
import com.abacus.franchise.repo.StudentRepo;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.OfflineExamService;
//import com.abacus.franchise.service.S3BucketService;
import com.abacus.franchise.utility.ExamType;
import com.abacus.franchise.utility.ImageStoreProcess;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class OfflineExamServiceImpl implements OfflineExamService {

	@Autowired
	OfflineExamRepo offlineExamRepo;

	@Autowired
	StudentExamRepository studentExamRepository;

	@Autowired
	FranchiseRepo franchiseRepo;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	StudentRepo studentRepo;

	private SuccessResponse response = new SuccessResponse();

//	@Autowired
//	S3BucketService s3BucketService;

	@Override
	public SuccessResponse saveOfflineExams(OfflineExamDTO offlineExamDTO, List<MultipartFile> pdf,HttpServletRequest request) {
		OfflineExam offlineExam = modelMapper.map(offlineExamDTO, OfflineExam.class);
		if (offlineExam.getExamType() == ExamType.OFFLINE) {
			List<StoredImages> pdfImages = new ArrayList<>();
			for (MultipartFile storedImages : pdf) {
				if (pdf != null && !pdf.isEmpty()) {
					StoredImages image = new StoredImages();
					List<String> saveFile = ImageStoreProcess.saveFile(storedImages, request);
					
					if(saveFile != null) {
						image.setPdfLink(saveFile.get(1));
						image.setPdfName(saveFile.get(0));
					}
					pdfImages.add(image);
				}
			}
			offlineExam.setPdf_images(pdfImages);
		} else if (offlineExam.getExamType() == ExamType.ONLINE) {
			offlineExam.setPdf_images(null);
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		offlineExam.setCreation_time(formatter.format(now));
		offlineExamRepo.save(offlineExam);
		studentRepo.offlineExamCompleted(offlineExam.getStudent_id());			
		
		
		studentExamRepository.changeExamStatus(offlineExamDTO.getStudent_id(),offlineExamDTO.getCourse_id(),offlineExamDTO.getFranchise_id(),offlineExamDTO.getMarks().intValue());
//			modelMapper.map(offlineExam, offlineExamDTO);
		response.saveOfflineExamSuccess(offlineExam);

		return response;
	}

	@Override
	public SuccessResponse getAllTheOfflineExam() {
		List<OfflineExam> offlineExams = offlineExamRepo.findAll();

		if (offlineExams.isEmpty()) {
			response.offlineExamNotFound();
			return response;
		}

		List<OfflineExamDTO> offlineExamDTOs = new ArrayList<>();
		for (OfflineExam offlineExam : offlineExams) {
			OfflineExamDTO examDTO = modelMapper.map(offlineExam, OfflineExamDTO.class);

			Optional<Student> studentOptional = studentRepo.findByid(offlineExam.getStudent_id());

			if (studentOptional.isEmpty()) {
				response.studentNotFound();
				return response;
			}

			Student student = studentOptional.get();

			examDTO.setFirst_name(student.getFirst_name());
			examDTO.setLast_name(student.getLast_name());
			examDTO.setMobile_no(student.getMobile_no());
			examDTO.setEmail(student.getEmail());
			examDTO.setName_on_certificate(student.getName_on_certificate());
			examDTO.setStudent_profile_link(student.getProfile_image_link());
			examDTO.setStudent_profile_name(student.getProfile_image_name());
			offlineExamDTOs.add(examDTO);

			Optional<Franchise> optionalFranchise = franchiseRepo.findById(offlineExam.getFranchise_id());
			if (optionalFranchise.isEmpty()) {
				response.franchiseNotFound();
				return response;
			}
			Franchise franchise = optionalFranchise.get();
			examDTO.setFranchise_name(franchise.getFranchise_name());
		}

		response.getOfflineExam(offlineExamDTOs);
		return response;
	}

	@Override
	public SuccessResponse getOfflineExamUsingTheId(Long id) {
		if (id != null) {
			Optional<OfflineExam> optional = offlineExamRepo.findByNewId(id);
			if (optional.isPresent()) {
				OfflineExam offlineExam = optional.get();
				response.getOfflineExam(offlineExam);
				return response;
			} else {
				response.offlineExamNotFound();
				return response;
			}
		} else {
			response.offlineExamNotFound();
			return response;
		}
	}

	@Override
	public SuccessResponse deleteOfflineExamById(Long id) {
		if (id != null) {
			Optional<OfflineExam> offlineExamOptional = offlineExamRepo.findByNewId(id);
			if (offlineExamOptional.isPresent()) {
				OfflineExam offlineExam = offlineExamOptional.get();
				offlineExam.setStatus(false);
				offlineExamRepo.save(offlineExam);

//				OfflineExamDTO offlineExamDTO = modelMapper.map(offlineExam, OfflineExamDTO.class);
				response.offlineExamDelete(offlineExam);
				return response;
			} else {
				response.offlineExamNotFound();
				return response;
			}
		} else {
			response.offlineExamNotFound();
			return response;
		}
	}

	@Override
	public SuccessResponse getOfflineExamUsingStudentId(Long student_id) {

		List<OfflineExam> offlineExams = offlineExamRepo.findByStudentId(student_id);
		if (offlineExams.isEmpty()) {
			response.ExamNotFound(); // Handle no exams found
			return response;
		}

		List<OfflineExamDTO> offlineExamDTOs = new ArrayList<>();

		for (OfflineExam offline : offlineExams) {

			OfflineExamDTO examDTO = modelMapper.map(offline, OfflineExamDTO.class);

			if (student_id == null) {

				response.studentNotFound();
				return response;

			}
			Optional<Student> studentOptional = studentRepo.findById(examDTO.getStudent_id());
			if (!studentOptional.isPresent()) {
				response.studentNotFound();
				return response;
			}

			Student student = studentOptional.get();
			examDTO.setFirst_name(student.getFirst_name());
			examDTO.setLast_name(student.getLast_name());
			examDTO.setMobile_no(student.getMobile_no());
			examDTO.setEmail(student.getEmail());
			examDTO.setName_on_certificate(student.getName_on_certificate());
			examDTO.setStudent_profile_link(student.getProfile_image_link());
			examDTO.setStudent_profile_name(student.getProfile_image_name());

			Optional<Franchise> optionalFranchise = franchiseRepo.findById(examDTO.getFranchise_id());
			if (optionalFranchise.isEmpty()) {
				response.franchiseNotFound();
				return response;
			}
			Franchise franchise = optionalFranchise.get();
			examDTO.setFranchise_name(franchise.getFranchise_name());
			offlineExamDTOs.add(examDTO);

		}
		response.getOfflineExam(offlineExamDTOs);
		return response;
	}

	@Override
	public SuccessResponse changeDawnloadStatus(Long offline_exam_id) {
		Optional<OfflineExam> offlineExams = offlineExamRepo.findById(offline_exam_id);
		if (offlineExams.isEmpty()) {
			response.ExamNotFound();
			return response;
		}
		OfflineExam offlineExam = offlineExams.get();
		offlineExam.setMark_as_download(true);
		OfflineExamDTO examDTO = modelMapper.map(offlineExam, OfflineExamDTO.class);

		if (offlineExam.getStudent_id() == null) {

			response.studentNotFound();
			return response;
		}
		Optional<Student> studentOptional = studentRepo.findById(examDTO.getStudent_id());
		if (!studentOptional.isPresent()) {
			response.studentNotFound();
			return response;
		}
		Student student = studentOptional.get();
		examDTO.setFirst_name(student.getFirst_name());
		examDTO.setLast_name(student.getLast_name());
		examDTO.setMobile_no(student.getMobile_no());
		examDTO.setEmail(student.getEmail());
		examDTO.setName_on_certificate(student.getName_on_certificate());
		examDTO.setStudent_profile_link(student.getProfile_image_link());
		examDTO.setStudent_profile_name(student.getProfile_image_name());

		Optional<Franchise> optionalFranchise = franchiseRepo.findById(examDTO.getFranchise_id());
		if (optionalFranchise.isEmpty()) {
			response.franchiseNotFound();
			return response;
		}
		Franchise franchise = optionalFranchise.get();
		examDTO.setFranchise_name(franchise.getFranchise_name());

		response.changeDawnloadStatus(examDTO);
		return response;

	}

}
