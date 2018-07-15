package com.store.management.storemanagement.dto;

import javax.validation.constraints.NotNull;

// Data Transfer Object to add a new like to database
public class LikeDTO {
	
	@NotNull(message="Field: idProduct must not be empty")
	private Integer idProduct;
	
	public Integer getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}
}
