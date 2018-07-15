package com.store.management.storemanagement.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

// Data Transfer Object to a add a product to database
public class ProductDTO {

	@NotNull(message = "Product name cannot be null")
	private String product;
	
	@NotNull(message = "Product price cannot be null")
	@DecimalMin(value = "0.01", inclusive = true, message="Price must be equal or greater than 0.01$")
	private Double price;
	
	@NotNull(message = "Product stock cannot be null")
	@Min(value=0, message="Stock must be equal or greater than 0")
	private Integer stock;
	
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
}
