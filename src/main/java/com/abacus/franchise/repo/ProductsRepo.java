package com.abacus.franchise.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.Products;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Long> {

	@Query(value = "select * from products where status = true",nativeQuery = true)
	List<Products> findAllActiveProduct();

}
