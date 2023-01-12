package com.onelineshop.inventory.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onelineshop.inventory.dto.InventoryResponse;
import com.onelineshop.inventory.entity.Inventory;
import com.onelineshop.inventory.repo.InventoryRepo;
import com.onelineshop.inventory.service.InventoryService;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepo inventoryRepo;

	@Override
	@Transactional(readOnly = true)
	public List<InventoryResponse> isInStock(List<String> skuCode) {
		return inventoryRepo.findBySkuCodeIn(skuCode).stream()
				.map(inventory->
					InventoryResponse.builder()
					.skuCode(inventory.getSkuCode())
					.inStock(inventory.getQuantity()>0)
					.build()
				
				).toList();
		
	}

}
