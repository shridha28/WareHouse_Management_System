package com.warehouse.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "art_id",
    "name",
    "stock"
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IArticleModel {

	@JsonProperty("art_id")
	private String artId;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("stock")
	private long stock;
	

    @JsonProperty("art_id")
    public String getArtId() {
        return artId;
    }

    @JsonProperty("art_id")
    public void setArtId(String artId) {
        this.artId = artId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }


    @JsonProperty("stock")
    public long getStock() {
        return stock;
    }

    @JsonProperty("stock")
    public void setStock(long stock) {
        this.stock = stock;
    }

}
