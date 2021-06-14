package order.domain.common;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class ItemStatusDTO {

    private ItemType itemType;                          // 요청한 Item의 타입
    private Long id;                                    // 요청한 Item의 아이디
    private String itemName;                            // 요청한 Item의 상품명
    private Long availableStockQuantity;                // 사용가능한 재고 갯수
    private Long stockThreshold;                        // 재고 부족 임계값
    private Long requestStockQuantity;                  // 입고 요청 시 입고 요청하는 재고 수
    private String encryptKey;                          // 밴더사를 위한 암호화 키
    private Boolean isExceedStockThreshold;             // 현재 재고가 임계값을 초과했는지


}
