package com.alethio.service.service.domain.order;


import com.alethio.service.service.domain.common.LocalDateTimeEntity;

import com.alethio.service.service.domain.item.ItemType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="order_table")                                     // 테이블 이름을 order로 하면 DB 예약어라 안됨
public class Order extends LocalDateTimeEntity {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContactInformation {
        private String contactEmail;
        private String contactName;
        private String mobile;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemIdentifier {
        @Enumerated(EnumType.STRING)
        private ItemType itemType;
        private Long itemId;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Order.ContactInformation contactInformation;

    @Embedded
    private Order.ItemIdentifier itemIdentifier;


}
