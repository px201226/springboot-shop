package stock.domain.stock.request;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import stock.domain.common.ItemType;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="receiving_request")
public class ReceivingRequestEntity  {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    private Long itemId;

    private String itemName;

    private String encryptKey;

    private Long requestStockQuantity;

    @CreatedDate
    private LocalDateTime createdDate;

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
