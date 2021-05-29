package com.alethio.service.service.domain.order;

import com.alethio.service.service.springboot.controller.dto.OrderSaveRequestDto;

public interface IOrderService {

    public OrderEntity placeOrder(OrderSaveRequestDto orderSaveRequestDto);
}
