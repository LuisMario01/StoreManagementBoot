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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.store.management.storemanagement.domain.Like;
import com.store.management.storemanagement.domain.Product;
import com.store.management.storemanagement.domain.ProductLog;
import com.store.management.storemanagement.domain.Purchase;
import com.store.management.storemanagement.domain.StoreUser;
import com.store.management.storemanagement.dto.LikeDTO;
import com.store.management.storemanagement.dto.ProductDTO;
import com.store.management.storemanagement.dto.PurchaseDTO;
import com.store.management.storemanagement.repository.LikeRepository;
import com.store.management.storemanagement.repository.ProductLogRepository;
import com.store.management.storemanagement.repository.ProductRepository;
import com.store.management.storemanagement.repository.PurchaseRepository;
import com.store.management.storemanagement.util.MessageWrapper;
import com.store.management.storemanagement.util.ProductLogUtil;
import com.store.management.storemanagement.util.PurchaseUtil;

@Service
public class ProductRepositoryService {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private LikeRepository likeRepository;
	
	@Autowired
	private ProductLogRepository productLogRepository;
	
	// Loading data for database
	public boolean loadProductData() {
		boolean result;
		try {		
			productRepository.save(new Product("Milk", 1.5, 100));
			productRepository.save(new Product("Doritos", 0.75, 200));
			productRepository.save(new Product("Almonds", 2.0, 89));
			productRepository.save(new Product("Soda", 0.65, 99));
			productRepository.save(new Product("Nuts", 2.55, 67));
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
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			MessageWrapper mw = new MessageWrapper(true, "Failed when loading products");
			response = new ResponseEntity<String>(gson.toJson(mw), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	// Method to show existing list of products sorted ascendantly by name
	public ResponseEntity<String> findAllSorted(String page, String sort) {
		ResponseEntity<String> response;
		try {
			int pageNumber = Integer.parseInt(page);
			int sortType = Integer.parseInt(sort);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			if(sortType==0) {
				List<Product> products;
				products = productRepository.findAllByOrderByProductAsc(PageRequest.of(pageNumber-1,3));
				response = new ResponseEntity<String>(gson.toJson(products), HttpStatus.OK);
			}
			// Missing sort type = 1
			else {
				List<Object> products = productRepository.findAllSortedByLikes();
				response = new ResponseEntity<String>(gson.toJson(products), HttpStatus.OK);
			}
			
		}catch(Exception e){
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			MessageWrapper mw = new MessageWrapper(true, "Transaction not completed. Couldn't load data from parameters.");
			response = new ResponseEntity<String>(gson.toJson(mw), HttpStatus.BAD_REQUEST);
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
		    String json = gson.toJson(product);
		    return new ResponseEntity<>(json, HttpStatus.OK);
		}catch(Exception e) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			MessageWrapper mw = new MessageWrapper(true, "Transaction not completed. Couldn't load data from parameters.");
			return new ResponseEntity<>(gson.toJson(mw), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Transactional
	//Saving a product. Requires admin authorization.
	public ResponseEntity<String> saveProduct(HttpServletRequest request, ProductDTO productDTO, BindingResult res) {
		if(!res.hasErrors()) {
		try {
			byte[] valueDecoded = Base64.decodeBase64(request.getHeader("token"));
			Gson usrGson = new Gson();
			StoreUser user= usrGson.fromJson(new String(valueDecoded), StoreUser.class);
			// Role 0 for admin
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			if(user.getRole()==0) {
				Product savingProduct = new Product();
				savingProduct.setProduct(productDTO.getProduct());
				savingProduct.setPrice(productDTO.getPrice());
				savingProduct.setStock(productDTO.getStock());
				Product result = productRepository.save(savingProduct);
				return new ResponseEntity<>(gson.toJson(result), HttpStatus.OK); //Shows just-saved product*/
			}
			else {
				MessageWrapper mw = new MessageWrapper(true,"Not allowed");
				return new ResponseEntity<>(gson.toJson(mw), HttpStatus.UNAUTHORIZED);
			}		
		}
		catch(Exception e) {
			Gson gson = new Gson();
			MessageWrapper mw = new MessageWrapper(true,"Product was not saved");
			return new ResponseEntity<>(gson.toJson(mw), HttpStatus.BAD_REQUEST);
		}
		}
		else {
			Gson gson = new Gson();
			List<FieldError> errors = res.getFieldErrors();
			String response = "";
		    for (FieldError error : errors ) {
		        response += error.getDefaultMessage()+". ";
		    }
			MessageWrapper mw = new MessageWrapper(true,response);
			return new ResponseEntity<>(gson.toJson(mw), HttpStatus.BAD_REQUEST); 
		}
	}
	
	//Buying a product - Performed with a DTO object of the purchase.
	//Requires user or admin authorization
	@Transactional
	public ResponseEntity<String> buyProduct(HttpServletRequest request, PurchaseDTO purchaseDTO, BindingResult res) {
		if(!res.hasErrors()) {
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
								MessageWrapper mw = new MessageWrapper(false, "Done");
								return new ResponseEntity<String>(gson.toJson(mw), HttpStatus.OK);
							}
							else {
								MessageWrapper mw = new MessageWrapper(true, "Transaction not completed. Error when saving purchase");
								return new ResponseEntity<String>(gson.toJson(mw), HttpStatus.INTERNAL_SERVER_ERROR);
							}
						}else {
							MessageWrapper mw = new MessageWrapper(true, "Insufficient stock to perform purchase.");
							return new ResponseEntity<String>(gson.toJson(mw), HttpStatus.BAD_REQUEST);
						}
					}
					else {
						MessageWrapper mw = new MessageWrapper(true, "Product doesn't exist");
						return new ResponseEntity<String>(gson.toJson(mw), HttpStatus.NOT_FOUND);
					}
				}
				else {
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					MessageWrapper mw = new MessageWrapper(true, "Not allowed");
					return new ResponseEntity<>(gson.toJson(mw), HttpStatus.UNAUTHORIZED);
				}
			}
			catch(Exception e) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				MessageWrapper mw = new MessageWrapper(true, "Transaction not completed. Couldn't load data from parameters.");
				return new ResponseEntity<>(gson.toJson(mw), HttpStatus.BAD_REQUEST);
			}
		}
		else {
			List<FieldError> errors = res.getFieldErrors();
			String response = "";
		    for (FieldError error : errors ) {
		        response += error.getDefaultMessage()+". ";
		    }
		    Gson gson = new GsonBuilder().setPrettyPrinting().create();
			MessageWrapper mw = new MessageWrapper(true, response);
			return new ResponseEntity<>(gson.toJson(mw), HttpStatus.BAD_REQUEST); 
		}
			
	}
	
	// Method to like a product
	// Requires user or admin authorization
	@Transactional
	public ResponseEntity<String> likeProduct(HttpServletRequest request, LikeDTO likeDTO, BindingResult res) {
		if(!res.hasErrors()) {
			try {
				byte[] valueDecoded = Base64.decodeBase64(request.getHeader("token"));
				Gson usrGson = new Gson();
				StoreUser user= usrGson.fromJson(new String(valueDecoded), StoreUser.class);
				
				if(user.getRole()==0 || user.getRole()==1) {
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					Like newLike = new Like();
					Product product = productRepository.findByIdProduct(likeDTO.getIdProduct().longValue());
					newLike.setProduct(product);
					newLike.setUser(user);
					newLike = likeRepository.save(newLike);
					
					if(newLike!=null) {
						MessageWrapper mw = new MessageWrapper(false, "Done");
						return new ResponseEntity<String>(gson.toJson(mw), HttpStatus.OK);
					}
					else {
						MessageWrapper mw = new MessageWrapper(true, "Transaction failed. Couldn't create like");
						return new ResponseEntity<>(gson.toJson(mw), HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
				else {
					Gson gson = new Gson();
					MessageWrapper mw = new MessageWrapper(true, "Not allowed");
					return new ResponseEntity<>(gson.toJson(mw), HttpStatus.UNAUTHORIZED);
				}
			}
			catch(Exception e) {
				Gson gson = new Gson();
				MessageWrapper mw = new MessageWrapper(true, "Transaction not completed. Couldn't load data from parameters.");
				return new ResponseEntity<>(gson.toJson(mw), HttpStatus.BAD_REQUEST);
			}
		}
		else {
			List<FieldError> errors = res.getFieldErrors();
			String response = "";
		    for (FieldError error : errors ) {
		        response += error.getDefaultMessage()+". ";
		    }
		    Gson gson = new Gson();
			MessageWrapper mw = new MessageWrapper(true, response);
			return new ResponseEntity<>(gson.toJson(mw), HttpStatus.BAD_REQUEST); 
		}
	}
	
	// Method to update product price
	@Transactional
	public ResponseEntity<String> alterProductPrice(HttpServletRequest request, Product product, BindingResult res) {
		if(!res.hasErrors()) {
			try {
					byte[] valueDecoded = Base64.decodeBase64(request.getHeader("token"));
					Gson usrGson = new Gson();
					StoreUser user= usrGson.fromJson(new String(valueDecoded), StoreUser.class);
					if(user.getRole()==0) { //Only admins can update price
						Gson gson = new GsonBuilder().setPrettyPrinting().create();
						Product buyingProduct = new Product();
						buyingProduct = productRepository.findByIdProduct(product.getIdProduct());
						if(buyingProduct!=null){ // Product exists in database
							Double previousPrice = buyingProduct.getPrice();
							productRepository.save(product);
							ProductLog productLog = ProductLogUtil.createProductLog(buyingProduct, previousPrice);
							productLogRepository.save(productLog);
							MessageWrapper mw = new MessageWrapper(false, "Done");
							return new ResponseEntity<String>(gson.toJson(mw), HttpStatus.OK);
						}
						else {
							MessageWrapper mw = new MessageWrapper(true, "Product not saved. Couldn't create product");
							return new ResponseEntity<>(gson.toJson(mw), HttpStatus.INTERNAL_SERVER_ERROR);
						}
					}
					else{
						MessageWrapper mw = new MessageWrapper(true, "Not allowed");
						return new ResponseEntity<>(usrGson.toJson(mw), HttpStatus.UNAUTHORIZED);
					}
			}catch(Exception e) {
				Gson gson = new Gson();
				MessageWrapper mw = new MessageWrapper(true, "Not allowed");
				return new ResponseEntity<>(gson.toJson(mw), HttpStatus.BAD_REQUEST);
			}
		}
		else {
			List<FieldError> errors = res.getFieldErrors();
			String response = "";
		    for (FieldError error : errors ) {
		        response += error.getDefaultMessage()+". ";
		    }
		    Gson gson = new Gson();
			MessageWrapper mw = new MessageWrapper(true, response);
			return new ResponseEntity<>(gson.toJson(mw), HttpStatus.BAD_REQUEST); 
		}
	}
	
	// Method to delete a product.
	@Transactional
	public ResponseEntity<String> deleteProduct(HttpServletRequest request, String idProduct) {
		try {
			byte[] valueDecoded = Base64.decodeBase64(request.getHeader("token"));
			Gson usrGson = new Gson();
			StoreUser user= usrGson.fromJson(new String(valueDecoded), StoreUser.class);
			System.out.println(user.getRole());
			if(user.getRole()==0) { // Only admins can delete products
				Integer product = Integer.parseInt(idProduct);
				productRepository.deleteByIdProduct(product.longValue());	
				return new ResponseEntity<>("Done", HttpStatus.OK);
			}
			else {
				MessageWrapper mw = new MessageWrapper(false, "Not allowed");
				return new ResponseEntity<>(usrGson.toJson(mw), HttpStatus.UNAUTHORIZED);
			}
		}catch(Exception e) {
			Gson gson = new Gson();
			MessageWrapper mw = new MessageWrapper(false, "Couldn't delete product");
			return new ResponseEntity<>(gson.toJson(mw), HttpStatus.BAD_REQUEST);
		}
	}
	
}
