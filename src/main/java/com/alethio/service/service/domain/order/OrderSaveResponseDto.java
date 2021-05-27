package com.alethio.service.service.domain.order;

import com.alethio.service.service.domain.item.ItemType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderSaveResponseDto {

    private ContactInformation contactInfo;

    @JsonProperty("items")
    private ItemIdentifier itemIdentifier;

    public static OrderSaveResponseDto of(Order order){
        return OrderSaveResponseDto.builder()
                .contactInfo(order.getContactInformation())
                .itemIdentifier(order.getItemIdentifier())
                .build();
    }
}
