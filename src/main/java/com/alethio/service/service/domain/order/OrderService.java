package com.alethio.service.service.domain.order;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService implements IOrderService {

    private final IOrderRepository iOrderRepository;

    @Override
    public Order placeOrder(OrderSaveRequestDto orderSaveRequestDto) {
        return iOrderRepository.save(orderSaveRequestDto.toEntity());
    }
}
