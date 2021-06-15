package com.alethio.service.service.domain.item;

import com.alethio.service.service.domain.common.ItemStatusDTO;
import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.exception.business.NoSuchItemException;

import java.util.Optional;

public class ItemService implements IItemService {

    private ItemRepositoryProvider itemRepositoryProvider;

    public ItemService(ItemRepositoryProvider itemRepositoryProvider) {
        this.itemRepositoryProvider = itemRepositoryProvider;
    }

    @Override
    public ItemStatusDTO addAvailableStock(ItemType itemType, Long itemId, Long quantity) {

        IItemRepository<ItemEntity> itemRepository = itemRepositoryProvider.getRepositoryByItemType(itemType);
        ItemEntity itemEntity = itemRepository.findById(itemId).orElseThrow(NoSuchItemException::new);

        itemEntity.addAvailableStock(quantity);

        return ItemStatusDTO.of(itemEntity);
    }

    @Override
    public ItemStatusDTO removeAvailableStock(ItemType itemType, Long itemId, Long quantity) {

        IItemRepository<ItemEntity> itemRepository = itemRepositoryProvider.getRepositoryByItemType(itemType);
        ItemEntity itemEntity = itemRepository.findById(itemId).orElseThrow(NoSuchItemException::new);

        itemEntity.removeAvailableStock(quantity);

        return ItemStatusDTO.of(itemEntity);
    }

    @Override
    public ItemStatusDTO getItemStatus(ItemType itemType, Long itemId) {

        IItemRepository<ItemEntity> itemRepository = itemRepositoryProvider.getRepositoryByItemType(itemType);
        ItemEntity itemEntity = itemRepository.findById(itemId).orElseThrow(NoSuchItemException::new);

        return ItemStatusDTO.of(itemEntity);
    }
}
