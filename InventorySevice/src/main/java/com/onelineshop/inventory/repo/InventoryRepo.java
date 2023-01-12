package com.onelineshop.inventory.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onelineshop.inventory.entity.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory, Long>{

	

	List<Inventory> findBySkuCodeIn(List<String> skuCode);

}
