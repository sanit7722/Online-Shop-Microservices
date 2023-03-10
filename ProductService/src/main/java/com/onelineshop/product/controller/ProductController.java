package com.onelineshop.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.onelineshop.product.dto.ProductReq;
import com.onelineshop.product.dto.ProductResponse;
import com.onelineshop.product.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductReq productReq) {
		
		productService.createProduct(productReq);
	}
	
	@GetMapping
	public ResponseEntity<List<ProductResponse>> getAllProducts(){
		
		List<ProductResponse> allProducts = productService.getAllProducts();
		return ResponseEntity.status(HttpStatus.OK).body(allProducts);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable String id){
		ProductResponse productById = productService.getProductById(id);
		return ResponseEntity.status(HttpStatus.OK).body(productById);
	}
	

}
