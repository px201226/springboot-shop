package com.alethio.service.service.domain.stock;

import com.alethio.service.service.domain.item.AbstractItemRepositoryProvider;
import com.alethio.service.service.domain.item.IItemRepository;
import com.alethio.service.service.domain.item.ItemType;

public class StockService implements IStockService {

    private AbstractItemRepositoryProvider itemRepositoryProvider;

    @Override
    public Long increaseStockQuantity(ItemType itemType, int quantity) {

        IItemRepository itemRepository = itemRepositoryProvider.getRepositoryByItemType(itemType);
        

        return null;
    }

    @Override
    public Long decreaseStockQuantity(ItemType itemType, int quantity) {
        return null;
    }
}
