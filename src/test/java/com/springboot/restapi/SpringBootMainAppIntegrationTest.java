package com.springboot.restapi;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT, classes=SpringBootMainApp.class)
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application.properties")
public class SpringBootMainAppIntegrationTest {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void contextLoads() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		System.out.println("SpringBootMainAppIntegrationTest.contextLoads() result = "+result.getResponse().getContentAsString());
	}
}
