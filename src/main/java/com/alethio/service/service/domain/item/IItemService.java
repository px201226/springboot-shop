package com.alethio.service.service.domain.item;

import com.alethio.service.service.domain.common.ItemStatusDVO;
import com.alethio.service.service.domain.common.ItemType;

public interface IItemService {

    public Long increaseAvailableStock(ItemType itemType, Long itemId, int quantity);

    public Long decreaseAvailableStock(ItemType itemType, Long itemId, int quantity);

    public boolean isAvailableStockLessThreshold(ItemType itemType, Long itemId);

    public ItemStatusDVO getItemStatus(ItemType itemType, Long itemId);
}
