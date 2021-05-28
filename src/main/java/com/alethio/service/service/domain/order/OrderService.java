package com.alethio.service.service.domain.order;


import com.alethio.service.service.domain.item.AbstractItemRepositoryProvider;
import com.alethio.service.service.domain.item.IItemRepository;
import com.alethio.service.service.domain.item.ItemType;

public class OrderService implements IOrderService {

    private IOrderRepository iOrderRepository;
    private AbstractItemRepositoryProvider itemRepositoryProvider;

    public OrderService(IOrderRepository iOrderRepository, AbstractItemRepositoryProvider itemRepositoryProvider){
        this.iOrderRepository = iOrderRepository;
        this.itemRepositoryProvider = itemRepositoryProvider;
    }

    @Override
    public Order placeOrder(OrderSaveRequestDto orderSaveRequestDto) {

        Long orderItemId = orderSaveRequestDto.getItemIdentifierRequestDto().getItemId();
        ItemType orderItemType = orderSaveRequestDto.getItemIdentifierRequestDto().getItemType();
        IItemRepository iItemRepository = itemRepositoryProvider.getRepositoryByItemType(orderItemType);

        iItemRepository.findById(orderItemId).orElseThrow(IllegalAccessError::new);

        return iOrderRepository.save(orderSaveRequestDto.toEntity());
    }
}
