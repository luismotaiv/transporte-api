package com.transport.api.util;

import com.transport.api.model.OrderStatus;

public class OrderStatusValidator {

    public static boolean isValidTransition(OrderStatus current, OrderStatus next) {
        return switch (current) {
            case CREATED -> next == OrderStatus.IN_TRANSIT || next == OrderStatus.CANCELLED;
            case IN_TRANSIT -> next == OrderStatus.DELIVERED;
            default -> false;
        };
    }
}