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
	public boolean createDataIntoBase() {
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
	
	public ResponseEntity<String> findAll() {
		ResponseEntity<String> response;
		List<Product> products;
		String result = "";
		try {
			for(Product product : productRepository.findAll()){
	        	result += product.toString() + "</br>";
	        }
			response = new ResponseEntity(result, HttpStatus.OK);
		}catch(Exception e){
			result = "Failed";
			response = new ResponseEntity("Failed", HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	// Method to show existing list of products sorted ascendantly by name
	public ResponseEntity<String> findAllSorted(String page, String sort) {
		ResponseEntity<String> response;
		String result = "";
		try {
			int pageNumber = Integer.parseInt(page);
			int sortType = Integer.parseInt(sort);
			if(sortType==0) {
				for(Product product : productRepository.findAllByOrderByProductAsc(new PageRequest(pageNumber-1, 3))){		        	
					result += product.toString() + "</br>";
		        }
			}
			response = new ResponseEntity(result, HttpStatus.OK);
		}catch(Exception e){
			result = "Failed";
			response = new ResponseEntity("Failed", HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	public ResponseEntity<String> saveOneProduct(HttpServletRequest request, Product product){
		ResponseEntity<String> response;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response = new ResponseEntity(gson.toString(), HttpStatus.OK);
		return response;
	}
}
