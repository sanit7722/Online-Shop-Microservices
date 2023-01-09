package com.onelineshop.product.service.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onelineshop.product.dto.ProductReq;
import com.onelineshop.product.dto.ProductResponse;
import com.onelineshop.product.entity.Product;
import com.onelineshop.product.repo.ProductRepo;
import com.onelineshop.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo productRepo;

	@Override
	public void createProduct(ProductReq productReq) {
		Product product=Product.builder()
				.id(UUID.randomUUID().toString())
				.name(productReq.getName())
				.price(productReq.getPrice())
				.description(productReq.getDescription())
				.build();
		productRepo.save(product);
		log.info("Product {} is saved ",product.getId());
		
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		List<Product> products = productRepo.findAll();
		
		List<ProductResponse> list = products.stream().map(product->{
			return ProductResponse.builder()
			.id(product.getId())
			.name(product.getName())
			.description(product.getDescription())
			.price(product.getPrice())
			.build();
			
		}).toList();
		return list;
	}

	@Override
	public ProductResponse getProductById(String id) {
		Product product = productRepo.findById(id).orElseThrow();
		
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}
	
	

}
