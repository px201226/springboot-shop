package com.alethio.service.service.domain.stock;

import com.alethio.service.service.domain.common.ItemType;

public interface IStockService {

    public Long increaseStockQuantity(ItemType itemType, Long itemId, int quantity);

    public Long decreaseStockQuantity(ItemType itemType, Long itemId, int quantity);

}

