package com.abacus.franchise.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abacus.franchise.dto.CourseDTO;
import com.abacus.franchise.model.Course;
import com.abacus.franchise.model.Franchise;
import com.abacus.franchise.model.FranchiseKitRequest;
import com.abacus.franchise.repo.CourseRepo;
import com.abacus.franchise.repo.FranchiseKitRequestRepo;
import com.abacus.franchise.repo.FranchiseRepo;
import com.abacus.franchise.repo.StudentRepo;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.CourseService;

import jakarta.transaction.Transactional;

@Component
public class CourseserviceImpl implements CourseService {

	@Autowired
	CourseRepo courseRepo;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	StudentRepo studentRepo;

	@Autowired
	FranchiseKitRequestRepo franchiseKitRequestRepo;

	@Autowired
	FranchiseRepo franchiseRepo;

	@Override
	public SuccessResponse saveAndUpdateTheCourseInformation(Course course) {
		SuccessResponse response = new SuccessResponse();
		System.out.println("course type : " + course.getCourseType());
		try {
			if (course.getCourse_name() == null || course.getCourse_duration() == null) {
				response.nullData();
				return response;
			}
			if (course.getCourseType() == null) {
				response.courseTypeRequired();
				return response;
			}
			if (course.getCourse_id() != null) {
				Optional<Course> getByTheid = courseRepo.findById(course.getCourse_id());
				if (getByTheid.isPresent()) {
					Course course2 = getByTheid.get();
					modelMapper.map(course, course2);
					System.out.println("55");
					List<Long> allFranchiesUsingCourseId = franchiseRepo
							.getAllFranchiesUsingCourseId(course.getCourse_id());
					System.out.println("56");
					for (Long franchise : allFranchiesUsingCourseId) {
						Optional<FranchiseKitRequest> byId = franchiseKitRequestRepo
								.findByFranchiseIdAndCourseId(franchise, course.getCourse_id());
						if (byId.isPresent()) {
							FranchiseKitRequest franchiseKitRequest = byId.get();
							franchiseKitRequest.setAvelableTotalKits(course.getNo_of_books());
						}
					}
					courseRepo.save(course2);
					CourseDTO courseDto = modelMapper.map(course2, CourseDTO.class);
					response.courseUpdated(courseDto);
					return response;
				} else {
					response.idNotFound();
					return response;
				}
			} else {
				courseRepo.save(course);
				CourseDTO courseDto = modelMapper.map(course, CourseDTO.class);
				response.saveTheCourse(courseDto);
				return response;
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.ExceptionSMS(response);
			return response;
		}
	}
//-----------------------------------------------------------------------------------------------------------------------	

	@Override
	public SuccessResponse getAllTheCoursess() {

		SuccessResponse response = new SuccessResponse();
		List<Course> all = courseRepo.findAllCourses();
		if (all == null) {
			response.notfound();
			return response;
		}
		response.getAllCourse(all);

		return response;
	}

//----------------------------------------------------------------------------------------------------------------------------------	

	@Override
	public SuccessResponse getTheCourseUsingTheID(Long id) {

		SuccessResponse response = new SuccessResponse();
		if (id == null) {
			response.idisNull();
			return response;
		}

		Optional<Course> getByid = courseRepo.findById(id);
		if (getByid.isPresent()) {
			Course course = getByid.get();
			response.getTheCourse(course);
			return response;
		} else {
			response.courseNotFound(id);
			return response;
		}
	}

//---------------------------------------------------------------------------------------------------------------------------	

	@Override
	public SuccessResponse getTheCourseUsingTheName(String name) {
		SuccessResponse response = new SuccessResponse();
		if (name == null) {
			response.nameIsNull();
			return response;
		}
		List<Course> getByName = courseRepo.findTheCourceUsingTheName(name);
		if (getByName == null || getByName.isEmpty()) {
			response.notfoundCourse();
			return response;
		}
		response.courseGetSuccesfully(getByName);
		return response;
	}

//--------------------------------------------------------------------------------------------------------------------------------	

	@Override
	@Transactional
	public SuccessResponse deleteTheCourseusingTheID(Long id) {
		SuccessResponse response = new SuccessResponse();

		if (id == null) {
			response.idisNull();
			return response;
		}
		Optional<Course> courseOptional = courseRepo.findById(id);
		if (courseOptional.isPresent()) {
			Course course = courseOptional.get();
			if (!course.isCourse_status()) {
				response.courseAlreadyDeleted();
				return response;
			}
			long activeStudentCount = studentRepo.countByCourseIdAndStatus(id);

			if (activeStudentCount > 0) {
				response.gretherThanCountStudentforCourse();
				return response;
			}

			course.setCourse_status(false);
			courseRepo.save(course);

			response.courseDeleteSuccesfully();
			return response;
		} else {
			response.notfound();
			return response;
		}
	}

	@Override
	public SuccessResponse getCoursesByFranchise(Long franchiseId) {
		SuccessResponse response = new SuccessResponse();

		if (franchiseId == null) {
			response.nullData();
			return response;
		}

		// Find the franchise by ID
		Optional<Franchise> franchiseOptional = franchiseRepo.findById(franchiseId);
		if (!franchiseOptional.isPresent()) {
			response.franchiseNotFound();
			return response;
		}

		Franchise franchise = franchiseOptional.get();

		// Retrieve all courses associated with the franchise
		Set<Course> courses = franchise.getCourses();
		if (courses.isEmpty()) {
			response.notfound();
			return response;
		}

		List<CourseDTO> courseDTOList = new ArrayList<>();
		for (Course course : courses) {
			CourseDTO courseDTO = modelMapper.map(course, CourseDTO.class);

			// Retrieve FranchiseKitRequest for this course and franchise
			Optional<FranchiseKitRequest> kitRequestOptional = franchiseKitRequestRepo
					.findByFranchiseIdAndCourseId(franchiseId, course.getCourse_id());

			if (kitRequestOptional.isPresent()) {
				FranchiseKitRequest kitRequest = kitRequestOptional.get();
				courseDTO.setNo_of_books(kitRequest.getRemainingStudents()); // Assign ordered kits count
			} else {
				courseDTO.setNo_of_books(0);
			}

			courseDTOList.add(courseDTO);
		}

		response.getAllCourse(courseDTOList);
		return response;
	}

}
