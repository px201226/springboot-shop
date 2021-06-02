package com.alethio.service.service.domain.item;

import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.exception.business.OutOfStockQuantityException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;


@Getter
@NoArgsConstructor
@MappedSuperclass
public abstract class ItemEntity {

    public final static Long DEFAULT_REQUEST_STOCK_THRESHOLD = 10L;   // 자동으로 입고 요청되는 재고의 임계값의 기본값
    public final static Long DEFAULT_REQUEST_STOCK_QUANTITY = 100L;            // 입고 요청을 할 때 입고 요청 수량의 기본값

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Vendor vendor;

    private Long availableStockQuantity;

    private Long stockThreshold;

    private Long requestStockQuantity;

    @Column(name = "item_name")
    private String name;

    public abstract ItemType getItemType();

    public ItemEntity(Vendor vendor, Long availableStockQuantity, Long stockThreshold, Long requestStockQuantity, String name) {
        this.vendor = vendor;
        this.availableStockQuantity = availableStockQuantity;
        this.stockThreshold = stockThreshold;
        this.requestStockQuantity = requestStockQuantity;
        this.name = name;
    }

    public Long addAvailableStock(Long quantity) {
        return availableStockQuantity += quantity;
    }

    public Long removeAvailableStock(Long quantity) throws OutOfStockQuantityException {
        if(availableStockQuantity - quantity < 0)
            throw new OutOfStockQuantityException(toStringPk());

        return availableStockQuantity -= quantity;
    }

    public boolean isExceedStockThreshold() {
        return availableStockQuantity < stockThreshold;
    }

    public String toStringPk(){
        return String.format("itemType: %s, itemId: %d", getItemType().getName(), getId());
    }
}
