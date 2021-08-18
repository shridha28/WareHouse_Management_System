package com.warehouse.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.warehouse.dao.model.ArticleDao;

public interface InventoryRepository extends MongoRepository<ArticleDao, String> {
	
	
}
