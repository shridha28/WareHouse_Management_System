package com.warehouse.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehouse.exception.InvalidJsonFileException;
import com.warehouse.model.IArticleModel;
import com.warehouse.model.Inventory;
import com.warehouse.service.InventoryService;

/**
 * Class InventoryController. Controller for inventory API requests.
 * 
 * @author Shridha S Jalihal
 */
@CrossOrigin(origins = "http://localhost:8083", maxAge = 3600)
@RestController
public class InventoryController {

	private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);
	
	
	private InventoryService inventoryService;
	
	@Autowired
	public InventoryController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}
	
   /*
	* End Point to import articles in the database
	* 
	* @param MultipartFile file to import articles from
	* 
	* @throws JsonParseException if parsing the file fails.
	* 
	* @throws JsonMappingException if mapping the json file to java beans fails.
	* 
	* @throws IOException if reading the file fails.
	*/
	@PostMapping(path = "/api/articles")
	public ResponseEntity<String> importArticles(@RequestParam("file") MultipartFile file) {

		Inventory inventory = null;
		try {
			inventory = new ObjectMapper().readValue(file.getBytes(), Inventory.class);
		} catch (IOException e) {
			logger.error("Could not parse the import file.",e);
			throw new InvalidJsonFileException("Incorrect Json File uploaded.");
		}

		if(inventory!=null)
		  inventoryService.saveArticles(inventory.getInventory());
		
		logger.info("Successfully imported articles in the Inventory Collection");
		return new ResponseEntity<>("Successfully imported articles into the inventory.",HttpStatus.CREATED);
	}

   /*
	* End Point to get all the articles
	* 
	* @return List of articles of type IArticleModel
	*/
	@GetMapping(path = "/api/articles")
	public List<IArticleModel> getArticles() {
		return inventoryService.getArticles();
	}

}
