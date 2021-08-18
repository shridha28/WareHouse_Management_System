package com.warehouse.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.warehouse.dao.model.ProductDao;

public interface ProductRepository extends MongoRepository<ProductDao, String> {

}
