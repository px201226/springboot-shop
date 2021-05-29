package com.alethio.service.service.domain.item;

import com.alethio.service.service.domain.common.ItemType;

public abstract class AbstractItemRepositoryProvider {

    public abstract IItemRepository getRepositoryByItemType(ItemType itemType);

}
