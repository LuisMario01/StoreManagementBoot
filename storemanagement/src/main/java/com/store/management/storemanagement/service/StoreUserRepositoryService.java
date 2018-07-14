package com.store.management.storemanagement.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.store.management.storemanagement.domain.StoreUser;
import com.store.management.storemanagement.dto.LoginDTO;
import com.store.management.storemanagement.repository.StoreUserRepository;

@Service
public class StoreUserRepositoryService {
	
	@Autowired
	public StoreUserRepository sur;
	
	// Creating data that will be stored when application starts
	public boolean loadStoreUserData() {
		try {
			
			// role 0: admins, 1: users
			sur.save(new StoreUser("admin", "pass", 0));
			sur.save(new StoreUser("user", "pass", 1));
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public ResponseEntity<String> login(@RequestBody LoginDTO login) {
		System.out.println(login.getUsername()+login.getPassword());
		try {
			StoreUser user = sur.findByUsername(login.getUsername());
			if(user!=null) {
				if(user.getUsername().equals(login.getUsername()) && user.getPassword().equals(login.getPassword())) {
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					String json = gson.toJson(user);
					byte[] bytesEncoded = Base64.encodeBase64(json.getBytes());
					return new ResponseEntity<String>(new String(bytesEncoded), HttpStatus.OK);
				}
				else return new ResponseEntity<String>("Incorrect credentials", HttpStatus.NOT_FOUND);
			}
			else {
				return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return new ResponseEntity<String>("Couldn't complete request", HttpStatus.BAD_REQUEST);
		}
	}

}
