package com.onelineshop.order.service;

import com.onelineshop.order.dto.OrderRequest;

public interface OrderService {
	
	void placeOrder(OrderRequest orderRequest);

}
