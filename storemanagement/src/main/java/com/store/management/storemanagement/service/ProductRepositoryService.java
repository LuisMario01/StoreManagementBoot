package com.store.management.storemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.management.storemanagement.domain.Product;
import com.store.management.storemanagement.repository.ProductRepository;

@Service
public class ProductRepositoryService {
	@Autowired
	ProductRepository productRepository;
	public boolean createDataIntoBase() {
		boolean result;
		try {
			productRepository.save(new Product("Chocolate", 1.25, 200));
			productRepository.save(new Product("Milk", 1.5, 100));
			result = true;
		}catch(Exception e) {
			result = false;
		}
		return result;
	}
	
	public String findAll() {
		List<Product> products;
		String result = "";
		try {
			for(Product product : productRepository.findAll()){
	        	result += product.toString() + "</br>";
	        }
		}catch(Exception e){
			
		}
		return result;
	}
}
