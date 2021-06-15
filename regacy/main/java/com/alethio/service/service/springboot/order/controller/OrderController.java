package com.alethio.service.service.springboot.order.controller;


import com.alethio.service.service.domain.order.IOrderService;
import com.alethio.service.service.domain.order.OrderEntity;
import com.alethio.service.service.domain.order.PlaceOrderRequestDTO;
import com.alethio.service.service.domain.order.PlaceOrderResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity placeOrder(@Valid @RequestBody PlaceOrderRequestDTO placeOrderRequestDto){

        OrderEntity orderEntity = orderService.placeOrder(placeOrderRequestDto);
        PlaceOrderResponseDTO responseDto = PlaceOrderResponseDTO.of(orderEntity);

        return ResponseEntity.ok(responseDto);
    }

}
