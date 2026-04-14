package com.transport.api.service;

import java.util.List;

import com.transport.api.dto.DriverRequestDTO;
import com.transport.api.dto.DriverResponseDTO;

public interface DriverService {

    DriverResponseDTO createDriver(DriverRequestDTO dto);

    List<DriverResponseDTO> getActiveDrivers();
}