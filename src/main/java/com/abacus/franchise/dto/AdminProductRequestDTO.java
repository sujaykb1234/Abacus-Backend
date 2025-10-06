package com.abacus.franchise.dto;

public class AdminProductRequestDTO {
	private Long productId;
	private Integer quantityToSend;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantityToSend() {
		return quantityToSend;
	}

	public void setQuantityToSend(Integer quantityToSend) {
		this.quantityToSend = quantityToSend;
	}

	public AdminProductRequestDTO(Long productId, Integer quantityToSend) {
		super();
		this.productId = productId;
		this.quantityToSend = quantityToSend;
	}

	public AdminProductRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
