package com.abacus.franchise.viewModels;

import java.util.List;
import java.util.UUID;

import com.abacus.franchise.model.KitOrderItem;

public class KitRequest {
	
	private UUID franchiseId;
		
    private List<KitOrderItem> kitOrderItems;

	private UUID addressId;
	
	private String line1;
	
	private String landmark;
	
	private String city;
	
	private UUID districtId;
	
	private UUID stateId;
	
	private String pincode;

	public UUID getFranchiseId() {
		return franchiseId;
	}

	public void setFranchiseId(UUID franchiseId) {
		this.franchiseId = franchiseId;
	}

	

	public List<KitOrderItem> getKitOrderItems() {
		return kitOrderItems;
	}

	public void setKitOrderItems(List<KitOrderItem> kitOrderItems) {
		this.kitOrderItems = kitOrderItems;
	}

	public UUID getAddressId() {
		return addressId;
	}

	public void setAddressId(UUID addressId) {
		this.addressId = addressId;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public UUID getDistrictId() {
		return districtId;
	}

	public void setDistrictId(UUID districtId) {
		this.districtId = districtId;
	}

	public UUID getStateId() {
		return stateId;
	}

	public void setStateId(UUID stateId) {
		this.stateId = stateId;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	
	
}
