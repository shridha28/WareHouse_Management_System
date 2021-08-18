package com.warehouse.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.warehouse.app.WmsApplication;
import com.warehouse.exception.InvalidJsonFileException;

/**
 * Class InventoryController. Controller for inventory API requests.
 * 
 * @author Shridha S Jalihal
 */
@SpringBootTest(classes = WmsApplication.class)
@RunWith(SpringRunner.class)
@ActiveProfiles({ "test" })
class ProductsControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	
	@Test
	void testImportProducts() throws Exception {
		Resource fileResource = new ClassPathResource("products.json");

		MockMultipartFile firstFile = new MockMultipartFile("file", fileResource.getFilename(),
				MediaType.APPLICATION_JSON_VALUE, fileResource.getInputStream());
		mockMvc.perform(MockMvcRequestBuilders.multipart("/api/products").file(firstFile))
				.andExpect(status().isCreated());
	}
	
	@Test
	void testGetProducts() throws Exception {
		mockMvc.perform(get("/api/products"))
		 .andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	void testGetProductsEmptyCollection() throws Exception {
		mongoTemplate.remove(new Query(),"Products");
		mockMvc.perform(get("/api/products"))
		 .andExpect(content().json("[]"));
	}
	
	
	@Test
	public void testImportProductsWithIncorrectProperty() throws Exception {
		Resource fileResource = new ClassPathResource("pro_incorrectproperty.json");

		MockMultipartFile firstFile = new MockMultipartFile("file", fileResource.getFilename(),
				MediaType.APPLICATION_JSON_VALUE, fileResource.getInputStream());
		mockMvc.perform(MockMvcRequestBuilders.multipart("/api/products").file(firstFile))
				.andExpect(status().is4xxClientError())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidJsonFileException));
	}
	
	
}
