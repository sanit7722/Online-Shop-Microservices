package com.onelineshop.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onelineshop.order.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long>{

}
