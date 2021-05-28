package com.alethio.service.service.domain.item;

import com.alethio.service.service.domain.exception.OutOfStockQuantityException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@NoArgsConstructor
@MappedSuperclass
public abstract class Item {

    public final static Long DEFAULT_STOCK_QUANTITY_REQUEST_THRESHOLD = 10L;

    public abstract ItemType getItemType();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected Long stockQuantity;

    protected Long stockRequestThreshold;

    protected String productName;


    protected Item(Long stockQuantity, Long stockRequestThreshold, String productName) {
        this.stockQuantity = stockQuantity;
        this.stockRequestThreshold = stockRequestThreshold;
        this.productName = productName;
    }

    public Long increaseStockQuantity(int quantity) {
        return stockQuantity += quantity;
    }

    public Long decreaseStockQuantity(int quantity) {
        if(stockQuantity - quantity < 0)
            throw new OutOfStockQuantityException();

        return stockQuantity -= quantity;
    }

    public boolean isStockQuantityLessLowerbound() {
        return stockQuantity < stockRequestThreshold;
    }

}
