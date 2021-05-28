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

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    static class ContactInformationRequestDto {

        @NotEmpty private String contactEmail;
        @NotEmpty private String contactName;
        @NotEmpty private String mobile;

        public Order.ContactInformation toEntity(){
            return Order.ContactInformation.builder()
                    .contactEmail(contactEmail)
                    .contactName(contactName)
                    .mobile(mobile)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    static class ItemIdentifierRequestDto {

        @NotNull private ItemType itemType;
        @Positive @JsonProperty("id")
        private Long itemId;

        public Order.ItemIdentifier toEntity(){
            return Order.ItemIdentifier.builder()
                    .itemType(itemType)
                    .itemId(itemId)
                    .build();
        }
    }

    @Valid
    @JsonProperty("contactInfo")
    private OrderSaveRequestDto.ContactInformationRequestDto contactInfoRequestDto;

    @Valid
    @JsonProperty("items")
    private OrderSaveRequestDto.ItemIdentifierRequestDto itemIdentifierRequestDto;


    public Order toEntity() {
        ContactInformationRequestDto trimContactInformationRequestDto =
                ContactInformationRequestDto.builder()
                .contactEmail(contactInfoRequestDto.contactEmail.trim())
                .contactName(contactInfoRequestDto.contactName.trim())
                .mobile(contactInfoRequestDto.mobile.trim())
                .build();

        return Order.builder()
                .contactInformation(trimContactInformationRequestDto.toEntity())
                .itemIdentifier(itemIdentifierRequestDto.toEntity())
                .build();
    }

}
