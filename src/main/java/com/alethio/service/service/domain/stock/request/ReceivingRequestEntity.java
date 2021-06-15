package com.alethio.service.service.domain.stock.request;


import com.alethio.service.service.domain.common.ItemType;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="receiving_request")
public class ReceivingRequestEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    private Long itemId;

    private String itemName;

    private String encryptKey;

    private Long requestStockQuantity;


    @Builder
    public ReceivingRequestEntity(ItemType itemType, Long itemId, String itemName, String encryptKey, Long requestStockQuantity) {

        assert itemType != null :                               "itemType must not be empty";
        assert itemName != null && !itemName.equals("") :       "itemName must not be empty";
        assert encryptKey != null  && !encryptKey.equals("") :  "encryptKey must not be empty";
        assert requestStockQuantity != null && requestStockQuantity > 0:    "requestQuatity must be positive";

        this.itemType = itemType;
        this.itemId = itemId;
        this.itemName = itemName;
        this.encryptKey = encryptKey;
        this.requestStockQuantity = requestStockQuantity;
    }
}
