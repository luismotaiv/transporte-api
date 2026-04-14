package com.transport.api.mapper;

import com.transport.api.dto.DriverResponseDTO;
import com.transport.api.dto.OrderResponseDTO;
import com.transport.api.model.Order;

public class OrderMapper {

    public static OrderResponseDTO toDTO(Order order) {

        OrderResponseDTO dto = new OrderResponseDTO();

        dto.setId(order.getId());

        dto.setStatus(order.getStatus().name());
        dto.setOrigin(order.getOrigin());
        dto.setDestination(order.getDestination());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setUpdatedAt(order.getUpdatedAt());
        
        dto.setPdfUrl(order.getPdfUrl());
        dto.setImageUrl(order.getImageUrl());

        //MAPEAR DRIVER (si existe)
        if (order.getDriver() != null) {

            DriverResponseDTO driverDTO = new DriverResponseDTO();
            driverDTO.setId(order.getDriver().getId());
            driverDTO.setName(order.getDriver().getName());
            driverDTO.setLicenseNumber(order.getDriver().getLicenseNumber());
            driverDTO.setActive(order.getDriver().isActive());

            dto.setDriver(driverDTO);
        }

        return dto;
    }
}