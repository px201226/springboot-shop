package com.alethio.service.service.springboot.order.dto;

import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.order.OrderEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderSaveResponseDto {

    @JsonIgnore
    private String contactEmail;

    @JsonIgnore
    private String contactName;

    @JsonIgnore
    private String mobile;

    @JsonIgnore
    private ItemType itemType;

    @JsonIgnore
    private Long itemId;

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

    public static OrderSaveResponseDto of(OrderEntity orderEntity){
        return OrderSaveResponseDto.builder()
                .contactEmail(orderEntity.getContactEmail())
                .contactName(orderEntity.getContactName())
                .mobile(orderEntity.getMobile())
                .itemType(orderEntity.getItemType())
                .itemId(orderEntity.getItemId())
                .build();
    }
}
