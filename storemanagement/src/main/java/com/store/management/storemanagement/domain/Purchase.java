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
import javax.validation.constraints.NotNull;

@Entity
@Table(name="purchase")
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPurchase;
	
	@NotNull(message="Field: idUser cannot be null")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idUser")
	private StoreUser user;
	
	@NotNull(message="Field: idProduct cannot be null")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idProduct")
	private Product product;
	
	@NotNull(message="Field: amount cannot be null")
	@Column(name="amount")
	private Integer amount;
	
	@NotNull(message="Field: date cannot be null")
	@Column(name="date")
	private Date date;

	public Long getIdPurchase() {
		return idPurchase;
	}

	public void setIdPurchase(Long idPurchase) {
		this.idPurchase = idPurchase;
	}

	public StoreUser getUser() {
		return user;
	}

	public void setUser(StoreUser user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
