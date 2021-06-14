package order.springboot.controller;


import order.domain.order.IOrderService;
import order.domain.order.OrderEntity;
import order.domain.order.PlaceOrderRequestDTO;
import order.domain.order.PlaceOrderResponseDTO;
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
    public ResponseEntity placeOrder(@Valid @RequestBody PlaceOrderRequestDTO placeOrderRequestDto){

        OrderEntity orderEntity = orderService.placeOrder(placeOrderRequestDto);
        PlaceOrderResponseDTO responseDto = PlaceOrderResponseDTO.of(orderEntity);

        return ResponseEntity.ok(responseDto);
    }

}
