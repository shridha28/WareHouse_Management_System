package com.warehouse;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.warehouse.app.WmsApplication;
import com.warehouse.dao.model.PArticleDao;
import com.warehouse.dao.model.ProductDao;
import com.warehouse.model.PArticleModel;
import com.warehouse.repo.ProductRepository;


@SpringBootTest(classes = WmsApplication.class)
@RunWith(SpringRunner.class)
@ActiveProfiles({ "test" })
public class ProductRepoTest {
	
	@Autowired
	private ProductRepository productRepository;
	

    @BeforeEach
    public void setUp() throws Exception {
    	
    	productRepository.save(ProductDao.builder().id("1")
    			.name("WindowSheet")
    			.articles(Arrays.asList(
    					  PArticleDao.builder()
    								 .artId("1")
    								 .amountOf(4).build(),
    								 
    					  PArticleDao.builder()
    								 .artId("3")
    								 .amountOf(5).build())).build());
    	
    }
	

    @Test
    public void findAllshouldBeNotEmpty() {
        assertFalse(productRepository.findAll().isEmpty());
    }
    
    @Test
    public void findByIdShouldNotEmpty() {
        assertFalse(productRepository.findById("1").isEmpty());
        assertEquals(productRepository.findById("1").get().getId(), "1");
        assertEquals(productRepository.findById("1").get().getName(), "WindowSheet");
        assertFalse(productRepository.findById("1").get().getArticles().isEmpty());
    }
    
    
    @Test
    public void findByIdShouldBeEmpty() {
        assertTrue(productRepository.findById("17").isEmpty());
    }
}
