package com.warehouse.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehouse.exception.InvalidJsonFileException;
import com.warehouse.model.Product;
import com.warehouse.model.Products;
import com.warehouse.service.ProductService;


/**
 * Class ProductController. Controller for product API requests.
 * 
 * @author Shridha S Jalihal
 */
@CrossOrigin(origins = "http://localhost:8083", maxAge = 3600)
@RestController
public class ProductController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;
	
	/*
	 * End Point to import products in the database
	 * 
	 * @param MultipartFile file to import articles from
	 * 
	 * @throws JsonParseException if parsing the file fails.
	 * 
	 * @throws JsonMappingException if mapping the json file to java beans fails.
	 * 
	 * @throws InvalidJsonFileException if file is incorrect.
	 */
	@PostMapping(path = "/api/products")
	public ResponseEntity<String> importProducts(@RequestParam("file") MultipartFile file) {

		Products products = null;
		try {
			products = new ObjectMapper().readValue(file.getBytes(), Products.class);
		} catch (IOException e) {
			logger.error("Could not parse the import file.",e);
			throw new InvalidJsonFileException("Incorrect Json File uploaded.");
		}

		if(products!=null)
			productService.saveProducts(products.getProductList());
		
		logger.info("Successfully imported products");
		return new ResponseEntity<String>("Successfully imported Products",HttpStatus.CREATED);
	}

	/*
	 * End Point to get all the products and stocks availability of the products' articles
	 * 
	 * @return List of products of type Product
	 */
	@GetMapping(path = "/api/products")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}


	/*
	 * End Point to get delete(sell) a product and update stock of the articles
	 * 
	 * @return ResponseEntity with no body
	 */
	@DeleteMapping(path = "/api/products/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable String productId)  {
		 productService.deleteProduct(productId);
		 return new ResponseEntity<String>("Successfully deleted the product.",HttpStatus.OK);
	}

	/*
	 * End Point to get a product with productId
	 * 
	 * @return product of type Product
	 */
	@GetMapping(path = "/api/products/{productId}")
	public Product getProduct(@PathVariable String productId)  {
		 return productService.getProduct(productId);
	}
}
