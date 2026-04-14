package com.transport.api.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.UUID;

import com.transport.api.dto.AssignDriverRequestDTO;
import com.transport.api.dto.OrderRequestDTO;
import com.transport.api.exception.BadRequestException;
import com.transport.api.model.Driver;
import com.transport.api.model.Order;
import com.transport.api.model.OrderStatus;
import com.transport.api.repository.DriverRepository;
import com.transport.api.repository.OrderRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateOrderSuccessfully() {
        OrderRequestDTO dto = new OrderRequestDTO();
        dto.setOrigin("CDMX");
        dto.setDestination("Guadalajara");

        Order savedOrder = new Order();
        savedOrder.setId(UUID.randomUUID());
        savedOrder.setOrigin(dto.getOrigin());
        savedOrder.setDestination(dto.getDestination());

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        var result = orderService.createOrder(dto);

        assertNotNull(result);
        assertEquals("CDMX", result.getOrigin());
        verify(orderRepository, times(1)).save(any(Order.class));
    }
    
    @Mock
    private DriverRepository driverRepository;
    
    @Test
    void shouldFailIfDriverNotActive() {
        UUID orderId = UUID.randomUUID();
        UUID driverId = UUID.randomUUID();

        Order order = new Order();
        order.setStatus(OrderStatus.CREATED);

        Driver driver = new Driver();
        driver.setActive(false);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(driverRepository.findById(driverId)).thenReturn(Optional.of(driver));

        AssignDriverRequestDTO dto = new AssignDriverRequestDTO();
        dto.setDriverId(driverId);

        assertThrows(BadRequestException.class, () -> {
            orderService.assignDriver(orderId, dto, null, null);
        });
    }	
}