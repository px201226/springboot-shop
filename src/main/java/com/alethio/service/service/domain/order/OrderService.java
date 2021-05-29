package com.alethio.service.service.domain.order;


import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.stock.IStockService;
import com.alethio.service.service.springboot.controller.dto.OrderSaveRequestDto;

public class OrderService implements IOrderService {

    private IOrderRepository orderRepository;
    private IStockService stockService;

    public OrderService(IOrderRepository orderRepository, IStockService stockService) {
        this.orderRepository = orderRepository;
        this.stockService = stockService;
    }

    @Override
    public OrderEntity placeOrder(OrderSaveRequestDto orderSaveRequestDto) {

        ItemType orderItemIdentifier = orderSaveRequestDto.getItemType();
        Long orderItemId = orderSaveRequestDto.getItemId();

        stockService.decreaseStockQuantity(orderItemIdentifier,orderItemId,1);

        return orderRepository.save(orderSaveRequestDto.toEntity());
    }
}
