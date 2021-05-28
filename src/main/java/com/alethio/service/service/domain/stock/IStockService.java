package com.alethio.service.service.domain.stock;

import com.alethio.service.service.domain.item.ItemType;

public interface IStockService {

    public Long increaseStockQuantity(ItemType itemType, int quantity);

    public Long decreaseStockQuantity(ItemType itemType, int quantity);


}

