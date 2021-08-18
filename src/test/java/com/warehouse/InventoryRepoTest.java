package com.warehouse;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.warehouse.app.WmsApplication;
import com.warehouse.dao.model.ArticleDao;
import com.warehouse.repo.InventoryRepository;

@SpringBootTest(classes = WmsApplication.class)
@RunWith(SpringRunner.class)
@ActiveProfiles({ "test" })
class InventoryRepoTest {

	
	@Autowired
	private InventoryRepository inventoryRepository;
	

    @BeforeEach
    void setUp() throws Exception {
    	
    	inventoryRepository.save(ArticleDao.builder().id("1")
    			.name("WindowSheet")
    			.stock(200).build());
    	inventoryRepository.save(ArticleDao.builder().id("2")
    			.name("Sofa cover ")
    			.stock(40).build());
    }
	

    @Test
    void findAllshouldBeNotEmpty() {
        assertFalse(inventoryRepository.findAll().isEmpty());
    }
    
    @Test
    void findByIdShouldNotEmpty() {
        assertFalse(inventoryRepository.findById("1").isEmpty());
        assertEquals("1", inventoryRepository.findById("1").get().getId());
        assertEquals("WindowSheet",inventoryRepository.findById("1").get().getName());
        assertEquals(200,inventoryRepository.findById("1").get().getStock());
    }
    
    
    @Test
    void findByIdShouldBeEmpty() {
        assertTrue(inventoryRepository.findById("17").isEmpty());
    }
}
