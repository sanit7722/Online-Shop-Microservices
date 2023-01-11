package com.onelineshop.order.service.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onelineshop.order.dto.OrderLineItemsDto;
import com.onelineshop.order.dto.OrderRequest;
import com.onelineshop.order.entity.Order;
import com.onelineshop.order.entity.OrderLineItem;
import com.onelineshop.order.repo.OrderRepo;
import com.onelineshop.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepo orderRepo;

	@Override
	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItem> list = orderRequest.getOrderLineItemsDtos().stream().map(this::mapToDto).toList();
		order.setOrderLineItems(list);
		orderRepo.save(order);

	}
	private OrderLineItem mapToDto(OrderLineItemsDto orderLineItemsDto) {
		
		OrderLineItem orderLineItem=new OrderLineItem();
		orderLineItem.setPrice(orderLineItemsDto.getPrice());
		orderLineItem.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItem.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItem;
	}

}
