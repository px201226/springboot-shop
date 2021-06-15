package order.domain.order;


import com.alethio.service.common.ItemType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlaceOrderRequestDTO {

    @NotEmpty
    private String contactEmail;

    @NotEmpty
    private String contactName;

    @NotEmpty
    private String mobile;

    @NotNull
    private ItemType itemType;

    @Positive
    private Long itemId;

    @JsonProperty("contactInfo")
    private void unpackContactInfo(Map<String,Object> contactMap) {
        this.contactEmail = (String) contactMap.get("contactEmail");
        this.contactName = (String) contactMap.get("contactName");
        this.mobile = (String) contactMap.get("mobile");
    }

    @JsonProperty("items")
    private void unpackItems(Map<String,Object> contactMap) {
        this.itemId =  Long.valueOf(contactMap.get("id").toString());
        this.itemType =  ItemType.fromString((String)contactMap.get("itemType"));
    }

    public OrderEntity toEntity() {
        return OrderEntity.builder()
                .contactEmail(contactEmail.trim())
                .contactName(contactName.trim())
                .mobile(mobile.trim())
                .itemType(itemType)
                .itemId(itemId)
                .build();
    }

}
