package com.store.management.storemanagement.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.store.management.storemanagement.domain.Product;
import com.store.management.storemanagement.domain.Purchase;
import com.store.management.storemanagement.domain.StoreUser;
import com.store.management.storemanagement.dto.ProductDTO;
import com.store.management.storemanagement.dto.PurchaseDTO;
import com.store.management.storemanagement.repository.ProductRepository;
import com.store.management.storemanagement.repository.PurchaseRepository;
import com.store.management.storemanagement.util.PurchaseUtil;

@Service
public class ProductRepositoryService {
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	PurchaseRepository purchaseRepository;
	
	// Loading data for database
	public boolean loadProductData() {
		boolean result;
		try {		
			productRepository.save(new Product("Milk", 1.5, 100));
			productRepository.save(new Product("Doritos", 0.75, 200));
			productRepository.save(new Product("Almonds", 2.0, 89));
			result = true;
		}catch(Exception e) {
			result = false;
		}
		return result;
	}
	
	// Find all products order ascendantly
	public ResponseEntity<String> findAllAsc() {
		ResponseEntity<String> response;
		List<Product> products;
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			products = productRepository.findAll();
			String json = gson.toJson(products);
			response = new ResponseEntity<String>(json, HttpStatus.OK);
		}catch(Exception e){
			response = new ResponseEntity<String>("Failed when loading products", HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	// Method to show existing list of products sorted ascendantly by name
	public ResponseEntity<String> findAllSorted(String page, String sort) {
		ResponseEntity<String> response;
		List<Product> products;
		try {
			int pageNumber = Integer.parseInt(page);
			int sortType = Integer.parseInt(sort);
			if(sortType==0) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				products = productRepository.findAllByOrderByProductAsc(PageRequest.of(pageNumber-1,3));
				String json = gson.toJson(products);
				response = new ResponseEntity<String>(json, HttpStatus.OK);
			}
			// Missing sort type = 1
			else {
				response = new ResponseEntity<String>("Sort 1", HttpStatus.OK);
			}
			
		}catch(Exception e){
			response = new ResponseEntity<String>("Failed", HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	// Saving one product (only for data load)
	public ResponseEntity<String> saveOneProduct(HttpServletRequest request, Product product){
		ResponseEntity<String> response;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response = new ResponseEntity<String>(gson.toString(), HttpStatus.OK);
		return response;
	}
	
	// Showing a product by its name
	public ResponseEntity<String> showProductByName(String productParam) {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Product product = productRepository.findByProduct(productParam);
			System.out.println("Value of product"+product.getProduct());
		    String json = gson.toJson(product);
		    return new ResponseEntity<>(json, HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
		}
	}
	
	@Transactional
	//Saving a product. Requires admin authorization.
	public ResponseEntity<String> saveProduct(HttpServletRequest request, ProductDTO productDTO) {	
		try {
			byte[] valueDecoded = Base64.decodeBase64(request.getHeader("token"));
			Gson usrGson = new Gson();
			System.out.println(usrGson.toString());
			StoreUser user= usrGson.fromJson(new String(valueDecoded), StoreUser.class);
			// Role 0 for admin
			if(user.getRole()==0) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				Product savingProduct = new Product();
				savingProduct.setProduct(productDTO.getProduct());
				savingProduct.setPrice(productDTO.getPrice());
				savingProduct.setStock(productDTO.getStock());
				Product result = new Product();
				result = productRepository.save(savingProduct);
				return new ResponseEntity<>(gson.toJson(result), HttpStatus.OK); //Shows just-saved product*/
			}
			else {
				return new ResponseEntity<>("Not allowed", HttpStatus.UNAUTHORIZED);
			}		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Product not saved", HttpStatus.BAD_REQUEST);
		}
	}
	
	//Buying a product - Performed with a DTO object of the purchase.
	//Requires user authorization
	public ResponseEntity<String> buyProduct(HttpServletRequest request, PurchaseDTO purchaseDTO) {
		try {
			byte[] valueDecoded = Base64.decodeBase64(request.getHeader("token"));
			Gson usrGson = new Gson();
			StoreUser user= usrGson.fromJson(new String(valueDecoded), StoreUser.class);
			System.out.println(user.getUsername()+user.getPassword());
			
			if(user.getRole()==0 || user.getRole()==1) { // admins can also buy.
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				Product buyingProduct = new Product();
				Purchase newPurchase = new Purchase();
				buyingProduct = productRepository.findByIdProduct(purchaseDTO.getIdProduct().longValue());
				if(buyingProduct!=null) {
					int newStock = buyingProduct.getStock()-purchaseDTO.getAmount(); //Decreasing stock w/purchase
					if(newStock>=0) {
						buyingProduct.setStock(newStock); //Setting new stock
						buyingProduct = productRepository.save(buyingProduct);
						newPurchase = purchaseRepository.save(PurchaseUtil.createPurchase(purchaseDTO, user, buyingProduct));
						if(newPurchase!=null && buyingProduct != null) {
							return new ResponseEntity<String>("Done", HttpStatus.OK);
						}
						else
							return new ResponseEntity<String>("Transaction not completed", HttpStatus.NOT_MODIFIED);
					}else {
						return new ResponseEntity<String>("Insufficient stock", HttpStatus.NOT_ACCEPTABLE);
					}
				}
				else return new ResponseEntity<String>("Product doesn't exist", HttpStatus.NO_CONTENT);
			}
			else {
				return new ResponseEntity<>("Not allowed", HttpStatus.UNAUTHORIZED);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Transaction not completed", HttpStatus.BAD_REQUEST);
		}
			
	}
	
}
