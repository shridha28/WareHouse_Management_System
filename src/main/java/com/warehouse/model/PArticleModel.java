package com.warehouse.model;

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
public class PArticleModel {

	@JsonProperty("art_id")
	private String artId;

	
	@JsonProperty("amount_of")
	private long amountOf;
	
	private String availabilty;

    @JsonProperty("art_id")
    public String getArtId() {
        return artId;
    }

    @JsonProperty("art_id")
    public void setArtId(String artId) {
        this.artId = artId;
    }


    @JsonProperty("amount_of")
    public long getAmountOf() {
        return amountOf;
    }

    @JsonProperty("amount_of")
    public void setAmountOf(long amountOf) {
        this.amountOf = amountOf;
    }

	
	
}
