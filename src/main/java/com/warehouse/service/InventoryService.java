package com.warehouse.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.warehouse.dao.model.ArticleDao;
import com.warehouse.mapper.InventoryMapper;
import com.warehouse.mapper.ProductMapper;
import com.warehouse.model.IArticleModel;
import com.warehouse.repo.InventoryRepository;
import com.warehouse.repo.ProductRepository;


/**
 * InventoryService service for articles to be persisted 
 * and retrieved from the inventory collection.
 * 
 * @author Shridha S Jalihal
 */
@Service
public class InventoryService {
	
	
	private static final Logger logger = LoggerFactory.getLogger(InventoryService.class);

	
	InventoryMapper mapper;

	
	InventoryRepository inventoryRepository;
	
	@Autowired
	public InventoryService(InventoryMapper mapper, InventoryRepository inventoryRepository) {
		this.mapper = mapper;
		this.inventoryRepository = inventoryRepository;
	}

   /*
	* Method to save articles
	* 
	* @param listOfArticles of type IArticleModel
	* 
	*/
	public void saveArticles(List<IArticleModel> listOfArticles) {

     logger.debug("Saving articles in Inventory collection");
		listOfArticles.forEach(article -> {
			Optional<ArticleDao> articleDao = inventoryRepository.findById(article.getArtId());
			if (articleDao.isPresent())
				article.setStock(article.getStock() + articleDao.get().getStock());
			inventoryRepository.save(mapper.convertToDao(article));
		});
	 logger.info("Saved articles in Inventory Collection");	
	}
	
   /*
	* Method to get articles from Inventory collection
	* 
	* @return listOfArticles of type IArticleModel
	* 
	*/
	public List<IArticleModel> getArticles() {
		
		List<IArticleModel> articles = new ArrayList<IArticleModel>();
		List<ArticleDao> articlesDaoList = inventoryRepository.findAll();
		for(ArticleDao articleDao : articlesDaoList) {
			articles.add(mapper.convertToModel(articleDao));
		}
		
	  logger.info("Successfully retrieved articles from Inventory Collection.");		
	  return articles;
	}


}
