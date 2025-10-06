package com.abacus.franchise.dto;

import java.util.List;

public class FranchiseOrderDTO {

	private Long franchiseId;
	private List<ProductOrderRequestDTO> productRequests;

	public Long getFranchiseId() {
		return franchiseId;
	}

	public void setFranchiseId(Long franchiseId) {
		this.franchiseId = franchiseId;
	}

	public List<ProductOrderRequestDTO> getProductRequests() {
		return productRequests;
	}

	public void setProductRequests(List<ProductOrderRequestDTO> productRequests) {
		this.productRequests = productRequests;
	}

	public FranchiseOrderDTO(Long franchiseId, List<ProductOrderRequestDTO> productRequests) {
		super();
		this.franchiseId = franchiseId;
		this.productRequests = productRequests;
	}

	public FranchiseOrderDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
