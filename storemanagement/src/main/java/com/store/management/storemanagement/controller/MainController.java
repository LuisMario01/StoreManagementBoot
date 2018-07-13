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
import com.store.management.storemanagement.service.ProductRepositoryService;


@RestController
public class MainController{
	@Autowired
	public ProductRepositoryService productRepositoryService;
	
	@RequestMapping(value = "/findall", method=RequestMethod.GET)
	public ResponseEntity<String> test(){
		ResponseEntity<String> response;
		String result = productRepositoryService.findAll();
        response = new ResponseEntity<>(result, HttpStatus.FOUND);
		return response;
	}
	
}
