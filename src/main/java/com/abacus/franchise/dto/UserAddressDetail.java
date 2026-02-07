package com.abacus.franchise.dto;

import java.time.LocalDate;
import java.util.UUID;

public interface UserAddressDetail {
 
	public UUID getUserId();
	public LocalDate getDateOfBirth();
	public String getEmail();
	public String getFirstName();
	public String getFranchiseName();
	public String getLastName();
	public String getMiddleName();
	public String getMobile();
	public String getProfileLink();
	public String getDocumentLink();
	public String getLine1();
	public String getCity();
	public String getLandmark();
	public String getCountryName();
	public String getPincode();
	public String getStateName();
	public String getDistrictName();
	
	 
}
