package com.alethio.service.service.domain.order;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity placeOrder(@Valid @RequestBody OrderSaveRequestDto orderSaveRequestDto){

        System.out.println("ddd");
        System.out.println(orderSaveRequestDto);
        Order order = orderService.placeOrder(orderSaveRequestDto);
        OrderSaveResponseDto responseDto = OrderSaveResponseDto.of(order);

        return ResponseEntity.ok(responseDto);
    }
}
