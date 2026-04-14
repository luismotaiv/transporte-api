package com.transport.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.transport.api.dto.AssignDriverRequestDTO;
import com.transport.api.dto.OrderFilterDTO;
import com.transport.api.dto.OrderRequestDTO;
import com.transport.api.dto.OrderResponseDTO;
import com.transport.api.exception.BadRequestException;
import com.transport.api.exception.ResourceNotFoundException;
import com.transport.api.mapper.OrderMapper;
import com.transport.api.model.Driver;
import com.transport.api.model.Order;
import com.transport.api.model.OrderStatus;
import com.transport.api.repository.DriverRepository;
import com.transport.api.repository.OrderRepository;
import com.transport.api.util.OrderStatusValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DriverRepository driverRepository;

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO dto) {

        Order order = new Order();
        order.setOrigin(dto.getOrigin());
        order.setDestination(dto.getDestination());
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        Order saved = orderRepository.save(order);

        return OrderMapper.toDTO(saved);
    }

    @Override
    public OrderResponseDTO getOrderById(UUID id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        return OrderMapper.toDTO(order);
    }

    @Override
    public List<OrderResponseDTO> getAllOrders(OrderFilterDTO filter) {

        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .map(OrderMapper::toDTO)
                .toList();
    }

    @Override
    public OrderResponseDTO updateOrderStatus(UUID id, OrderStatus newStatus) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (!OrderStatusValidator.isValidTransition(order.getStatus(), newStatus)) {
            throw new BadRequestException("Invalid status transition");
        }

        order.setStatus(newStatus);
        order.setUpdatedAt(LocalDateTime.now());

        Order updated = orderRepository.save(order);

        return OrderMapper.toDTO(updated);
    }

    private final FileStorageService fileStorageService;
    
    @Override
    public OrderResponseDTO assignDriver(UUID orderId, AssignDriverRequestDTO dto, MultipartFile pdf, MultipartFile image) {

    	 System.out.println("PDF FILE: " + (pdf != null ? pdf.getOriginalFilename() : "NULL"));
    	    System.out.println("IMAGE FILE: " + (image != null ? image.getOriginalFilename() : "NULL"));
    	
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        Driver driver = driverRepository.findById(dto.getDriverId())
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found"));

        if (order.getStatus() != OrderStatus.CREATED) {
            throw new BadRequestException("Order must be in CREATED status");
        }

        if (!driver.isActive()) {
            throw new BadRequestException("Driver is not active");
        }

        //GUARDAR ARCHIVOS
        String pdfPath = fileStorageService.saveFile(pdf);
        String imagePath = fileStorageService.saveFile(image);

        order.setDriver(driver);
        order.setPdfUrl(pdfPath);
        order.setImageUrl(imagePath);
        order.setUpdatedAt(LocalDateTime.now());

        Order updated = orderRepository.save(order);

        return OrderMapper.toDTO(updated);
    }
}