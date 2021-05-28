package com.alethio.service.service.domain.item;

import com.alethio.service.service.domain.exception.NoSuchItemException;
import com.alethio.service.service.domain.order.Order;

public class ItemService implements IItemService {

    private AbstractItemRepositoryProvider itemRepositoryProvider;

    @Override
    public Long increaseStockQuantity(Order.ItemIdentifier itemIdentifier, int quantity) {

        IItemRepository itemRepository = itemRepositoryProvider.getRepositoryByItemType(itemIdentifier.getItemType());
        Item item = itemRepository.findById(itemIdentifier.getItemId()).orElseThrow(NoSuchItemException::new);

        Long afterQuantity = item.increaseStockQuantity(quantity);

        return afterQuantity;
    }

    @Override
    public Long decreaseStockQuantity(Order.ItemIdentifier itemIdentifier, int quantity) {

        IItemRepository itemRepository = itemRepositoryProvider.getRepositoryByItemType(itemIdentifier.getItemType());
        Item item = itemRepository.findById(itemIdentifier.getItemId()).orElseThrow(NoSuchItemException::new);

        Long afterQuantity = item.decreaseStockQuantity(quantity);

        return afterQuantity;
    }

    @Override
    public boolean isStockQuantityLessLowerbound(Order.ItemIdentifier itemIdentifier) {
        return false;
    }
}
