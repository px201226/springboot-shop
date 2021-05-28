package com.alethio.service.service.domain.order;


import com.alethio.service.service.domain.common.LocalDateTimeEntity;
import com.alethio.service.service.domain.item.ItemType;
import lombok.*;
import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="order_table")                                     // 테이블 이름을 order로 하면 DB 예약어라 안됨
public class Order extends LocalDateTimeEntity {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ContactInformation {
        private String contactEmail;
        private String contactName;
        private String mobile;

        @Builder
        public ContactInformation(String contactEmail, String contactName, String mobile) {
            assert contactEmail != null && !contactEmail.equals("") : "contactEmail must not be empty";
            assert contactName != null && !contactName.equals("") : "contactName must not be empty";
            assert mobile != null && !mobile.equals("") : "mobile must not be empty";

            this.contactEmail = contactEmail;
            this.contactName = contactName;
            this.mobile = mobile;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ItemIdentifier {

        @Enumerated(EnumType.STRING)
        private ItemType itemType;
        private Long itemId;

        @Builder
        public ItemIdentifier(ItemType itemType, Long itemId) {
            assert itemType != null : "itemType must be not null";
            assert itemId > 0 : "itemId must be not 0 or negative";

            this.itemType = itemType;
            this.itemId = itemId;
        }
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Order.ContactInformation contactInformation;

    @Embedded
    private Order.ItemIdentifier itemIdentifier;

    @Builder
    public Order(ContactInformation contactInformation, ItemIdentifier itemIdentifier) {
        assert contactInformation != null : "contactInformation must be not null";
        assert itemIdentifier != null : "itemIdentifier must be not null";

        this.contactInformation = contactInformation;
        this.itemIdentifier = itemIdentifier;
    }

}
