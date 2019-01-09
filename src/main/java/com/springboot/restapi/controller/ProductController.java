package com.springboot.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restapi.pojos.Product;
import com.springboot.restapi.service.ProductService;

@RestController
@RequestMapping("api/v1")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "welcome", method = RequestMethod.GET)
	public String welcomeMessage() {
		return "Welcome to Product REST API Version 1.0";
	}

	@RequestMapping(value = "product/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addProduct(@RequestBody Product product) {
		System.out.println("ProductController.addProduct() = " + product);
		productService.addProduct(product);
		return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "product/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
		Product product = productService.getProductById(id);
		if (product != null) {
			return new ResponseEntity<>(product, HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(value = "product/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> productList = productService.getAllProducts();
		return new ResponseEntity<>(productList, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "product/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> deleteProductById(@PathVariable("id") int id) {
		Product product = productService.deleteProductById(id);
		if (product != null) {
			return new ResponseEntity<>(product, HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
