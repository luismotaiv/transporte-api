package com.transport.api.dto;

import com.transport.api.model.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderFilterDTO {

    private OrderStatus status;
    private String origin;
    private String destination;
}