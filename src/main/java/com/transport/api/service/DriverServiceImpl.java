package com.transport.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.transport.api.dto.DriverRequestDTO;
import com.transport.api.dto.DriverResponseDTO;
import com.transport.api.model.Driver;
import com.transport.api.repository.DriverRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    @Override
    public DriverResponseDTO createDriver(DriverRequestDTO dto) {

        Driver driver = new Driver();
        driver.setName(dto.getName());
        driver.setLicenseNumber(dto.getLicenseNumber());
        driver.setActive(dto.isActive());

        Driver saved = driverRepository.save(driver);

        return mapToDTO(saved);
    }

    @Override
    public List<DriverResponseDTO> getActiveDrivers() {

        return driverRepository.findByActiveTrue()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private DriverResponseDTO mapToDTO(Driver driver) {
        DriverResponseDTO dto = new DriverResponseDTO();
        dto.setId(driver.getId());
        dto.setName(driver.getName());
        dto.setLicenseNumber(driver.getLicenseNumber());
        dto.setActive(driver.isActive());
        return dto;
    }
}