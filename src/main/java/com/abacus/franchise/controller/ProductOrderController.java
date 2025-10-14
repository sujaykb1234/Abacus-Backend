package com.abacus.franchise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abacus.franchise.dto.FranchiseOrderDTO;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.AdminService;
import com.abacus.franchise.service.FranchiseService;

@RestController
@RequestMapping("/abacus/v1/orders")
public class ProductOrderController {

	@Autowired
	FranchiseService franchiseService;

	@Autowired
	AdminService adminService;

	@PostMapping("/create")
	public SuccessResponse createOrder(@RequestBody FranchiseOrderDTO orderDTO) {
		return franchiseService.createOrder(orderDTO);
	}

	@GetMapping("/getOrdersByFranchiseId/{franchiseId}")
	public SuccessResponse getOrders(@PathVariable Long franchiseId) {
		return franchiseService.getOrdersByFranchise(franchiseId);
	}

	@GetMapping("/getAllProductOrders")
	public SuccessResponse getAllOrders() { 
		return adminService.getAllProductOrders();
	}

}
