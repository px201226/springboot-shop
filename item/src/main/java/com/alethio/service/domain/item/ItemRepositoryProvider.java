package com.alethio.service.domain.item;


import com.alethio.service.common.ItemType;

public abstract class ItemRepositoryProvider {

    /**
     * ItemType에 해당하는 Repository를 반환한다.
     * @param itemType 조회할 ItemType
     * @return
     */
    public abstract IItemRepository getRepositoryByItemType(ItemType itemType);

}
