package com.store.management.storemanagement.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="likes")
public class Like {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLike;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user")
	private StoreUser user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idProduct")
	private Product product;
	
	public Long getIdLike() {
		return idLike;
	}
	public void setIdLike(Long idLike) {
		this.idLike = idLike;
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
	
	
	
}
