package com.alethio.service.service.domain.item;

public abstract class AbstractItemRepositoryProvider {

    public abstract IItemRepository getRepositoryByItemType(ItemType itemType);

}
