package com.store.management.storemanagement.util;

import java.util.Date;

import com.store.management.storemanagement.domain.Product;
import com.store.management.storemanagement.domain.Purchase;
import com.store.management.storemanagement.domain.StoreUser;
import com.store.management.storemanagement.dto.PurchaseDTO;

public class PurchaseUtil {
	
	public static Purchase createPurchase(PurchaseDTO purchaseDTO, StoreUser user, Product product) {
		Purchase purchase = new Purchase();
		purchase.setUser(user);
		purchase.setProduct(product);
		purchase.setAmount(purchaseDTO.getAmount());
		purchase.setDate(new Date());
		return purchase;
	}
}
