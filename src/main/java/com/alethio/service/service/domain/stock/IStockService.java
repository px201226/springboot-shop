package com.alethio.service.service.domain.stock;

import com.alethio.service.service.domain.item.ItemType;
import com.alethio.service.service.domain.order.Order;

public interface IStockService {

    public Long increaseStockQuantity(Order.ItemIdentifier itemIdentifier, int quantity);

    public Long decreaseStockQuantity(Order.ItemIdentifier itemIdentifier, int quantity);


}

