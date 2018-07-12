package com.store.management.storemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.management.storemanagement.domain.Product;
import com.store.management.storemanagement.repository.ProductRepository;


@RestController
public class MainController{
	@Autowired
	public ProductRepository productRepository;
	
	@RequestMapping(value="/save")
	public void save() {
		productRepository.save(new Product("Chocolate", 1.25, 200));
		productRepository.save(new Product("Milk", 1.5, 100));
	}
	
	@RequestMapping(value = "/findall", method=RequestMethod.GET)
	public ResponseEntity<String> test(){
		ResponseEntity<String> response;
		String result = "";
        
        for(Product product : productRepository.findAll()){
        	result += product.toString() + "</br>";
        }
           
        response = new ResponseEntity<>(result, HttpStatus.FOUND);
		return response;
	}
	
}
