package com.abacus.franchise.dto;

public class ProductDispatchDetails {
	private String productName;
    private int dispatchedQuantity;
    private int remainingStock;
    private String trackingNumber;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getDispatchedQuantity() {
		return dispatchedQuantity;
	}
	public void setDispatchedQuantity(int dispatchedQuantity) {
		this.dispatchedQuantity = dispatchedQuantity;
	}
	public int getRemainingStock() {
		return remainingStock;
	}
	public void setRemainingStock(int remainingStock) {
		this.remainingStock = remainingStock;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public ProductDispatchDetails(String productName, int dispatchedQuantity, int remainingStock,
			String trackingNumber) {
		super();
		this.productName = productName;
		this.dispatchedQuantity = dispatchedQuantity;
		this.remainingStock = remainingStock;
		this.trackingNumber = trackingNumber;
	}

	public ProductDispatchDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

}
