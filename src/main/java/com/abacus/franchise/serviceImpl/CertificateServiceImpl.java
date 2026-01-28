package com.abacus.franchise.serviceImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abacus.franchise.dto.CertificateDTO;
import com.abacus.franchise.model.Certificate;
import com.abacus.franchise.model.Course;
import com.abacus.franchise.model.Student;
import com.abacus.franchise.model.StudentExam;
import com.abacus.franchise.repo.CertificateRepo;
import com.abacus.franchise.repo.CourseRepo;
import com.abacus.franchise.repo.StudentExamRepository;
import com.abacus.franchise.repo.StudentRepo;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.CertificateService;
//import com.abacus.franchise.service.S3BucketService;

@Component
public class CertificateServiceImpl implements CertificateService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	private CertificateRepo certificateRepository;

//	@Autowired
//	S3BucketService s3BucketService;

	@Autowired
	StudentRepo studentRepo;

	@Autowired
	CourseRepo courseRepo;

	@Autowired
	StudentExamRepository studentExamRepository;

	private SuccessResponse response = new SuccessResponse();


	@Override
	public String generateCertificateNumber() {
		LocalDate currentDate = LocalDate.now();
		int year = currentDate.getYear() % 100;
		int month = currentDate.getMonthValue();

		Optional<Certificate> topByOrderCertificateDesc = certificateRepository.findTopByOrderByCertificateNumDesc();
		String lastDriverId;
		if (topByOrderCertificateDesc.isPresent() && topByOrderCertificateDesc != null) {
			lastDriverId = topByOrderCertificateDesc.get().getCertificate_number();
		} else {
			lastDriverId = "CRNO" + String.format("%02d%02d", year, month) + "000000";
		}

		String numericPart = lastDriverId.substring(10);
		int numericValue = Integer.parseInt(numericPart) + 1;
		String newNumericPart = String.format("%06d", numericValue);

		return "CRNO" + String.format("%02d%02d", year, month) + newNumericPart;
	}

	@Override
	public SuccessResponse saveCertificate(CertificateDTO certificateDTO) {
		Optional<Certificate> existingCertificate = certificateRepository.findByStudentIdAndCourseIdAndIsGeneratedTrue(
				certificateDTO.getStudentId(), certificateDTO.getCourseId());
		if (existingCertificate.isPresent()) {
			response.setMessage("Certificate has already been generated for this student and course.");
			response.setResponse(null);
			response.setStatus(false);
			return response;
		}

		Optional<Student> studentOptional = studentRepo.findById(certificateDTO.getStudentId());
		if (studentOptional.isEmpty()) {
			response.setMessage("Student not found");
			response.setResponse(null);
			response.setStatus(false);
			return response;
		}

		Optional<Course> courseOptional = courseRepo.findById(certificateDTO.getCourseId());
		if (courseOptional.isEmpty()) {
			response.setMessage("Course not found");
			response.setResponse(null);
			response.setStatus(false);
			return response;
		}

		System.out.println("exam id : " + certificateDTO.getExamId() + ", student id :  "
				+ certificateDTO.getStudentId() + ", course id : " + certificateDTO.getCourseId());
		Optional<StudentExam> studentExamOptional = studentExamRepository.findByExamStudentAndCourseId(
				certificateDTO.getExamId(), certificateDTO.getStudentId(), certificateDTO.getCourseId());

		if (studentExamOptional.isEmpty()) {
			response.setMessage("Student Exam not completed Yet..");
			response.setResponse(null);
			response.setStatus(false);
			return response;
		}

		Student student = studentOptional.get();
		StudentExam studentExam = studentExamOptional.get();
		Certificate certificate = new Certificate();
		certificate.setCertificate_number(generateCertificateNumber());
		certificate.setStudent_name(student.getFirst_name() + " " + student.getLast_name());
		certificate.setCourse_name(studentExam.getCourse_name());
		certificate.setAdmission_date(student.getCreation_time());
		certificate.setStudentId(certificateDTO.getStudentId());
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate issueDate = studentExam.getCompletedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		certificate.setCompletion_date(issueDate.format(dateFormatter));
		certificate.setStudentMarks(studentExam.getMarks());
		certificate.setCourseId(certificateDTO.getCourseId());
		certificate.setExamName(studentExam.getExamName());
		certificate.setStudentProfImgLink(student.getProfile_image_link());
		certificate.setStatus(certificateDTO.getStatus());
		certificate.setGenerated(true);
		certificateRepository.save(certificate);
		response.setMessage("Certificate created successfully");
		response.setData(certificate);
		response.setStatus(true);
		return response;
	}

	@Override
	public SuccessResponse getAllCertificate() {
		List<Certificate> certificates = certificateRepository.findAll();
		if (certificates.isEmpty()) {
			response.certificateNotFound();
			return response;
		}
		response.certificatesRetrieved(certificates);
		return response;
	}

	@Override
	public SuccessResponse getCertificateById(Long id) {
		if (id != null) {
			Optional<Certificate> certificateOptional = certificateRepository.findByNewId(id);
			if (certificateOptional.isPresent()) {
				Certificate certificate = certificateOptional.get();
				response.certificatesRetrieved(certificate);
				return response;
			} else {
				response.certificateNotFound();
				return response;
			}
		} else {
			response.certificateNotFound();
			return response;
		}
	}

	@Override
	public SuccessResponse deleteCertificateById(Long id) {
		if (id != null) {
			Optional<Certificate> certificateOptional = certificateRepository.findByNewId(id);
			if (certificateOptional.isPresent()) {
				Certificate certificate = certificateOptional.get();
				certificate.setStatus(false);
				Certificate save = certificateRepository.save(certificate);
				response.certificateDeleted(save);
				return response;
			} else {
				response.certificateNotFound();
				return response;
			}
		} else {
			response.certificateNotFound();
			return response;
		}
	}


	private String convertImageToBase64(String imageUrl) {
		try {
			URL url = new URL(imageUrl);
			InputStream inputStream = url.openStream();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int bytesRead;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				byteArrayOutputStream.write(buffer, 0, bytesRead);
			}

			byte[] imageBytes = byteArrayOutputStream.toByteArray();
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);
			inputStream.close();
			return base64Image;
		} catch (IOException e) {
			e.printStackTrace();
			return null; // or handle the error appropriately
		}
	}

	@Override
	public SuccessResponse findByStudentId(Long studentId, Long courseId) {
		if (studentId == null) {
			response.nullData();
			return response;
		}
		System.out.println(courseId + " std : " + studentId);
		Optional<Certificate> findByStudent = certificateRepository.findByStudentAndCourseId(studentId, courseId);
		if (!findByStudent.isPresent()) {
			response.certificateNotFound();
			return response;
		}

		Certificate certificate = findByStudent.get();
		// Convert the profile image URL to Base64
		String base64Image = convertImageToBase64(certificate.getStudentProfImgLink());
		if (base64Image != null) {
			certificate.setStudentProfImgLink(base64Image);
		} else {
			response.setMessage("Failed to convert image to Base64");
			response.setStatus(false);
			return response;
		}
		response.certificatesRetrieved(findByStudent);
		return response;
	}

}