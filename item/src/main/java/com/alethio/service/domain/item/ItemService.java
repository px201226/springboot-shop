package com.alethio.service.domain.item;


import com.alethio.service.common.ItemStatusDTO;
import com.alethio.service.common.ItemType;
import com.alethio.service.exception.business.NoSuchItemException;

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

        return itemEntity.convertToDto();
    }

    @Override
    public ItemStatusDTO removeAvailableStock(ItemType itemType, Long itemId, Long quantity) {

        IItemRepository<ItemEntity> itemRepository = itemRepositoryProvider.getRepositoryByItemType(itemType);
        ItemEntity itemEntity = itemRepository.findById(itemId).orElseThrow(NoSuchItemException::new);

        itemEntity.removeAvailableStock(quantity);

        return itemEntity.convertToDto();
    }

    @Override
    public ItemStatusDTO getItemStatus(ItemType itemType, Long itemId) {

        IItemRepository<ItemEntity> itemRepository = itemRepositoryProvider.getRepositoryByItemType(itemType);
        ItemEntity itemEntity = itemRepository.findById(itemId).orElseThrow(NoSuchItemException::new);

        return itemEntity.convertToDto();
    }
}
