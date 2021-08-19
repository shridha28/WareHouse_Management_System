package com.warehouse.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.warehouse.app.WmsApplication;
import com.warehouse.exception.ProductNotFoundException;
import com.warehouse.model.PArticleModel;
import com.warehouse.model.Product;


@SpringBootTest(classes = WmsApplication.class)
@RunWith(SpringRunner.class)
@ActiveProfiles({ "test" })
@TestInstance(Lifecycle.PER_CLASS)
class ProductServiceTest {

	
	@Autowired
	private ProductService productService;
	
	private List<Product> products;
	
	@BeforeAll
	void init(){
		products = 
    			Arrays.asList(Product.builder()
    					                    .name("Shelving Unit")
    					                    .price(BigDecimal.valueOf(34.68))
    					                    .articles(Arrays.asList(PArticleModel.builder().artId("23").amountOf(9).build())).build(),
    					      Product.builder()
    					      				.name("Corner Shelving Unit")
    					      				.price(BigDecimal.valueOf(199))
    					      				.articles(Arrays.asList(PArticleModel.builder().artId("23").amountOf(9).build())).build(),
    						  Product.builder()
    						 				.name("BookCase")
    						 				.price(BigDecimal.valueOf(390))
    						 				.articles(Arrays.asList(PArticleModel.builder().artId("78").amountOf(6).build())).build());
		
		productService.saveProducts(products);
	}

    @Test 
    void testProductsSavedSuccess() {
    	assertNotEquals(0,productService.getAllProducts().size());
    }
    
    @Test 
    void testProductsGetSuccess() {
    	
    	List<Product> products = productService.getAllProducts();
    	assertNotEquals(0,products.size());
    	assertNotNull(products.get(0).getId());
    	assertNotNull(products.get(0).getName());
    	assertNotNull(products.get(0).getArticles());
    	assertNotNull(products.get(0).getArticles().get(0).getAvailabilty());
    	assertNotNull(products.get(0).getArticles().get(0).getArtId());
    	assertNotNull(products.get(0).getArticles().get(0).getAmountOf());
    }
   
    
    @Test 
    void testProductGetSuccess() {
    	List<Product> products = productService.getAllProducts();
    	Product product = productService.getProduct(products.get(0).getId());
    	assertNotNull(product.getId());
    	assertNotNull(product.getName());
    	assertNotNull(product.getPrice());
    	assertNotNull(product.getArticles());
    	assertNotNull(product.getArticles().get(0).getAvailabilty());
    }
    
    
	@Test
	void testProductGetThrowsException() {

		ProductNotFoundException productNotFoundException = assertThrows(ProductNotFoundException.class,
				() -> productService.getProduct("pid4444"));

		assertEquals("Product With Id: pid4444 does not exist",productNotFoundException.getMessage());
	}
	
}
