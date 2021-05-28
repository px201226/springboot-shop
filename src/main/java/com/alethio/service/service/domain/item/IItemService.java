package com.alethio.service.service.domain.item;

import com.alethio.service.service.domain.order.Order;

public interface IItemService {

    public Long increaseStockQuantity(Order.ItemIdentifier itemIdentifier, int quantity);

    public Long decreaseStockQuantity(Order.ItemIdentifier itemIdentifier, int quantity);

    public boolean isStockQuantityLessLowerbound(Order.ItemIdentifier itemIdentifier);
}
