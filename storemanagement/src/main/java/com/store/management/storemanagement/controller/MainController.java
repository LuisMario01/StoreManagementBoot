package com.store.management.storemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.store.management.storemanagement.domain.StoreUser;
import com.store.management.storemanagement.dto.LoginDTO;
import com.store.management.storemanagement.service.ProductRepositoryService;
import com.store.management.storemanagement.service.StoreUserRepositoryService;


@RestController
public class MainController{
	@Autowired
	public ProductRepositoryService prs;
	
	@Autowired
	public StoreUserRepositoryService surs;
	
	//Login method.
	//Everyone is allowed to access this method.
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> login(@RequestBody LoginDTO login){
		ResponseEntity<String> results = surs.login(login);
		return results;
	}
	
	// Product listing method. Shows everything, no authentication required. No sorting.
	@RequestMapping(value = "/products", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> showAllProducts(){
		ResponseEntity<String> response = prs.findAllAsc();
		return response;
	}
	
	// Product listing method. Shows everything, no authentication required.
	@RequestMapping(value = {"/products"}, 
		params = {"page", "sort"}, 
		method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> showAllProductsSorted(@RequestParam("page")String page, @RequestParam("sort")String sort){
		ResponseEntity<String> response = prs.findAllSorted(page,sort);
		return response;
	}
	
	// Show one product, searching it by name
	@RequestMapping(value = "/products/{product}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> showProductByName(@PathVariable("product")String productname) {
		ResponseEntity<String> results = prs.showProductByName(productname);
		return results;   
	}
	
	
	
}
