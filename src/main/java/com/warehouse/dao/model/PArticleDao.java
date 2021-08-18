package com.warehouse.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PArticleDao (Product Article Dao) pojo for Dao layer
 * 
 * @author Shridha S Jalihal
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PArticleDao {

	private String artId;

	private long amountOf;
	
	private String availability;
}
