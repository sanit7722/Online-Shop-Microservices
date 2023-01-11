package com.onelineshop.inventory.service.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onelineshop.inventory.entity.Inventory;
import com.onelineshop.inventory.repo.InventoryRepo;
import com.onelineshop.inventory.service.InventoryService;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService{
	
	@Autowired
	private InventoryRepo inventoryRepo;

	@Override
	@Transactional(readOnly = true)
	public boolean isInStock(String skuCode) {
		Optional<Inventory> findBySkuCode = inventoryRepo.findBySkuCode(skuCode);
		return findBySkuCode.isPresent();
	}

}
