package com.warehouse.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.warehouse.app.WmsApplication;
import com.warehouse.model.IArticleModel;


@SpringBootTest(classes = WmsApplication.class)
@RunWith(SpringRunner.class)
@ActiveProfiles({ "test" })
public class InventoryServiceTest {

	
	@Autowired
	InventoryService inventoryService;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	List<IArticleModel> articles;

    @BeforeEach
    public void setUp() throws Exception {
    	 articles = 
    			Arrays.asList(IArticleModel.builder()
    					                    .artId("78").name("WardrobeDoors").stock(10).build(),
    					      IArticleModel.builder()
    					                    .artId("123851").name("ScrewType-123851").stock(25).build());
    	mongoTemplate.remove(new Query(),"Inventory");
     	inventoryService.saveArticles(articles);
    }

    @Test 
    public void testArticlesDoNotExistInDb() {
    	assertEquals(inventoryService.getArticles().size(),2);
    	List<IArticleModel> articles = inventoryService.getArticles();
    	assertEquals(articles.get(0).getStock(),10);
    	assertEquals(articles.get(1).getStock(),25);
    }
   
    
    @Test 
    public void testArticlesExistInDb() {
    	inventoryService.saveArticles(articles);
    	assertEquals(inventoryService.getArticles().size(),2);
    	List<IArticleModel> articles = inventoryService.getArticles();
    	assertEquals(articles.get(0).getStock(),20);
    	assertEquals(articles.get(1).getStock(),50);
    }
    
    @Test 
    public void testNoArticlesInDb() {
    	mongoTemplate.remove(new Query(),"Inventory");
    	assertEquals(inventoryService.getArticles().size(),0);
    }
}
