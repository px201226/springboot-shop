package com.alethio.service.service.domain.item;

import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.exception.OutOfStockQuantityException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemEntityTest {

    @Test
    @DisplayName("아이템 재고가 임계값보다 같거나 많은 상태 검증")
    public void should(){

        //given
        Long availableStock = 10L;
        Long threshold = 10L;
        ItemEntity itemEntity = mock(ItemEntity.class, withSettings()
                .useConstructor(Vendor.AMADON, availableStock, threshold, 100L, "떡볶이")
                .defaultAnswer(CALLS_REAL_METHODS));

        //when & then
        assertEquals(false, itemEntity.isAvailableStockLessThreshold());

    }


    @Test
    @DisplayName("아이템 재고가 임계값보다 작은 상태 검증")
    public void should2(){

        //given
        Long availableStock = 9L;
        Long threshold = 10L;
        ItemEntity itemEntity = mock(ItemEntity.class, withSettings()
                .useConstructor(Vendor.AMADON, availableStock, threshold, 100L, "떡볶이")
                .defaultAnswer(CALLS_REAL_METHODS));

        //when & then
        assertEquals(true, itemEntity.isAvailableStockLessThreshold());

    }

    @Test
    @DisplayName("아이템 재고 수량이 감소되는지 검증")
    public void should3(){

        //given
        Long availableStock = 11L;
        Long expectQuantity = 10L;
        ItemEntity itemEntity = mock(ItemEntity.class, withSettings()
                .useConstructor(Vendor.AMADON, availableStock, 10L, 100L, "떡볶이")
                .defaultAnswer(CALLS_REAL_METHODS));

        //when & then
        assertEquals(expectQuantity, itemEntity.decreaseAvailableStock(1));

    }

    @Test
    @DisplayName("아이템 재고 수량이 증가되는지 검증")
    public void should4(){

        //given
        Long availableStock = 10L;
        Long expectQuantity = 11L;
        ItemEntity itemEntity = mock(ItemEntity.class, withSettings()
                .useConstructor(Vendor.AMADON, availableStock, 10L, 100L, "떡볶이")
                .defaultAnswer(CALLS_REAL_METHODS));

        //when & then
        assertEquals(expectQuantity, itemEntity.increaseAvailableStock(1));

    }

    @Test
    @DisplayName("아이템 재고 수량 0미만 이면 예외를 던지는지 검증")
    public void should5(){

        //given
        Long availableStock = 0L;
        ItemEntity itemEntity = mock(ItemEntity.class, withSettings()
                .useConstructor(Vendor.AMADON, availableStock, 10L, 100L, "떡볶이")
                .defaultAnswer(CALLS_REAL_METHODS));

        //when & then
        assertThrows(OutOfStockQuantityException.class, () ->
                itemEntity.decreaseAvailableStock(1)
        );

    }
}