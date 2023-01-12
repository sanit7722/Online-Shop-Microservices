package com.onelineshop.inventory.service;

import java.util.List;

import com.onelineshop.inventory.dto.InventoryResponse;

public interface InventoryService {
	List<InventoryResponse> isInStock(List<String> skuCode);

}
