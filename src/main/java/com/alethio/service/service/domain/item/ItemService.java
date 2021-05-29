package com.alethio.service.service.domain.item;

import com.alethio.service.service.domain.common.ItemStatusDVO;
import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.exception.NoSuchItemException;

public class ItemService implements IItemService {

    private AbstractItemRepositoryProvider itemRepositoryProvider;

    public ItemService(AbstractItemRepositoryProvider itemRepositoryProvider) {
        this.itemRepositoryProvider = itemRepositoryProvider;
    }

    @Override
    public Long increaseAvailableStock(ItemType itemType, Long itemId, int quantity) {

        IItemRepository itemRepository = itemRepositoryProvider.getRepositoryByItemType(itemType);
        ItemEntity itemEntity = itemRepository.findById(itemId).orElseThrow(NoSuchItemException::new);

        Long afterQuantity = itemEntity.increaseAvailableStock(quantity);

        return afterQuantity;
    }

    @Override
    public Long decreaseAvailableStock(ItemType itemType, Long itemId, int quantity) {

        IItemRepository itemRepository = itemRepositoryProvider.getRepositoryByItemType(itemType);
        ItemEntity itemEntity = itemRepository.findById(itemId).orElseThrow(NoSuchItemException::new);

        Long afterQuantity = itemEntity.decreaseAvailableStock(quantity);

        return afterQuantity;
    }

    @Override
    public boolean isAvailableStockLessThreshold(ItemType itemType, Long itemId) {
        IItemRepository itemRepository = itemRepositoryProvider.getRepositoryByItemType(itemType);
        ItemEntity itemEntity = itemRepository.findById(itemId).orElseThrow(NoSuchItemException::new);

        return itemEntity.isAvailableStockLessThreshold();
    }

    @Override
    public ItemStatusDVO getItemStatus(ItemType itemType, Long itemId) {
        IItemRepository itemRepository = itemRepositoryProvider.getRepositoryByItemType(itemType);
        ItemEntity itemEntity = itemRepository.findById(itemId).orElseThrow(NoSuchItemException::new);

        return ItemStatusDVO.of(itemEntity);
    }
}
