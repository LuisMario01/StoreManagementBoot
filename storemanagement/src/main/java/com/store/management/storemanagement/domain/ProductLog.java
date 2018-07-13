package com.store.management.storemanagement.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="productlog")
public class ProductLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProductLog;
	
	@Column(name="previousPrice")
	private Double previousPrice;
	
	@Column(name="currentPrice")
	private Double currentPrice;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idProduct")
	private Product product;

	public Long getIdProductLog() {
		return idProductLog;
	}

	public void setIdProductLog(Long idProductLog) {
		this.idProductLog = idProductLog;
	}

	public Double getPreviousPrice() {
		return previousPrice;
	}

	public void setPreviousPrice(Double previousPrice) {
		this.previousPrice = previousPrice;
	}

	public Double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
