package com.onelineshop.order.service.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.onelineshop.order.dto.InventoryResponse;
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

	@Autowired
	private WebClient webClient;

	@Override
	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItem> list = orderRequest.getOrderLineItemsDtos().stream().map(this::mapToDto).toList();
		order.setOrderLineItems(list);
		
		
		List<String> skuCodes = order.getOrderLineItems().stream().map(orderLineItem->orderLineItem.getSkuCode()).toList();

		 InventoryResponse[] inventoryResponseArray = webClient.get()
				.uri("http://localhost:8083/api/inventory",ueiBuilder-> ueiBuilder.queryParam("skuCode", skuCodes).build())
				.retrieve()
				.bodyToMono(InventoryResponse[].class)
				.block();
		 Boolean allProductsIsInStock = Arrays.stream(inventoryResponseArray).allMatch(inventoryResponse->inventoryResponse.isInStock());
		if (allProductsIsInStock)
			orderRepo.save(order);
		else
			throw new IllegalArgumentException("Product is not in stock, try again later");

	}

	private OrderLineItem mapToDto(OrderLineItemsDto orderLineItemsDto) {

		OrderLineItem orderLineItem = new OrderLineItem();
		orderLineItem.setPrice(orderLineItemsDto.getPrice());
		orderLineItem.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItem.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItem;
	}

}
