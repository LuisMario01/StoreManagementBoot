package com.store.management.storemanagement.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

// Data Transfer Object to add a new purchase to database
public class PurchaseDTO {
	@NotNull(message = "Field: idProduct must not be empty")
	private Integer idProduct;
	
	@NotNull(message = "Field: Amount must not be empty")
	@Min(message="Amount must be equal or greater than 1", value=1)
	private Integer amount;

	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
