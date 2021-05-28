package com.alethio.service.service.domain.stock;

import com.alethio.service.service.domain.item.AbstractItemRepositoryProvider;
import com.alethio.service.service.domain.item.IItemRepository;
import com.alethio.service.service.domain.item.IItemService;
import com.alethio.service.service.domain.item.ItemType;
import com.alethio.service.service.domain.order.Order;

public class StockService implements IStockService {

    private IItemService itemService;

    public StockService(IItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public Long increaseStockQuantity(Order.ItemIdentifier itemIdentifier, int quantity) {
        Long afterQuantity = itemService.increaseStockQuantity(itemIdentifier, quantity);
        return afterQuantity;
    }

    @Override
    public Long decreaseStockQuantity(Order.ItemIdentifier itemIdentifier, int quantity) {
        Long afterQuantity = itemService.decreaseStockQuantity(itemIdentifier, quantity);
        return afterQuantity;
    }
}
