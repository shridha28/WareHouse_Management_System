
package com.warehouse.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;



public class Inventory {

    @JsonProperty("inventory")
    private List <IArticleModel> articles;

    

    @JsonProperty("inventory")
    public List <IArticleModel> getInventory() {
        return articles;
    }

    @JsonProperty("inventory")
    public void setInventory(List <IArticleModel> articles) {
        this.articles = articles;
    }


}
