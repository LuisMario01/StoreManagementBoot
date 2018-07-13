package com.store.management.storemanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.store.management.storemanagement.service.ProductRepositoryService;

@SpringBootApplication
public class StoremanagementApplication {

	@Autowired
	public ProductRepositoryService productRepositoryService;
	
	public static void main(String[] args) {
		SpringApplication.run(StoremanagementApplication.class, args);
	}
	
	@Component
	public class CommandLineAppStartupRunner implements CommandLineRunner {
	    @Override
	    public void run(String...args) throws Exception {
	        productRepositoryService.createDataIntoBase();
	    }
	}
}
