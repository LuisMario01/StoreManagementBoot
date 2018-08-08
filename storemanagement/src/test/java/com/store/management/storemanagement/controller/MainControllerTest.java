package com.store.management.storemanagement.controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.store.management.storemanagement.StoremanagementApplication;
import com.store.management.storemanagement.domain.Like;
import com.store.management.storemanagement.domain.Product;
import com.store.management.storemanagement.domain.ProductLog;
import com.store.management.storemanagement.domain.Purchase;
import com.store.management.storemanagement.domain.StoreUser;
import com.store.management.storemanagement.util.AssertAnnotations;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, StoremanagementApplication.class})
@WebAppConfiguration
@AutoConfigureMockMvc
public class MainControllerTest {
	
	@Test
	  public void typeAnnotationsForProduct() {
	    // assert
	    AssertAnnotations.assertType(
	        Product.class, Entity.class, Table.class);
	  }
	
	@Test
	  public void typeAnnotationsForStoreUser() {
	    // assert
	    AssertAnnotations.assertType(
	        StoreUser.class, Entity.class, Table.class);
	  }
	
	@Test
	  public void typeAnnotationsForLike() {
	    // assert
	    AssertAnnotations.assertType(
	        Like.class, Entity.class, Table.class);
	  }
	
	@Test
	  public void typeAnnotationsForPurchase() {
	    // assert
	    AssertAnnotations.assertType(
	        Purchase.class, Entity.class, Table.class);
	  }
	
	@Test
	  public void typeAnnotationsForProductLog() {
	    // assert
	    AssertAnnotations.assertType(
	        ProductLog.class, Entity.class, Table.class);
	  }
	

	  @Test
	  public void methodAnnotationsForStoreUser() {
	    // assert
	    AssertAnnotations.assertMethod(
	        StoreUser.class, "getIdUser");
	    AssertAnnotations.assertMethod(
		        StoreUser.class, "getPassword");
	    AssertAnnotations.assertMethod(
		        StoreUser.class, "getUsername");
	    AssertAnnotations.assertMethod(
		        StoreUser.class, "getRole");
	  }
	  
	  @Test
	  public void methodAnnotationsForLike() {
	    // assert
	    AssertAnnotations.assertMethod(
	        Like.class, "getIdLike");
	    AssertAnnotations.assertMethod(
	    		Like.class, "getUser");
	    AssertAnnotations.assertMethod(
	    		Like.class, "getProduct");
	  }
	  
	  @Test
	  public void methodAnnotationsForPurchase() {
	    // assert
	    AssertAnnotations.assertMethod(
	        Purchase.class, "getIdPurchase");
	    AssertAnnotations.assertMethod(
	    		Purchase.class, "getUser");
	    AssertAnnotations.assertMethod(
	    		Purchase.class, "getProduct");
	    AssertAnnotations.assertMethod(
	    		Purchase.class, "getAmount");
	    AssertAnnotations.assertMethod(
	    		Purchase.class, "getDate");
	  }
	  
	  @Test
	  public void methodAnnotationsForProduct() {
	    // assert
	    AssertAnnotations.assertMethod(
	        Product.class, "getIdProduct");
	    AssertAnnotations.assertMethod(
	    		Product.class, "getProduct");
	    AssertAnnotations.assertMethod(
	    		Product.class, "getPrice");
	    AssertAnnotations.assertMethod(
	    		Product.class, "getStock");
	    AssertAnnotations.assertMethod(
	    		Product.class, "getPurchases");
	    AssertAnnotations.assertMethod(
	    		Product.class, "getLikes");
	    AssertAnnotations.assertMethod(
	    		Product.class, "getProductlog");
	  }
	  
	  @Test
	  public void methodAnnotationsForProductLog() {
	    // assert
	    AssertAnnotations.assertMethod(
	    		ProductLog.class, "getIdProductLog");
	    AssertAnnotations.assertMethod(
	    		ProductLog.class, "getPreviousPrice");
	    AssertAnnotations.assertMethod(
	    		ProductLog.class, "getCurrentPrice");
	    AssertAnnotations.assertMethod(
	    		ProductLog.class, "getProduct");
	  }

}
