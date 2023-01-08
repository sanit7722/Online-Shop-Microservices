package com.onelineshop.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onelineshop.product.entity.Product;

public interface ProductRepo extends JpaRepository<Product, String>{

}
