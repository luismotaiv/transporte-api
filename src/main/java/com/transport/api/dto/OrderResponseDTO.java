package com.transport.api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta de orden")
public class OrderResponseDTO {

	@Schema(description = "ID de la orden")
    private UUID id;
	
	@Schema(description = "Estado de la orden")
    private String status;
	
    private String origin;
    private String destination;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String pdfUrl;
    private String imageUrl;
    
    private DriverResponseDTO driver;
}