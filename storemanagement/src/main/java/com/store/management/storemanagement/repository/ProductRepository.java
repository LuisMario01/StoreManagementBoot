package com.store.management.storemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.management.storemanagement.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	public Product save(Product product);
	public List<Product> findAll();
}
