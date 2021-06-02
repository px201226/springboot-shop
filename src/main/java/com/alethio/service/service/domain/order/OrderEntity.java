package com.alethio.service.service.domain.order;


import com.alethio.service.service.domain.common.LocalDateTimeEntity;
import com.alethio.service.service.domain.common.ItemType;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="order_table")                                     // 테이블 이름을 order로 하면 DB 예약어라 안됨
public class OrderEntity extends LocalDateTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contactEmail;

    private String contactName;

    private String mobile;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    private Long itemId;

    @Builder
    public OrderEntity(String contactEmail, String contactName, String mobile, ItemType itemType, Long itemId) {
        this.contactEmail = contactEmail;
        this.contactName = contactName;
        this.mobile = mobile;
        this.itemType = itemType;
        this.itemId = itemId;
    }

}
