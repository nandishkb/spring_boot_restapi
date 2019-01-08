package com.springboot.restapi.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.restapi.entity.ProductEntity;
import com.springboot.restapi.repository.ProductRepository;

@Repository("productDao")
public class ProductDao {

	@Autowired
	private ProductRepository pRepo;
	
	public void addProduct(ProductEntity pEntity) {
		pRepo.saveAndFlush(pEntity);
	}

	public ProductEntity getProductById(int id) {
		Optional<ProductEntity> prod = pRepo.findById(id);
		ProductEntity pEntity = prod.isPresent() ? prod.get() : null;
		return pEntity;
	}

}
