package com.store.management.storemanagement.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.store.management.storemanagement.dto.LikeDTO;
import com.store.management.storemanagement.dto.LoginDTO;
import com.store.management.storemanagement.dto.ProductDTO;
import com.store.management.storemanagement.dto.PurchaseDTO;
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
	
	// Adding a new product to database
	@Transactional
	@RequestMapping(value="/products/addProduct", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> saveProduct(HttpServletRequest request, @RequestBody ProductDTO productDTO) {	
		ResponseEntity<String> results = prs.saveProduct(request, productDTO);
		return results;
	}
	
	// Buying a product
	@Transactional
	@RequestMapping(value="/products/buyProduct", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> buyProduct(HttpServletRequest request, @RequestBody PurchaseDTO purchaseDTO) {
		ResponseEntity<String> results = prs.buyProduct(request, purchaseDTO);
		return results;
	}
	
	@Transactional
	@RequestMapping(value="/products/likeProduct", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> likeProduct(HttpServletRequest request, @RequestBody LikeDTO likeDTO) {
		ResponseEntity<String> results = prs.likeProduct(request, likeDTO);
		return results;
	}
	
	
	
}
