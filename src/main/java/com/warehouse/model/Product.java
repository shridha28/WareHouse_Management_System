package com.warehouse.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	
	private String id;
	
	@JsonProperty("price")
	private BigDecimal price;
		
	@JsonProperty("name")
	private String name;

	@JsonProperty("contain_articles")
	private List<PArticleModel> articles;

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("contain_articles")
	public List<PArticleModel> getArticles() {
		return articles;
	}

	@JsonProperty("contain_articles")
	public void setArticles(List<PArticleModel> articles) {
		this.articles = articles;
	}

}
