package com.warehouse.dao.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.warehouse.model.PArticleModel;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



/**
 * ProductDao pojo for Dao layer
 * 
 * @author Shridha S Jalihal
 */
@Getter
@Setter
@Builder
@Document(collection = "Products")
public class ProductDao {
	
	private String id;
	
	private String name;
	
	private List<PArticleDao> articles;

	

}