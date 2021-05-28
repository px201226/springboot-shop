package com.alethio.service.service.domain.order;

import com.alethio.service.service.domain.item.ItemType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {


    @Test
    @DisplayName("Order 객체의 필드값에 null이 들어가면 안된다")
    public void should_exception_when_order_field_is_null(){

        //given
        Order.ContactInformation contactInformation = Order.ContactInformation.builder()
                .contactEmail("test@test.com")
                .contactName("hong")
                .mobile("111")
                .build();

        Order.ItemIdentifier itemIdentifier = Order.ItemIdentifier.builder()
                .itemId(1L)
                .itemType(ItemType.CLOTHES)
                .build();

        //when & then
        assertThrows(AssertionError.class, () ->
                Order.builder()
                .itemIdentifier(null)
                .contactInformation(contactInformation)
                .build()
        );

        assertThrows(AssertionError.class, () ->
                Order.builder()
                        .itemIdentifier(itemIdentifier)
                        .contactInformation(null)
                        .build()
        );
    }

    @Test
    @DisplayName("Order.ContactInformation 객체의 필드값에 null이 들어가면 안된다")
    public void should_exception_when_order_contactInformation_field_is_null(){

        assertThrows(AssertionError.class, () ->
                Order.ContactInformation.builder()
                        .contactName(null)
                        .contactEmail("test@test.com")
                        .mobile("01011111111")
                        .build()
        );

        assertThrows(AssertionError.class, () ->
                Order.ContactInformation.builder()
                        .contactName("hong")
                        .contactEmail(null)
                        .mobile("0101111111")
                        .build()
        );

        assertThrows(AssertionError.class, () ->
                Order.ContactInformation.builder()
                        .contactName("hong")
                        .contactEmail("test@test.com")
                        .mobile(null)
                        .build()
        );
    }

    @Test
    @DisplayName("Order.ItemIdentifier 객체의 필드값에 null이 들어가면 안된다")
    public void should_exception_when_order_itemIdentifier_field_is_null(){

        assertThrows(AssertionError.class, () ->
                Order.ItemIdentifier.builder()
                    .itemId(1L)
                    .itemType(null)
                    .build()
        );


        assertThrows(AssertionError.class, () ->
                Order.ItemIdentifier.builder()
                        .itemId(0L)
                        .itemType(ItemType.CLOTHES)
                        .build()
        );
    }
}