package com.transport.api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.transport.api.dto.AssignDriverRequestDTO;
import com.transport.api.dto.OrderFilterDTO;
import com.transport.api.dto.OrderRequestDTO;
import com.transport.api.dto.OrderResponseDTO;
import com.transport.api.model.OrderStatus;

public interface OrderService {

    OrderResponseDTO createOrder(OrderRequestDTO dto);

    OrderResponseDTO getOrderById(UUID id);

    List<OrderResponseDTO> getAllOrders(OrderFilterDTO filter);

    OrderResponseDTO updateOrderStatus(UUID id, OrderStatus status);

    OrderResponseDTO assignDriver(UUID orderId, AssignDriverRequestDTO dto, MultipartFile pdf, MultipartFile image);
}
