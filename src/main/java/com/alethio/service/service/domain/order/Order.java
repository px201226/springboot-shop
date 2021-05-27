package com.alethio.service.service.domain.order;


import com.alethio.service.service.domain.common.LocalDateTimeEntity;

import com.alethio.service.service.domain.item.ItemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="order_table")                                     // 테이블 이름을 order로 하면 DB 예약어라 안됨
public class Order extends LocalDateTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private ContactInformation contactInformation;

    @Embedded
    private ItemIdentifier itemIdentifier;

}
