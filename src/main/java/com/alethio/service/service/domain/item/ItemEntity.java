package com.alethio.service.service.domain.item;

import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.exception.business.OutOfStockQuantityException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Positive;


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

    private Long requestStockThreshold;

    private Long requestStockQuantity;

    @Column(name = "item_name")
    private String name;

    public abstract ItemType getItemType();

    public ItemEntity(Vendor vendor, Long availableStockQuantity, Long requestStockThreshold, Long requestStockQuantity, String name) {

        assert vendor != null :                         "vendor must be not null";
        assert availableStockQuantity >= 0 :            "availableStockQuantity must be more then 2";
        assert requestStockQuantity > 0 :               "requestStockQuantity must be positive";
        assert name != null :                           "name must be not null";

        this.vendor = vendor;
        this.availableStockQuantity = availableStockQuantity;
        this.requestStockThreshold = requestStockThreshold;
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

    public boolean isAvailableStockLessThreshold() {
        return availableStockQuantity < requestStockThreshold;
    }

    public String toStringPk(){
        return String.format("itemType: %s, itemId: %d", getItemType().getName(), getId());
    }
}
