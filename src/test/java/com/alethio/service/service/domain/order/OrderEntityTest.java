package com.alethio.service.service.domain.order;

import com.alethio.service.service.domain.common.ItemType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("[도메인] OrderEntity 테스트")
class OrderEntityTest {

    @Test
    @DisplayName("주문에 연락자 이메일이 없으면 예외를 던진다")
    public void should_exception_when_order_contactEmail_is_null(){
        assertThrows(AssertionError.class, () ->
                OrderEntity.builder()
                .contactEmail(null)
                .contactName("구매자")
                .mobile("01011111111")
                .itemId(1L)
                .itemType(ItemType.FOOD)
                .build()
        );
    }

    @Test
    @DisplayName("주문에 연락자 이름이 없으면 예외를 던진다")
    public void should_exception_when_order_contactName_is_null(){
        assertThrows(AssertionError.class, () ->
                OrderEntity.builder()
                        .contactEmail("test@test.com")
                        .contactName(null)
                        .mobile("01011111111")
                        .itemId(1L)
                        .itemType(ItemType.FOOD)
                        .build()
        );
    }

    @Test
    @DisplayName("주문에 연락처가 없으면 예외를 던진다")
    public void should_exception_when_order_mobile_is_null(){
        assertThrows(AssertionError.class, () ->
                OrderEntity.builder()
                        .contactEmail("test@test.com")
                        .contactName("구매자")
                        .mobile(null)
                        .itemId(1L)
                        .itemType(ItemType.FOOD)
                        .build()
        );
    }

    @Test
    @DisplayName("주문에 Item.Id가 없으면 예외를 던진다.")
    public void should_exception_when_order_itemId_is_null(){
        assertThrows(AssertionError.class, () ->
                OrderEntity.builder()
                        .contactEmail("test@test.com")
                        .contactName("구매자")
                        .mobile("01011111111")
                        .itemId(null)
                        .itemType(ItemType.FOOD)
                        .build()
        );
    }

    @Test
    @DisplayName("주문에 ItemType이 없으면 예외를 던진다")
    public void should_exception_when_order_itemType_is_null(){
        assertThrows(AssertionError.class, () ->
                OrderEntity.builder()
                        .contactEmail("test@test.com")
                        .contactName("구매자")
                        .mobile("01011111111")
                        .itemId(1L)
                        .itemType(null)
                        .build()
        );
    }

}