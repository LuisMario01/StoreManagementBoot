package com.store.management.storemanagement.dto;

// Data Transfer Object to add a new purchase to database
public class PurchaseDTO {
	private Integer idProduct;
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
