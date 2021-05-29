package com.alethio.service.service.springboot.order;


import com.alethio.service.service.domain.order.IOrderService;
import com.alethio.service.service.domain.order.OrderEntity;
import com.alethio.service.service.springboot.item.ApplicationContextProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
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

        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        OrderEntity orderEntity = orderService.placeOrder(orderSaveRequestDto);
        OrderSaveResponseDto responseDto = OrderSaveResponseDto.of(orderEntity);

        return ResponseEntity.ok(responseDto);
    }
}
