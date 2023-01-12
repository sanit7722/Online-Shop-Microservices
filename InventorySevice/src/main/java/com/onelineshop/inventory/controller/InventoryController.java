package com.onelineshop.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onelineshop.inventory.dto.InventoryResponse;
import com.onelineshop.inventory.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	@GetMapping("/{skuCode}")
	public List<InventoryResponse> inStock(@RequestParam List<String> skuCode) {
		return inventoryService.isInStock(skuCode);
	}

}
