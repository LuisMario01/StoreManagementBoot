package com.store.management.storemanagement.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.store.management.storemanagement.domain.Product;
import com.store.management.storemanagement.repository.ProductRepository;

@Service
public class ProductRepositoryService {
	@Autowired
	ProductRepository productRepository;
	
	
	public boolean loadProductData() {
		boolean result;
		try {		
			productRepository.save(new Product("Chocolate", 1.25, 200));
			productRepository.save(new Product("Milk", 1.5, 100));
			productRepository.save(new Product("Doritos", 0.75, 200));
			productRepository.save(new Product("Almonds", 2.0, 89));
			result = true;
		}catch(Exception e) {
			result = false;
		}
		return result;
	}
	
	public ResponseEntity<String> findAllAsc() {
		ResponseEntity<String> response;
		List<Product> products;
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			products = productRepository.findAll();
			String json = gson.toJson(products);
			response = new ResponseEntity(json, HttpStatus.OK);
		}catch(Exception e){
			response = new ResponseEntity("Failed when loading products", HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	// Method to show existing list of products sorted ascendantly by name
	public ResponseEntity<String> findAllSorted(String page, String sort) {
		ResponseEntity<String> response;
		List<Product> products;
		String result = "";
		try {
			int pageNumber = Integer.parseInt(page);
			int sortType = Integer.parseInt(sort);
			if(sortType==0) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				products = productRepository.findAllByOrderByProductAsc(PageRequest.of(pageNumber-1,3));
				String json = gson.toJson(products);
				response = new ResponseEntity<String>(json, HttpStatus.OK);
			}
			// Missing sort type = 1
			else {
				response = new ResponseEntity<String>("Sort 1", HttpStatus.OK);
			}
			
		}catch(Exception e){
			result = "Failed";
			response = new ResponseEntity<String>("Failed", HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	public ResponseEntity<String> saveOneProduct(HttpServletRequest request, Product product){
		ResponseEntity<String> response;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response = new ResponseEntity(gson.toString(), HttpStatus.OK);
		return response;
	}
	
	public ResponseEntity<String> showProductByName(String productParam) {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Product product = productRepository.findByProduct(productParam);
		    String json = gson.toJson(product);
		    return new ResponseEntity<>(json, HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
		}
	}
}
