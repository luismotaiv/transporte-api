package com.transport.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.transport.api.dto.AssignDriverRequestDTO;
import com.transport.api.dto.OrderRequestDTO;
import com.transport.api.dto.OrderResponseDTO;
import com.transport.api.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Crear una nueva orden")
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(dto));
    }
    
    @Operation(summary = "Asignar driver y subir documentos")
    @PatchMapping(
            value = "/{orderId}/assign-driver",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<OrderResponseDTO> assignDriver(
            @PathVariable UUID orderId,

            @RequestPart("driverId") String driverId,

            @RequestPart(value = "pdf", required = false) MultipartFile pdf,

            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        System.out.println("LLEGA a CONTROLLER");

        AssignDriverRequestDTO dto = new AssignDriverRequestDTO();
        dto.setDriverId(UUID.fromString(driverId));

        return ResponseEntity.ok(
                orderService.assignDriver(orderId, dto, pdf, image)
        );
    }
}
