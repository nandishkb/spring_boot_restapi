package com.springboot.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.restapi.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Number> {

	/**
	 * All Crud methods will be auto implemented here.
	 */
	
}
