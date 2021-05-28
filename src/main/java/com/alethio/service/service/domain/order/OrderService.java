package com.alethio.service.service.domain.order;


import com.alethio.service.service.domain.item.AbstractItemRepositoryProvider;
import com.alethio.service.service.domain.item.IItemRepository;
import com.alethio.service.service.domain.item.ItemType;
import com.alethio.service.service.domain.stock.IStockService;

public class OrderService implements IOrderService {

    private IOrderRepository orderRepository;
    private IStockService stockService;

    public OrderService(IOrderRepository orderRepository, IStockService stockService) {
        this.orderRepository = orderRepository;
        this.stockService = stockService;
    }

    @Override
    public Order placeOrder(OrderSaveRequestDto orderSaveRequestDto) {

        Order.ItemIdentifier orderItemIdentifier = orderSaveRequestDto.getItemIdentifierRequestDto().toEntity();

        stockService.decreaseStockQuantity(orderItemIdentifier,1);

        return orderRepository.save(orderSaveRequestDto.toEntity());
    }
}
