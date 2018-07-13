package com.store.management.storemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.store.management.storemanagement.service.ProductRepositoryService;


@RestController
public class MainController{
	@Autowired
	public ProductRepositoryService productRepositoryService;
	
	// Product listing method. Shows everything, no authentication required.
	@RequestMapping(value = "/findAll", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> showAllProducts(){
		ResponseEntity<String> response = productRepositoryService.findAll();
		return response;
	}
	
	// Product listing method. Shows everything, no authentication required.
	@RequestMapping(value = {"/products"}, 
		params = {"page", "sort"}, 
		method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> showAllProductsSorted(@RequestParam("page")String page, @RequestParam("sort")String sort){
		ResponseEntity<String> response = productRepositoryService.findAllSorted(page,sort);
		return response;
	}
	
	
	
}
