package com.warehouse.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.warehouse.app.WmsApplication;
import com.warehouse.dao.model.ArticleDao;
import com.warehouse.dao.model.PArticleDao;
import com.warehouse.dao.model.ProductDao;
import com.warehouse.enums.ArticleStock;
import com.warehouse.exception.OutOfStockException;
import com.warehouse.mapper.ProductMapper;
import com.warehouse.model.Product;
import com.warehouse.repo.InventoryRepository;
import com.warehouse.repo.ProductRepository; 


@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=WmsApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
class ProductServiceUnitTest {

	
	private ProductService productService;

	@Mock
	private ProductRepository productRepo;

	@Autowired
	private ProductMapper mapper;
	
	@Mock
	private InventoryRepository articlesRepo;
	
	private List<ProductDao> products;
	
	ArticleDao article1;
	
	ArticleDao article2;
	
	@BeforeAll
	void init(){
		MockitoAnnotations.initMocks(this);
		products = 
    			Arrays.asList(ProductDao.builder()
    										.id("pid1234")
    					                    .name("Shelving Unit")
    					                    .price(BigDecimal.valueOf(34.68))
    					                    .articles(Arrays.asList(PArticleDao.builder().artId("23").amountOf(9).build())).build(),
    					      ProductDao.builder()
    					      				.id("pid6789")
    					      				.name("Corner Shelving Unit")
    					      				.price(BigDecimal.valueOf(199))
    					      				.articles(Arrays.asList(PArticleDao.builder().artId("23").amountOf(9).build())).build(),
    					      ProductDao.builder()
    					      				.id("pid9876")
    					      				.name("BookCase")
    						 				.price(BigDecimal.valueOf(390))
    						 				.articles(Arrays.asList(PArticleDao.builder().artId("78").amountOf(6).build())).build());
		
		productService = new ProductService(mapper, productRepo, articlesRepo);
		
		article1 = ArticleDao.builder()
				.id("23")
				.stock(10)
				.name("type34Screws").build();
		
		article2 = ArticleDao.builder()
				.id("78")
				.stock(10)
				.name("typeZ98Bolts").build();
	}

    @Test 
    void testStockAvailability() {
    	when(productRepo.findAll()).thenReturn(products);
    	article1.setStock(20);
    	article2.setStock(10);
    	when(articlesRepo.findById("23")).thenReturn(Optional.of(article1));
    	when(articlesRepo.findById("78")).thenReturn(Optional.of(article2));
    	
    	List<Product> products = productService.getAllProducts();
    	
    	
    	assertEquals(ArticleStock.INSTOCK.getAvailability(), products.get(0).getArticles().get(0).getAvailabilty());
    	assertEquals(ArticleStock.INSTOCK.getAvailability(), products.get(1).getArticles().get(0).getAvailabilty());
    	assertEquals(ArticleStock.INSTOCK.getAvailability(), products.get(2).getArticles().get(0).getAvailabilty());
    }
    

    @Test 
    void testStockUnAvailabilityForArticleId23() {
    	when(productRepo.findAll()).thenReturn(products);
    	article1.setStock(10);
    	article2.setStock(7);
    	when(articlesRepo.findById("23")).thenReturn(Optional.of(article1));
    	when(articlesRepo.findById("78")).thenReturn(Optional.of(article2));
    	List<Product> products = productService.getAllProducts();
    	
    	assertEquals(ArticleStock.INSTOCK.getAvailability(), products.get(0).getArticles().get(0).getAvailabilty());
    	assertEquals(ArticleStock.OUTOFSTOCK.getAvailability(), products.get(1).getArticles().get(0).getAvailabilty());
    	assertEquals(ArticleStock.INSTOCK.getAvailability(), products.get(2).getArticles().get(0).getAvailabilty());
    }

    
    @Test 
    void testStockUnAvailabilityForArticleId78() {
    	when(productRepo.findAll()).thenReturn(products);
    	article1.setStock(5);
    	article2.setStock(2);
    	when(articlesRepo.findById("23")).thenReturn(Optional.of(article1));
    	when(articlesRepo.findById("78")).thenReturn(Optional.of(article2));
    	
    	List<Product> products = productService.getAllProducts();
    	
    	
    	assertEquals(ArticleStock.OUTOFSTOCK.getAvailability(), products.get(0).getArticles().get(0).getAvailabilty());
    	assertEquals(ArticleStock.OUTOFSTOCK.getAvailability(), products.get(1).getArticles().get(0).getAvailabilty());
    	assertEquals(ArticleStock.OUTOFSTOCK.getAvailability(), products.get(2).getArticles().get(0).getAvailabilty());
    }
 
    
    @Test 
    void testDeleteAProduct() {
    	article1.setStock(24);
    	when(productRepo.findById("pid1234")).thenReturn(Optional.of(products.get(0)));
    	when(articlesRepo.findById("23")).thenReturn(Optional.of(article1));
    	when(articlesRepo.save(article1)).thenReturn(article1);
    	doNothing().when(productRepo).delete(products.get(0));
    	productService.deleteProduct("pid1234");
    	
    	Mockito.verify(articlesRepo, Mockito.times(1)).save(article1);
    	Mockito.verify(productRepo, Mockito.times(1)).deleteById(products.get(0).getId());
    	assertEquals(15, article1.getStock());
    }
    
    @Test 
    void testDeleteAProductStockUnavailability() {
    	article1.setStock(1);
    	when(productRepo.findById("pid1234")).thenReturn(Optional.of(products.get(0)));
    	when(articlesRepo.findById("23")).thenReturn(Optional.of(article1));
    	when(articlesRepo.save(article1)).thenReturn(article1);
    	doNothing().when(productRepo).delete(products.get(0));
     	
    	

    	OutOfStockException outOfStockException = assertThrows(OutOfStockException.class,
				() -> productService.deleteProduct("pid1234"));

		assertEquals("Insufficient stocks: 23 for the product: pid1234",outOfStockException.getMessage());
    	
    	Mockito.verify(articlesRepo, Mockito.times(0)).save(article1);
    	Mockito.verify(productRepo, Mockito.times(0)).deleteById(products.get(0).getId());
    	
    }
}
