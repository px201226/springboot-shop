package com.alethio.service.service.domain.item;

import com.alethio.service.service.domain.order.Order;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class Item {

    private final static Long DEFAULT_STOCK_QUANTITY_LOWER_BOUND = 10L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long stockQuantity;

    private Long stockQuantityLowerBound;

    private String productName;

    public abstract ItemType getItemType();

    public Long increaseStockQuantity(int quantity) {
        return stockQuantity += quantity;
    }

    public Long decreaseStockQuantity(int quantity) {
        return stockQuantity -= quantity;
    }

    public boolean isStockQuantityLessLowerbound() {
        return stockQuantity < stockQuantityLowerBound;
    }

}
