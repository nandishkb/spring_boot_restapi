package com.springboot.restapi.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.springboot.restapi.pojos.Product;
import com.springboot.restapi.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@Test
	public void testWelcomeMessage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/welcome").accept(MediaType.ALL)).andExpect(status().isOk())
				.andExpect(content().string("Welcome to Product REST API Version 1.0"));
	}

	@Test
	public void testGetAllProducts() throws Exception {

		Mockito.when(productService.getAllProducts()).thenReturn(getPreparedProductList(3));
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/v1/product/").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		System.out.println("ProductControllerTest.testGetAllProducts() === "+contentAsString);
		Mockito.verify(productService).getAllProducts();
	}
	
	private List<Product> getPreparedProductList(int num) {
		ArrayList<Product> prodList = new ArrayList<>();
		for (int i = 0 ; i < num ; i++) {
			Product prod = new Product();
			prod.setName("prod"+i);
			prod.setId(i+1);
			prod.setCost(i+10);
			prod.setCategoryId(prod.getId()+""+prod.getName()+""+prod.getCost());
			prodList.add(prod);
		}
		return prodList;
	}
}