package com.alethio.service.service.domain.item;

import com.alethio.service.service.domain.common.ItemType;

public abstract class ItemRepositoryProvider {

    public abstract IItemRepository getRepositoryByItemType(ItemType itemType);

}
