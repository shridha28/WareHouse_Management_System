package com.warehouse;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

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
import com.warehouse.dao.model.PArticleDao;
import com.warehouse.dao.model.ProductDao;
import com.warehouse.repo.ProductRepository;


@SpringBootTest(classes = WmsApplication.class)
@RunWith(SpringRunner.class)
@ActiveProfiles({ "test" })
@TestInstance(Lifecycle.PER_CLASS)
class ProductRepoTest {
	
	@Autowired
	private ProductRepository productRepository;

    @BeforeAll
    void setUp() throws Exception {
    	
    	productRepository.save(ProductDao.builder().id("1")
    			.name("WindowSheet")
    			.price(BigDecimal.valueOf(89))
    			.articles(Arrays.asList(
    					  PArticleDao.builder()
    								 .artId("1")
    								 .amountOf(4).build(),
    					  PArticleDao.builder()
    								 .artId("3")
    								 .amountOf(5).build())).build());
    	
    }
	

    @Test
    void testFindAllshouldBeNoBeEmpty() {
        assertFalse(productRepository.findAll().isEmpty());
    }
    
    @Test
    void testFindByIdShouldNotBeEmpty() {
        assertFalse(productRepository.findById("1").isEmpty());
        assertEquals("1",productRepository.findById("1").get().getId() );
        assertEquals("WindowSheet",productRepository.findById("1").get().getName() );
        assertFalse(productRepository.findById("1").get().getArticles().isEmpty());
    }
    
    @Test
    void testFindByIdShouldBeEmpty() {
        assertTrue(productRepository.findById("17").isEmpty());
    }
    
}
