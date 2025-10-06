package com.abacus.franchise.dto;

public class ProductOrderRequestDTO {
	private Long productId;
	private Integer requestedQuantity;
	private String size; // Optional field for product size

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getRequestedQuantity() {
		return requestedQuantity;
	}

	public void setRequestedQuantity(Integer requestedQuantity) {
		this.requestedQuantity = requestedQuantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public ProductOrderRequestDTO(Long productId, Integer requestedQuantity, String size) {
		super();
		this.productId = productId;
		this.requestedQuantity = requestedQuantity;
		this.size = size;
	}

	public ProductOrderRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
