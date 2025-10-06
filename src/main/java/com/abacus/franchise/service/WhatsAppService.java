package com.abacus.franchise.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.abacus.franchise.model.Admin;
import com.abacus.franchise.repo.AdminRepo;
import com.abacus.franchise.response.SuccessResponse;

@Service
public class WhatsAppService {

	@Autowired
	AdminRepo adminRepo;

	private final RestTemplate restTemplate;

	public WhatsAppService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String sendWhatsAppMessage(String number, String message) {
		
		Optional<Admin> adminDetail = adminRepo.getAdminDetail();
		
		// Construct the full URL with instance_id and access_token
		String url = adminDetail.get().getApiUrl() + "?number=" + number + "&type=text&message=" + message + "&instance_id=" + adminDetail.get().getInstanceId()
				+ "&access_token=" + adminDetail.get().getAccessToken();

		try {
			String response = restTemplate.getForObject(url, String.class);
			return response; // Return the response from the API
		} catch (Exception e) {
			e.printStackTrace();
			return "Error occurred while sending message: " + e.getMessage();
		}
	}

	public SuccessResponse sendWhatsAppMessages(String number, String message) {
		SuccessResponse successResponse = new SuccessResponse();
		Optional<Admin> adminDetail = adminRepo.getAdminDetail();
		
		// Construct the full URL with instance_id and access_token
		String url = adminDetail.get().getApiUrl() + "?number=" + number + "&type=text&message=" + message + "&instance_id=" + adminDetail.get().getInstanceId()
				+ "&access_token=" + adminDetail.get().getAccessToken();
		try {
			// Make the API call
			String response = restTemplate.getForObject(url, String.class);
			System.out.println("response : " + response);

			if (response.contains("error")) {
				successResponse.setStatus(false);
				successResponse.setMessage("Failed to send WhatsApp message");
				successResponse.setStatusCode(HttpStatus.BAD_REQUEST);
			} else {
				successResponse.setResponse(message);
				successResponse.setStatus(true);
				successResponse.setMessage("Message sent successfully");
				successResponse.setStatusCode(HttpStatus.ACCEPTED);
			}

		} catch (Exception e) {
			System.out.println("exception messagge : " + e.getMessage());
			successResponse.setMessage("Error occurred while sending message: " + e.getMessage());
			successResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return successResponse;
	}
}
