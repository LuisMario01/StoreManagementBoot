package com.store.management.storemanagement.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.store.management.storemanagement.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	// Creating products methods
	public Product save(Product product);
	
	
	// Searching methods
	public Product findByProduct(String product);
	public Product findByIdProduct(Long idProduct);
	
	// Listing methods
	public List<Product> findAll();
	
	public List<Product> findAllByOrderByProductAsc();
	
	public List<Product> findAllByOrderByProductDesc(Pageable page);
	public List<Product> findAllByOrderByProductAsc(Pageable page);
	
}
