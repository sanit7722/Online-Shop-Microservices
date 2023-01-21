package com.onelineshop.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.onelineshop.order.dto.OrderRequest;
import com.onelineshop.order.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMenthod")
	public String placeOrder(@RequestBody OrderRequest orderRequest) {
		orderService.placeOrder(orderRequest);
		return "Order Placed Successfully";
		
	}
	
	public String fallbackMenthod(OrderRequest orderRequest, RuntimeException exception) {
		return "Oops! Something went wrong, please try after sometime";
	}

	
}
