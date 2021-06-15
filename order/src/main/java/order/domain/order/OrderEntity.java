package order.domain.order;


import com.alethio.service.common.ItemType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="order_table")                                     // 테이블 이름을 order로 하면 DB 예약어라 안됨
public class OrderEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contactEmail;

    private String contactName;

    private String mobile;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    private Long itemId;

    @CreatedDate
    private LocalDateTime createdDate;

    @Builder
    public OrderEntity(String contactEmail, String contactName, String mobile, ItemType itemType, Long itemId) {
        this.contactEmail = contactEmail;
        this.contactName = contactName;
        this.mobile = mobile;
        this.itemType = itemType;
        this.itemId = itemId;
    }

}
