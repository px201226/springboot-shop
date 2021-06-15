package order.domain.order;


import com.alethio.service.common.ItemType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"contactInfo","items","createDateTime"})
public class PlaceOrderResponseDTO {

    @JsonProperty("contactInfo")
    private String contactEmail;

    @JsonProperty("contactInfo")
    private String contactName;

    @JsonProperty("contactInfo")
    private String mobile;

    @JsonProperty("items")
    private ItemType itemType;

    @JsonProperty("items")
    private Long itemId;

    private LocalDateTime createDateTime;

    @JsonProperty("contactInfo")
    private Map<String, Object> packContactInfo(){
        Map<String,Object> pack = new HashMap<>();
        pack.put("contactEmail", contactEmail);
        pack.put("contactName", contactName);
        pack.put("mobile", mobile);
        return pack;
    }

    @JsonProperty("items")
    private Map<String, Object> packItems(){
        Map<String,Object> pack = new HashMap<>();
        pack.put("id", itemId);
        pack.put("itemType", itemType.getName());
        return pack;
    }

    public static PlaceOrderResponseDTO of(OrderEntity orderEntity){
        return PlaceOrderResponseDTO.builder()
                .contactEmail(orderEntity.getContactEmail())
                .contactName(orderEntity.getContactName())
                .mobile(orderEntity.getMobile())
                .itemType(orderEntity.getItemType())
                .itemId(orderEntity.getItemId())
                .createDateTime(orderEntity.getCreatedDate())
                .build();
    }
}
