package com.store.management.storemanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.store.management.storemanagement.domain.StoreUser;

import com.store.management.storemanagement.repository.StoreUserRepository;

@Service
public class StoreUserRepositoryService {
	
	@Autowired
	public StoreUserRepository surs;
	
	// Creating data that will be stored when application starts
	public boolean loadStoreUserData() {
		try {
			
			// role 0: admins, 1: users
			surs.save(new StoreUser("admin", "pass", 0));
			surs.save(new StoreUser("user", "pass", 1));
			return true;
		}catch(Exception e) {
			return false;
		}
	}

}
