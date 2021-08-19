/**
 * 
 */
package com.warehouse.enums;

/**
 * @author Shridha S Jalihal
 * Enum Class to set availability of article stocks for the 
 */
public enum ArticleStock {
	OUTOFSTOCK("OUT-OF-STOCK"), INSTOCK("IN-STOCK");

	public final String availability;

	private ArticleStock(String availability) {
		this.availability = availability;
	}

	public String getAvailability() {
		return availability;
	}
}
