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
public class InventoryRepoTest {

	
	@Autowired
	private InventoryRepository inventoryRepository;
	

    @BeforeEach
    public void setUp() throws Exception {
    	
    	inventoryRepository.save(ArticleDao.builder().id("1")
    			.name("WindowSheet")
    			.stock(200).build());
    	inventoryRepository.save(ArticleDao.builder().id("2")
    			.name("Sofa cover ")
    			.stock(40).build());
    }
	

    @Test
    public void findAllshouldBeNotEmpty() {
        assertFalse(inventoryRepository.findAll().isEmpty());
    }
    
    @Test
    public void findByIdShouldNotEmpty() {
        assertFalse(inventoryRepository.findById("1").isEmpty());
        assertEquals(inventoryRepository.findById("1").get().getId(), "1");
        assertEquals(inventoryRepository.findById("1").get().getName(), "WindowSheet");
        assertEquals(inventoryRepository.findById("1").get().getStock(), 200);
    }
    
    
    @Test
    public void findByIdShouldBeEmpty() {
        assertTrue(inventoryRepository.findById("17").isEmpty());
    }
}
