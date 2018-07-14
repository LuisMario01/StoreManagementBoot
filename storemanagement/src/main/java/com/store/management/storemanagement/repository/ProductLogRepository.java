package com.store.management.storemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.management.storemanagement.domain.ProductLog;

@Repository
public interface ProductLogRepository extends JpaRepository<ProductLog, Integer>{
	public ProductLog save(ProductLog productLog);
}
