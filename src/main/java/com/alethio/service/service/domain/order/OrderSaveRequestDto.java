package com.alethio.service.service.domain.order;


import com.alethio.service.service.domain.item.ItemType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderSaveRequestDto {

    @Valid
    private ContactInformation contactInfo;

    @Valid
    @JsonProperty("items")
    private ItemIdentifier itemIdentifier;


    public Order toEntity() {
        ContactInformation trimContactInformation =
                ContactInformation.builder()
                .contactEmail(contactInfo.getContactEmail().trim())
                .contactName(contactInfo.getContactName().trim())
                .mobile(contactInfo.getMobile().trim())
                .build();

        return Order.builder()
                .contactInformation(trimContactInformation)
                .itemIdentifier(itemIdentifier)
                .build();
    }

}
