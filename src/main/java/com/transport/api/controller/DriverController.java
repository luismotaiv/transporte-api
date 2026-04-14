package com.transport.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transport.api.dto.DriverRequestDTO;
import com.transport.api.dto.DriverResponseDTO;
import com.transport.api.service.DriverService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping
    public ResponseEntity<DriverResponseDTO> createDriver(@Valid @RequestBody DriverRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(driverService.createDriver(dto));
    }

    @GetMapping("/active")
    public ResponseEntity<List<DriverResponseDTO>> getActiveDrivers() {
        return ResponseEntity.ok(driverService.getActiveDrivers());
    }
}