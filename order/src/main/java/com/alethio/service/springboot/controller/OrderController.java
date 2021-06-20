package com.alethio.service.springboot.controller;


import com.alethio.service.domain.order.IOrderService;
import com.alethio.service.domain.order.OrderEntity;
import com.alethio.service.domain.order.PlaceOrderRequestDTO;
import com.alethio.service.domain.order.PlaceOrderResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/com/alethio/service")
public class OrderController {

    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity placeOrder(@Valid @RequestBody PlaceOrderRequestDTO placeOrderRequestDto){

        OrderEntity orderEntity = orderService.placeOrder(placeOrderRequestDto);
        PlaceOrderResponseDTO responseDto = PlaceOrderResponseDTO.of(orderEntity);

        return ResponseEntity.ok(responseDto);
    }

}
