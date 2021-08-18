package com.warehouse.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
import com.warehouse.model.PArticleModel;
import com.warehouse.model.Product;


@SpringBootTest(classes = WmsApplication.class)
@RunWith(SpringRunner.class)
@ActiveProfiles({ "test" })
class ProductServiceTest {

	
	@Autowired
	private ProductService productService;
	
	private static List<Product> products;
	
	@BeforeAll
	static void init(){
		products = 
    			Arrays.asList(Product.builder()
    					                    .id("pid1234").name("Shelving Unit")
    					                    .articles(Arrays.asList(PArticleModel.builder().artId("23").amountOf(9).build())).build(),
    					      Product.builder()
    					      				.id("pid6787").name("Corner Shelving Unit")
    					      				.articles(Arrays.asList(PArticleModel.builder().artId("23").amountOf(9).build())).build(),
    						  Product.builder()
    						 				.id("pid0989").name("BookCase")
    						 				.articles(Arrays.asList(PArticleModel.builder().artId("78").amountOf(6).build())).build());
		
	}

    @Test 
    void testProductsSavedSuccess() {
    	productService.saveProducts(products);
    	assertNotEquals(0,productService.getAllProducts().size());
    }
    
    @Test 
    void testProductsGetSuccess() {
    	List<Product> products = productService.getAllProducts();
    	assertNotEquals(0,productService.getAllProducts().size());
    	assertNotNull(products.get(0).getId());
    	assertNotNull(products.get(0).getName());
    	assertNotNull(products.get(0).getArticles());
    	assertNotNull(products.get(0).getArticles().get(0).getAvailabilty());
    }
    
}
