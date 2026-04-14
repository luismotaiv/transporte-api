package com.transport.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transport.api.model.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {
	
}
