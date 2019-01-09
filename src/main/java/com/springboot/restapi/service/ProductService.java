package com.springboot.restapi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restapi.dao.ProductDao;
import com.springboot.restapi.entity.ProductEntity;
import com.springboot.restapi.pojos.Product;

@Service("productService")
public class ProductService {

	@Autowired
	private ProductDao productDao;

	@Transactional
	public void addProduct(Product product) {
		ProductEntity pEntity = new ProductEntity();
		pEntity.setCategoryId(product.getCategoryId());
		pEntity.setCost(product.getCost());
		pEntity.setId(product.getId());
		pEntity.setName(product.getName());
		productDao.addProduct(pEntity);
	}

	public Product getProductById(int id) {
		ProductEntity pEntity = productDao.getProductById(id);
		if (pEntity != null) {
			Product prod = new Product();
			prod.setCategoryId(pEntity.getCategoryId());
			prod.setCost(pEntity.getCost());
			prod.setId(pEntity.getId());
			prod.setName(pEntity.getName());
			return prod;
		}
		return null;
	}

	public List<Product> getAllProducts() {
		List<ProductEntity> prodEntityList = productDao.getAllProducts();
		List<Product> prodList = new ArrayList<>();
		for (Iterator<ProductEntity> iterator = prodEntityList.iterator(); iterator.hasNext();) {
			ProductEntity productEntity = (ProductEntity) iterator.next();
			Product prod = new Product();
			prod.setCategoryId(productEntity.getCategoryId());
			prod.setCost(productEntity.getCost());
			prod.setId(productEntity.getId());
			prod.setName(productEntity.getName());
			prodList.add(prod);
		}
		return prodList;
	}

	public Product deleteProductById(int id) {
		ProductEntity productEntity = productDao.deleteProductById(id);
		Product prod = null;
		if (productEntity != null) {
			prod = new Product();
			prod.setCategoryId(productEntity.getCategoryId());
			prod.setCost(productEntity.getCost());
			prod.setId(productEntity.getId());
			prod.setName(productEntity.getName());
		}
		return prod;
	}
	
}
