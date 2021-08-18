package com.warehouse.dao.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * ArticleDao pojo for Dao layer
 * 
 * @author Shridha S Jalihal
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Inventory")
public class ArticleDao {
	
	private String id;
	
	private String name;
	
	private long stock;

}
