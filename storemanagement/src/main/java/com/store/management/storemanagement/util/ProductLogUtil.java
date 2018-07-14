package com.store.management.storemanagement.util;

import com.store.management.storemanagement.domain.Product;
import com.store.management.storemanagement.domain.ProductLog;

public class ProductLogUtil {
	
	public static ProductLog createProductLog(Product product, Double previousPrice) {
		ProductLog newLog = new ProductLog();
		newLog.setProduct(product);
		newLog.setPreviousPrice(previousPrice);
		newLog.setCurrentPrice(product.getPrice());
		return newLog;
	}
}
