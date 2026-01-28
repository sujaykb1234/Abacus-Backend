package com.abacus.franchise.response;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import com.abacus.franchise.dto.AdminDto;
import com.abacus.franchise.dto.CourseDTO;
import com.abacus.franchise.dto.ExamDTO;
import com.abacus.franchise.dto.FranchiseDTO;
import com.abacus.franchise.dto.ProductRequestDTO;
import com.abacus.franchise.dto.StudentDTO;
import com.abacus.franchise.model.Course;
import com.abacus.franchise.model.ExamAttempt;
import com.abacus.franchise.model.Messages;

public class SuccessResponse {

	private Object response;

	private Boolean status;

	private String message;

	private HttpStatusCode statusCode;

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setData(Object quiz) {
		this.response = quiz;

	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatusCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatusCode statusCode) {
		this.statusCode = statusCode;
	}

	public SuccessResponse(Object response, Boolean status, String message, HttpStatusCode statusCode) {
		super();
		this.response = response;
		this.status = status;
		this.message = message;
		this.statusCode = statusCode;
	}

	public SuccessResponse() {
		super();
	}

//------------------------------------------------------------------------------------------------------------------------------------	
	public void nullAdminnameAndPass() {

		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.nullUserNamePass;

	}

	public void loginSuccessfully(AdminDto adminDto) {
		this.response = adminDto;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.admilogin;

	}

	public void wrongPassword() {
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.wrongPassword;
	}

	public void userNotFound() {
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.userNotFound;

	}

	public void missingFieldResponse(Object franchise) {
		this.response = franchise;
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.nullData;

	}

	public void mobileAlreadyExist() {
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.mobile_no_already_exist;
	}

	public void emailAlreadyExist() {
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.email_no_already_exist;

	}

	public void saveTheAdmin(AdminDto adminDto) {
		this.response = adminDto;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.adminRegister;
	}

	public void ExceptionSMS(SuccessResponse response2) {
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.imageError;
	}

	public void adminUpdated(AdminDto adminDto) {
		this.response = adminDto;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.adminUpdate;

	}

	public void idNotFound() {
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.idnotFound;

	}

	public void nullFile() {
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.imageNull;
	}

	public void sendEmailSuccessfully(int randomNumber) {
		this.response = randomNumber;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.EmailSendSuccesfully;

	}

	public void emailNotSend() {
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.EmailNotSend;

	}

	public void emailNotFound() {
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.emailNotFound;

	}

	public void passwordNull() {
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.passwordNull;
	}

	public void lengofpassword() {
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.passwordLenght;

	}

	public void passwordUpdateSuccesfully() {
		this.response = null;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.passwordUpdateSuccesfully;

	}

	public void incorrectUserName() {
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.incorrectUserName;

	}

	public void nullData() {
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.nullField;
	}

	public void franchiseUpdated(FranchiseDTO franchiseDto) {
		this.response = franchiseDto;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.franchiseupdate;

	}

	public void saveTheFranchise(FranchiseDTO franchiseDto) {
		this.response = franchiseDto;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.franchiseSave;

	}

	public void frinchiesLoginSuccessfully(String val) {
		this.response = val;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.franchiseLogin;

	}

	public void studentUpdated(StudentDTO studentDto) {
		this.response = studentDto;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.studentUpdate;
	}

	public void saveTheStudent(Object studentDto) {
		this.response = studentDto;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.studentSave;
	}

	public void StudentloginSuccessfully(String token) {
		this.response = token;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.studentLoginSuccesfully;
	}

	public void saveQuestions(Object obj) {
		this.response = obj;
		this.status = true;
		this.statusCode = HttpStatus.CREATED;
		this.message = Messages.questions_save;
	}

	public void updateQuestions(Object obj) {
		this.response = obj;
		this.status = true;
		this.statusCode = HttpStatus.CREATED;
		this.message = Messages.questions_update;
	}

	public void questionsFound(Object obj) {
		this.response = obj;
		this.status = true;
		this.statusCode = HttpStatus.FOUND;
		this.message = Messages.questions_found;
	}

	public void questionsNotFound() {
		this.response = null;
		this.status = true;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.questions_not_found;
	}

	public void getSingleStudent(Object studentDto) {
		this.response = studentDto;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.studentGetSuccesfully;

	}

	public void notfound() {
		this.status = false;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.dataNotFound;
	}

	public void nameIsNull() {
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.nameIsNull;
	}

	public void notFoundOrIncorrectName() {
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.nameIsWrong;
	}

	public void saveExam(Object obj) {
		this.response = obj;
		this.status = true;
		this.statusCode = HttpStatus.CREATED;
		this.message = Messages.exam_save;
	}

	public void courseNotFound(Long course_id) {
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.courseNotFound;
	}

	public void courseUpdated(CourseDTO courseDto) {
		this.response = courseDto;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.courseUpdateSuccesfully;
	}

	public void saveTheCourse(CourseDTO courseDto) {
		this.response = courseDto;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.courseSaveSuccesfully;
	}

	public void getAllCourse(Object all) {
		this.response = all;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.courseGetSuccesfully;
	}

	public void examAttemptNotFound(Object obj) {
		this.response = obj;
		this.status = true;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.exam_not_found;
	}

	public void examSubmittedSuccessfully(Object obj) {
		this.response = obj;
		this.status = true;
		this.statusCode = HttpStatus.CREATED;
		this.message = Messages.exam_submit;
	}

	public void invalidStudentForAttempt() {
		this.response = null;
		this.status = true;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.student_not_found;
	}

	public void saveState(Object obj) {
		this.response = obj;
		this.status = true;
		this.statusCode = HttpStatus.CREATED;
		this.message = Messages.save_state;
	}

	public void updateState(Object obj) {
		this.response = obj;
		this.status = true;
		this.statusCode = HttpStatus.CREATED;
		this.message = Messages.update_state;
	}

	public void stateNotFound() {
		this.response = null;
		this.status = false;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.state_not_found;
	}

	public void stateFound(Object obj) {
		this.response = obj;
		this.status = true;
		this.statusCode = HttpStatus.FOUND;
		this.message = Messages.state_found;
	}

	public void saveDistrict(Object obj) {
		this.response = obj;
		this.status = true;
		this.statusCode = HttpStatus.CREATED;
		this.message = Messages.save_district;
	}

	public void updateDistrict(Object obj) {
		this.response = obj;
		this.status = true;
		this.statusCode = HttpStatus.CREATED;
		this.message = Messages.update_district;
	}

	public void districtNotFound() {
		this.response = null;
		this.status = false;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.district_not_found;
	}

	public void districtFound(Object obj) {
		this.response = obj;
		this.status = true;
		this.statusCode = HttpStatus.FOUND;
		this.message = Messages.district_found;
	}

	public void saveTaluka(Object obj) {
		this.response = obj;
		this.status = true;
		this.statusCode = HttpStatus.CREATED;
		this.message = Messages.save_taluka;
	}

	public void updateTaluka(Object obj) {
		this.response = obj;
		this.status = true;
		this.statusCode = HttpStatus.CREATED;
		this.message = Messages.update_taluka;
	}

	public void talukaNotFound() {
		this.response = null;
		this.status = false;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.taluka_not_found;
	}

	public void talukaFound(Object obj) {
		this.response = obj;
		this.status = true;
		this.statusCode = HttpStatus.FOUND;
		this.message = Messages.taluka_found;
	}

	public void franchiseNotFound() {
		this.response = null;
		this.status = true;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.franchies_not_found;
	}

	public void noStudentsFound() {
		this.response = null;
		this.status = true;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.student_not_found;
	}

	public void studentsgetSucccesfully(Object studentDTOs) {
		this.response = studentDTOs;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.student_get_succesfully;
	}

	public void idisNull() {
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.id_is_null;
	}

	public void getSingleFranchise(FranchiseDTO franchiseDto) {
		this.response = franchiseDto;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.franchies_get_succesfully;
	}

	public void getAllFranchies(Map<String, Object> body) {
		this.response = body;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.franchies_get_succesfully;

	}

	public void studentGetSuccesfully(Map<String, Object> body) {
		this.response = body;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.student_get_succesfully;

	}

	public void franchiesDeleteSuccesfully() {
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.franchies_delete_succesfully;

	}

	public void gretherThanCountStudent() {
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.first_delet_student;
	}

	public void nullDataForStudent(Object data) {
		this.response = data;
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.nullField;
	}

	public void franchiseNotAuthorized() {
		this.response = null;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.franchies_not_authenticated;
	}

	public void franchiseRequests(Object franchiseDTOs) {
		this.response = franchiseDTOs;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.franchies_requests;
	}

	public void success(String message, Object data) {
		this.message = message;
		this.response = data;
		this.status = true;
	}

	public void kitOrderPlacced(Object save) {
		this.response = save;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.kit_order_placced;
	}

	public void kitCountZero() {
		this.response = null;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.kit_count;
	}

	public void noKitReq() {
		this.response = null;
		this.status = true;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.noKitRequest;
	}

	public void kitRequests(Object kitReq) {
		this.response = kitReq;
		this.status = true;
		this.statusCode = HttpStatus.FOUND;
		this.message = Messages.kitReq;
	}

	public void getUisngTheName(List<StudentDTO> studentDTOs) {
		this.response = studentDTOs;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.studentGetSuccesfully;

	}

	public void studentdeleteSuccesfully() {
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.studentDelete;

	}

	public void getSingleStudentUsingCourseID(List<StudentDTO> studentDTOs) {
		this.response = studentDTOs;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.studentGetSuccesfully;

	}

	public void getAllFranchies(Object body) {
		this.response = body;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.franchies_get_succesfully;
	}

	public void getTheCourse(Course course) {
		this.response = course;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.course_get_succesfully;
	}

	public void notfoundCourse() {
		this.status = false;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.course_not_found;
	}

	public void courseGetSuccesfully(List<Course> getByName) {
		this.response = getByName;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.course_get_succesfully;

	}

	public void courseDeleteSuccesfully() {
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.course_delete_succesfully;

	}

	public void gretherThanCountStudentforCourse() {
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.first_delet_student_course;
	}

	public void courseAlreadyDeleted() {
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.course_already_deleted;

	}

	public void certificateSaved(Object certificate) {
		this.response = certificate;
		this.status = true;
		this.message = Messages.certificate_saved;
		this.statusCode = HttpStatus.OK;
	}

	public void certificateUpdated(Object certificate) {
		this.response = certificate;
		this.status = true;
		this.message = Messages.certificate_updated;
		this.statusCode = HttpStatus.OK;
	}

	public void certificateDeleted(Object certificate) {
		this.response = certificate;

		this.status = true;
		this.message = Messages.certificate_deleted;
		this.statusCode = HttpStatus.OK;
	}

	public void certificateNotFound() {
		this.response = null;
		this.status = false;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.certificate_not_found;
	}

	public void certificatesRetrieved(Object certificates) {
		this.response = certificates;
		this.status = true;
		this.message = Messages.certificates_retrieved;
		this.statusCode = HttpStatus.OK;
	}

	public void courseAssign(Object obj) {
		this.response = obj;
		this.status = true;
		this.message = Messages.course_assign;
		this.statusCode = HttpStatus.OK;
	}

	public void courseRemoved(Object obj) {
		this.response = obj;
		this.status = true;
		this.message = Messages.course_removed;
		this.statusCode = HttpStatus.OK;
	}

	public void courseRetrieved(Object obj) {
		this.response = obj;
		this.status = true;
		this.message = Messages.course_retrieved;
		this.statusCode = HttpStatus.OK;
	}

	public void getAllExam(List<ExamAttempt> all) {
		this.response = all;
		this.status = true;
		this.message = Messages.getTheAllExam;
		this.statusCode = HttpStatus.OK;

	}

	public void getSingleExamUsingCourseID(List<ExamDTO> examDTOs) {

		this.response = examDTOs;
		this.status = true;
		this.message = Messages.getTheAllExam;
		this.statusCode = HttpStatus.OK;
	}

	public void noExamsFound() {
		this.status = false;
		this.message = Messages.exam_not_found;
		this.statusCode = HttpStatus.BAD_REQUEST;
	}

	public void insufficientKits() {
		this.response = null;
		this.status = true;
		this.message = Messages.insufficientKit;
		this.statusCode = HttpStatus.NOT_ACCEPTABLE;
	}

	public void orderKitFirst(Long course_id) {
		this.response = course_id;
		this.status = true;
		this.message = Messages.orderKits;
		this.statusCode = HttpStatus.NOT_ACCEPTABLE;
	}

	public void getTheSingleExam(ExamAttempt exam) {
		this.response = exam;
		this.status = true;
		this.message = Messages.getTheAllExam;
		this.statusCode = HttpStatus.OK;
	}

	public void saveOfflineExamSuccess(Object exam) {
		this.response = exam;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.OFFLINE_EXAM_SAVE_SUCCESS;
	}

	public void offlineExamNotFound() {
		this.response = null;
		this.status = false;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.OFFLINE_EXAM_NOT_FOUND;
	}

	public void offlineExamDelete(Object exam) {
		this.response = exam;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.OFFLINE_EXAM_DELETE_SUCCESS;
	}

	public void getOfflineExam(Object exam) {
		this.response = exam;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.OFFLINE_EXAM_FOUND;
	}

	public void franchiseNotFoundForCourse(Object obj) {
		this.response = obj;
		this.status = false;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.franchise_not_found_for_course;
	}

	public void noExamsCreatedForCourse(Object exam) {
		this.response = exam;
		this.status = false;
		this.statusCode = HttpStatus.NOT_ACCEPTABLE;
		this.message = Messages.exam_not_create;
	}

	public void saveExamForFranchises(Object exam) {
		this.response = exam;
		this.status = true;
		this.statusCode = HttpStatus.CREATED;
		this.message = Messages.exam_created;
	}

	public void ExamNotFound() {
		this.status = false;
		this.statusCode = HttpStatus.NOT_ACCEPTABLE;
		this.message = Messages.exam_not_found;

	}

	public void setStudents(Object exam) {
		this.response = exam;
		this.status = true;
		this.statusCode = HttpStatus.CREATED;
		this.message = Messages.exam_set_to_Student;
	}

	public void noPracticeTestsFound() {
		this.response = null;
		this.status = true;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.noPractiseTests;
	}

	public void practiceTestsForStudent(Object tests) {
		this.response = tests;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.practiceTests;
	}

	public void studentNotFound() {
		this.response = null;
		this.status = true;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.studentNotFound;
	}

	public void studentNotEnrolleCourse() {
		this.response = null;
		this.status = false;
		this.statusCode = HttpStatus.NOT_IMPLEMENTED;
		this.message = Messages.studentAreNotHavingCourse;
	}

	public void examForStudentsRetrived(Object data) {
		this.response = data;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.examForStudent;
	}

	public void notificationNotFound() {
		this.response = null;
		this.status = true;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.notificationsNotFound;
	}

	public void notificationsRetrived(Object data) {
		this.response = data;
		this.status = true;
		this.statusCode = HttpStatus.FOUND;
		this.message = Messages.notificationsRetrived;
	}

	public void viewed(Object findById) {
		this.response = findById;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.viewed;
	}

	public void alredyAssignThecourse() {
		// TODO Auto-generated method stub
		this.response = null;
		this.status = true;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.courseAlreadyAssign;
	}

	public void noFranchisesFoundForCourse(Long courseId) {
		this.response = courseId;
		this.status = true;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = "NO FRANCHISE FOUND FOR COURSEID IS " + courseId;
	}

	public void sendEmailSuccessfullyToFrnachies() {
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.EmailSendSuccesfully;
	}

	public void studentCount(Object obj) {
		this.response = obj;
		this.status = true;
		this.statusCode = HttpStatus.FOUND;
		this.message = Messages.student_count;
	}

	public void getAllStudent(Object allStudents) {
		this.response = allStudents;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.student_get_succesfully;

	}

	public void kitsSentSuccessfully(Object save) {
		this.response = save;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.kitSendSuccessfully;
	}

	public void notEnoughKitsAvailable() {
		this.response = null;
		this.status = true;
		this.statusCode = HttpStatus.NOT_EXTENDED;
		this.message = Messages.noKitsAvaiable;
	}

	public void franchiesnotfound() {
		this.response = null;
		this.status = true;
		this.statusCode = HttpStatus.NOT_EXTENDED;
		this.message = Messages.franchiesNotFound;
	}

	public void studentAlreadyEnrolledCourse() {
		this.status = true;
		this.statusCode = HttpStatus.NOT_EXTENDED;
		this.message = Messages.alreadyEnrolled;

	}

	public void alredyInTheCourse() {
		this.status = true;
		this.statusCode = HttpStatus.NOT_EXTENDED;
		this.message = Messages.alreadyInCourseStudent;
	}

	public void noFranchiseKitRequestFound() {
		this.response = null;
		this.status = false;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.record_not_found;
	}

	public void examAlreadyAssign() {
		this.response = null;
		this.status = false;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.exam_already_assign;
	}

	public void courseNotFound() {
		this.response = null;
		this.status = false;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.course_not_found_for_this_student;
	}

	public void examNotCompleted() {
		this.status = false;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.previous_course_not_complete;
	}

	public void examNotFoundForStudent() {
		this.status = false;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.Exam_not_found_student;

	}

	public void requestCourse() {
		this.response = null;
		this.status = false;
		this.statusCode = HttpStatus.NOT_ACCEPTABLE;
		this.message = Messages.request_for_course;
	}

	public void courseNotAssignedToFranchise(Long courseId) {
		this.response = courseId;
		this.status = false;
		this.statusCode = HttpStatus.NOT_ACCEPTABLE;
		this.message = Messages.request_for_course;

	}

	public void courseAlreadyCompleted() {
		this.status = false;
		this.statusCode = HttpStatus.NOT_ACCEPTABLE;
		this.message = Messages.this_course_already_complete;

	}

	public void getallStudentExams(Object findByExamAndFranchiseId) {
		this.response = findByExamAndFranchiseId;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.student_exam_retrived;
	}

	public void reAttemptRequestSuccess(Object studentExam2) {
		this.response = studentExam2;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.reattempt_requested;
	}

	public void reAttemptRequestApproved(Object value) {
		this.response = value;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.reattempt_request_approved;
	}

	public void ExceptionForImg(Object name) {
		this.response = name;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.exception_for_img;
	}

	public void changeDawnloadStatus(Object exam) {
		this.response = exam;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.change_dawnload_status;
	}

	public void oflineExamStudents(Object test) {
		this.response = test;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.OFFLINE_EXAM_FOUND;
	}

	public void imageFound(Object sliderImage) {
		this.response = sliderImage;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.IMAGE_FOUND;

	}

	public void imageSave(Object sliderImage) {
		this.response = sliderImage;
		this.status = true;
		this.statusCode = HttpStatus.CREATED;
		this.message = Messages.IMAGE_SAVE;

	}

	public void imageDelete(Object sliderImage) {
		this.response = sliderImage;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.IMAGE_DELETE;

	}

	public void imageNotFound() {
		this.response = null;
		this.status = true;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.IMAGE_NOT_FOUND;

	}

	public void kitRequestNotFound() {
		this.response = null;
		this.status = false;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.kit_request_not_found;
	}

	public void getAllKitRequests(Object allStudents) {
		this.response = allStudents;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.kit_request_get_succesfully;

	}

	public void swichCourseSuccessfully(Object newEnrollment) {
		this.response = newEnrollment;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = "SWITCH COURSE SUCCCESSFULLY.";
	}

	public void deleteSuccess() {
		this.response = null;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = "QUESTION DELETED SUCCESSFULLY..!";
	}

	public void deleteExamSuccess() {
		this.response = null;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = "EXAM DELETED SUCCESSFULLY..!";
	}
	
	public void pdfUploadedSuccessfully(Object examUpload) {
		this.response = examUpload;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = "QUESTION PAPER UPLOAD SUCCESSFULLY..!";
	}

	public void examFound(Object data) {
		this.response = data;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = "QUESTION PAPER PDF GET SUCCESSFULLY..!";
	}

	public void paperDeleted(Object offlineExamUpload) {
		this.response = offlineExamUpload;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = "PAPER DELETED SUCCESSFULLY..!";
	}

	public void questionPaperNotFound() {
		this.response = null;
		this.status = false;
		this.statusCode = HttpStatus.OK;
		this.message = "QUESTION PAPER NOT FOUND..!";
	}

	public void courseNotSelected() {
		this.response = null;
		this.status = false;
		this.statusCode = HttpStatus.OK;
		this.message = "PLEASE SELECT A COURSE..!";
	}

	public void getAllStudents(Object res) {
		this.response = res;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = "GET ALL STUDENTS..!";
	}

	public void examNotCompletedForCurrentCourse() {
		this.response = null;
		this.status = false;
		this.statusCode = HttpStatus.NOT_ACCEPTABLE;
		this.message = "CURRENT COURSE EXAM NOT COMPLETED YET..!";
	}

	public void pleaseSelectCourseType() {
		this.response = null;
		this.status = false;
		this.statusCode = HttpStatus.NOT_ACCEPTABLE;
		this.message = "SPECIFY COURSE TYPE BETWEEN 'ABACUS' OR 'OTHER'..!";

	}

	public void courseTypeRequired() {
		this.response = null;
		this.status = false;
		this.statusCode = HttpStatus.NOT_ACCEPTABLE;
		this.message = "COURSE TYPE OF REQUIRED..!";

	}

	public void productSaved(Object obj) {
		this.response = obj;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = "PRODUCT ADDED SUCCESSFULLY..";
	}

	public void success(String message) {
		this.status = true;
		this.message = message;
		this.statusCode = HttpStatus.OK;
	}

	public void notFound(String message) {
		this.status = false;
		this.message = message;
		this.statusCode = HttpStatus.NOT_FOUND;
	}

	public void productRequestNotFound() {
		this.status = false;
		this.message = "PRODUCT ORDER REQUESTS NOT FOUND..!";
		this.statusCode = HttpStatus.NOT_FOUND;
		this.response = null;
	}

	public void partialSuccess(String string, List<ProductRequestDTO> responseDTOs, List<String> errorMessages) {
		this.status = false;
		this.message = string;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.response = errorMessages;
	}

	public void franchiseOrderNotFound() {
		this.status = false;
		this.message = "Franchise order not found..!";
		this.statusCode = HttpStatus.NOT_FOUND;
		this.response = null;
	}

	public void examNotDeleted() {
		
		this.status = false;
		this.message = "Exam cannot be deleted a s students have taken this exam.";
		this.statusCode = HttpStatus.NOT_ACCEPTABLE;
		this.response = null;
	}

	public void examDelete() {
		this.status = true;
		this.message = "Exam Delete Succesfully";
		this.statusCode = HttpStatus.OK;
		this.response = null;		
	}

	public void noQuestionsFoundForCourse(Long course_id) {
		this.status = false;
		this.message = "no Questions Found For Course";
		this.statusCode = HttpStatus.BAD_GATEWAY;
		this.response = null;		
	}

	public void basicDetailsIsNull() {
		this.status = false;
		this.message = "BASIC DETAIL IS NULL";
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.response = null;		
	}

	public void addressDetailIsNull() {
		this.status = false;
		this.message = "ADDRESS IS NULL";
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.response = null;		
	}

	public void rolesNotFound() {
		this.status = false;
		this.message = "ROLE NOT FOUND";
		this.statusCode = HttpStatus.NOT_FOUND;
		this.response = null;	
	}

	public void saveUserResponse(String accessToken) {
		this.status = true;
		this.message = "SAVE USER SUCCESSFULLY";
		this.statusCode = HttpStatus.CREATED;
		this.response = accessToken;		
	}

	public void loginCredentialIsNull() {
		this.status = false;
		this.message = "USERNAME AND PASSWORD IS NULL";
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.response = null;			
	}

	public void usernameIncorrect() {
		this.status = false;
		this.message = "ENTER CORRECT USERNAME";
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.response = null;		
	}

	public void userIsDeactivate() {
		this.status = false;
		this.message = "ACCOUNT IS DEACTIVATE";
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.response = null;		
	}
}
