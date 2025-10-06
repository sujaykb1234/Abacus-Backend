package com.abacus.franchise.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.ProductOrderRequest;

@Repository
public interface ProductOrderRequestRepo extends JpaRepository<ProductOrderRequest, Long> {

	@Query(value = "select * from product_order_request where franchise_order_id = ?1 and product_id = ?2", nativeQuery = true)
	Optional<ProductOrderRequest> findByFranchiseOrderAndProduct(Long long1, Long long2);

	@Query(value = "select * from product_order_request where product_id = ?1", nativeQuery = true)
	List<ProductOrderRequest> findAllByProductId(Long productId);

}
