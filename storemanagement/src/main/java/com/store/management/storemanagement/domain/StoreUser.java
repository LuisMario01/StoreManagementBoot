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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

// Domain class for StoreUser in database
@Entity
@Table(name="storeuser", uniqueConstraints = {
		@UniqueConstraint(columnNames = "username") })

public class StoreUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	
	//Make sure column is unique, since this is used to identify a user from login.
	@NotNull(message = "Field: username cannot be null")
	@Column(name="username", unique = true, length=8)
	private String username;
	
	@Column(name="password", length=8)
	@NotNull(message = "Field: password cannot be null")
	private String password;
	
	@Column(name="role")
	@NotNull(message = "Field: role cannot be null")
	private Integer role;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private Collection<Purchase> purchases = new ArrayList<Purchase>();
	
	protected StoreUser() {}
	
	public StoreUser(String username, String password, Integer role) {
		this.password = password;
		this.username = username;
		this.role = role;
	}
	
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	
	public Collection<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Collection<Purchase> purchases) {
		this.purchases = purchases;
	}
}
