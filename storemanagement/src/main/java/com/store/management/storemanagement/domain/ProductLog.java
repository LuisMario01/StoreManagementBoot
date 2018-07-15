package com.store.management.storemanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="productlog")
public class ProductLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProductLog;
	
	@NotNull(message="Field: previousPrice cannot be null")
	@Column(name="previousPrice")
	private Double previousPrice;
	
	@NotNull(message="Field: currentPrice cannot be null")
	@Column(name="currentPrice")
	private Double currentPrice;
	
	@NotNull(message="Field: idProduct must not be null")
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
