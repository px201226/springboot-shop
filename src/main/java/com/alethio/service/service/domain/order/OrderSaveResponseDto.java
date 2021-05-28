package com.alethio.service.service.domain.order;

import com.alethio.service.service.domain.item.ItemType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderSaveResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class ContactInformationResponseDto {

        @NotEmpty private String contactEmail;
        @NotEmpty private String contactName;
        @NotEmpty private String mobile;

        public static ContactInformationResponseDto of(Order.ContactInformation contactInformation){
            return ContactInformationResponseDto.builder()
                    .contactEmail(contactInformation.getContactEmail())
                    .contactName(contactInformation.getContactName())
                    .mobile(contactInformation.getMobile())
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class ItemIdentifierResponseDto {

        @NotNull
        private ItemType itemType;

        @Positive @JsonProperty("id")
        private Long itemId;

        public static ItemIdentifierResponseDto of(Order.ItemIdentifier itemIdentifier) {
            return ItemIdentifierResponseDto.builder()
                    .itemId(itemIdentifier.getItemId())
                    .itemType(itemIdentifier.getItemType())
                    .build();
        }
    }

    @JsonProperty("contactInfo")
    private ContactInformationResponseDto contactInfoDto;

    @JsonProperty("items")
    private ItemIdentifierResponseDto itemIdentifierResponseDto;

    public static OrderSaveResponseDto of(Order order){
        return OrderSaveResponseDto.builder()
                .contactInfoDto(ContactInformationResponseDto.of(order.getContactInformation()))
                .itemIdentifierResponseDto(ItemIdentifierResponseDto.of(order.getItemIdentifier()))
                .build();
    }
}
