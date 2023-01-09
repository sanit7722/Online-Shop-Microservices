package com.onelineshop.product.service;

import java.util.List;

import com.onelineshop.product.dto.ProductReq;
import com.onelineshop.product.dto.ProductResponse;

public interface ProductService {
	
	void createProduct(ProductReq productReq);
	
	List<ProductResponse> getAllProducts();
	
	ProductResponse getProductById(String id);
	

}
