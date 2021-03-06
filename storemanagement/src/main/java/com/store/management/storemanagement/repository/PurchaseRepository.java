package com.store.management.storemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.management.storemanagement.domain.Purchase;

public interface PurchaseRepository  extends JpaRepository<Purchase, Long>{
	Purchase save(Purchase purchase);
}
