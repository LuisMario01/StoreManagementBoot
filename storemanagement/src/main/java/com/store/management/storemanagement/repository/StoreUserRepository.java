package com.store.management.storemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.management.storemanagement.domain.StoreUser;

public interface StoreUserRepository extends JpaRepository<StoreUser, Long>{
	
	// Create methods
	public StoreUser save(StoreUser user);
	
	// Searching methods
	public StoreUser findByUsername(String username);
}
