package com.store.management.storemanagement.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.store.management.storemanagement.domain.Product;


@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepository extends JpaRepository<Product, Long>{
	// Creating products methods
	public Product save(Product product);
	
	// Searching methods
	public Product findByProduct(String product);
	public Product findByIdProduct(Long idProduct);
	
	// Listing methods
	public List<Product> findAll();
	
	public List<Product> findAllByOrderByProductAsc();
	
	@Query(value="select product.id_product, product.product, product.stock, product.price, count(likes.id_like)  "
			+ "from product LEFT JOIN likes ON product.id_product = likes.id_product GROUP BY (product.id_product) "
			+ "ORDER by count(likes.id_like);", nativeQuery=true)
	public List<Object> findAllSortedByLikes();
	
	public List<Product> findAllByOrderByProductDesc(Pageable page);
	public List<Product> findAllByOrderByProductAsc(Pageable page);
	
	// Deleting methods
	public void deleteByIdProduct(Long idProduct);
	
}
