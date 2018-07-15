package com.store.management.storemanagement.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

// Domain class for Product in database
@Entity
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduct;
	
	@NotNull(message = "Product name cannot be null")
	@Column(name="product")
	private String product;
	
	@NotNull(message = "Product price cannot be null")
	@DecimalMin(value = "0.01", inclusive = true, message="Price must be equal or greater than 0.01$")
	@Column(name="price")
	private Double price;
	
	@NotNull(message = "Product stock cannot be null")
	@Min(value=0, message="Stock must be equal or greater than 0")
	@Column(name="stock")
	private Integer stock;
	
	@OneToMany(mappedBy="product", fetch=FetchType.LAZY)
	private Collection<Purchase> purchases = new ArrayList<Purchase>();
	
	@OneToMany(mappedBy="product", fetch=FetchType.LAZY)
	private Collection<Like> likes = new ArrayList<Like>();
	
	@OneToMany(mappedBy="product", fetch=FetchType.LAZY)
	private Collection<ProductLog> productlog = new ArrayList<ProductLog>();
	
	public Product() {}
	
	public Product(String product, Double price, Integer stock){
		this.product = product;
		this.stock = stock;
		this.price = price;
	}
	
	public Long getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}
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
	public Collection<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Collection<Purchase> purchases) {
		this.purchases = purchases;
	}
	
	public Collection<Like> getLikes() {
		return likes;
	}
	public void setLikes(Collection<Like> likes) {
		this.likes = likes;
	}
	public Collection<ProductLog> getProductlog() {
		return productlog;
	}
	public void setProductlog(Collection<ProductLog> productlog) {
		this.productlog = productlog;
	}
	@Override
	public String toString() {
		return "{id:"+this.idProduct+",product:"+this.product+"}";
	}
	
	
}
