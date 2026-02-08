package com.abacus.franchise.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.dto.ProductDetail;
import com.abacus.franchise.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
	
	@Query(value = "SELECT product_id,product_name,quantity FROM product where is_active = true",nativeQuery = true)
	List<ProductDetail> getAllProductDetail();

}
