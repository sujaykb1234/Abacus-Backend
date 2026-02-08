package com.abacus.franchise.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import com.abacus.franchise.dto.CourseDetail;
import com.abacus.franchise.utility.Messages;


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

		
	public void kitsSentSuccessfully(Object save) {
		this.response = save;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.kitSendSuccessfully;
	}
	
	public void addressNotFound() {
		this.status = false;
		this.message = Messages.addressNotFound;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.response = null;			
	}
	
	public void franchiesnotfound() {
		this.response = null;
		this.status = true;
		this.statusCode = HttpStatus.NOT_EXTENDED;
		this.message = Messages.franchiesNotFound;
	}
	
	public void courseNotFound(String courseId) {
		this.response = null;
		this.status = false;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = courseId+Messages.course_not_found;
	}
	
	public void courseFound(List<CourseDetail> allCoursesByFranchiseId) {
		this.status = true;
		this.message = Messages.course_found;
		this.statusCode = HttpStatus.CREATED;
		this.response = allCoursesByFranchiseId;		
	}
	
	public void userNotFound() {
		this.status = false;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.userNotFound;
        this.response = null;
	}

	
	public void userFoundResponse(Object object) {
		this.status = true;
		this.statusCode = HttpStatus.FOUND;
		this.message = Messages.userFound;
        this.response = object;
	}
	
	public void loginSuccessfully(Object object) {
		this.response = object;
		this.status = true;
		this.statusCode = HttpStatus.OK;
		this.message = Messages.admilogin;

	}
	
	public void wrongPassword() {
		this.status = false;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.message = Messages.wrongPassword;
	}
	
	public void userIsDeactivate() {
		this.status = false;
		this.message = Messages.accountIsDeactivate;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.response = null;		
	}
	
	public void usernameIncorrect() {
		this.status = false;
		this.message = Messages.correctUsername;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.response = null;		
	}
	
	public void basicDetailsIsNull() {
		this.status = false;
		this.message = Messages.basicDetailIsNull;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.response = null;		
	}
	
	public void addressDetailIsNull() {
		this.status = false;
		this.message = Messages.addressIsNull;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.response = null;		
	}

	public void rolesNotFound() {
		this.status = false;
		this.message = Messages.rolesNotFound;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.response = null;	
	}
	
	public void stateNotFound() {
		this.response = null;
		this.status = false;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.state_not_found;
	}
	
	public void districtNotFound() {
		this.response = null;
		this.status = false;
		this.statusCode = HttpStatus.NOT_FOUND;
		this.message = Messages.district_not_found;
	}
	
	public void saveUserResponse(String accessToken) {
		this.status = true;
		this.message = Messages.save_user;
		this.statusCode = HttpStatus.CREATED;
		this.response = accessToken;		
	}
	
	
	public void loginCredentialIsNull() {
		this.status = false;
		this.message = Messages.usernamePasswordNull;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.response = null;			
	}

	public void mobileAlreadyExist() {
		this.status = false;
		this.message = Messages.mobileAlreadyExist;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.response = null;		
	}

	public void emailAlreadyExist() {
		this.status = false;
		this.message = Messages.emailAlreadyExist;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.response = null;			
	}
		
	public void passwordRequired() {
		this.status = false;
		this.message = Messages.passwordRequired;
		this.statusCode = HttpStatus.BAD_REQUEST;
		this.response = null;
	}
}
