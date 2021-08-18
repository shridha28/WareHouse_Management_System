package com.warehouse.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Products {
	
   @JsonProperty("products")	
   private List<Product> productList;
   

}
