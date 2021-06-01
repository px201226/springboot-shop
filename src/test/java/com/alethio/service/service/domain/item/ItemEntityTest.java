package com.alethio.service.service.domain.item;

import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.exception.business.OutOfStockQuantityException;
import org.hibernate.annotations.common.reflection.ReflectionUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("[도메인] ItemEntity 테스트")
class ItemEntityTest {

    @Test
    @DisplayName("이용 가능한 재고가 임계값보다 같거나 많은 상태 검증")
    public void 이용가능한_재고가_임계값_보다_충분한_상태(){

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
    @DisplayName("이용_가능한 재고가 임계값보다 작은 상태 검증")
    public void 이용가능한_재고가_임계값_보다_작은_상태(){

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
    @DisplayName("이용 가능한 재고를 줄일 수 있다")
    public void 이용_가능한_재교를_줄인다(){

        //given
        Long availableStock = 11L;
        Long expectQuantity = 10L;
        ItemEntity itemEntity = mock(ItemEntity.class, withSettings()
                .useConstructor(Vendor.AMADON, availableStock, 10L, 100L, "떡볶이")
                .defaultAnswer(CALLS_REAL_METHODS));

        //when & then
        assertEquals(expectQuantity, itemEntity.removeAvailableStock(1L));

    }

    @Test
    @DisplayName("이용 가능한 재고를 추가시킬 수 있다")
    public void 이용_가능한_재고를_추가시킨다(){

        //given
        Long availableStock = 10L;
        Long expectQuantity = 11L;
        ItemEntity itemEntity = mock(ItemEntity.class, withSettings()
                .useConstructor(Vendor.AMADON, availableStock, 10L, 100L, "떡볶이")
                .defaultAnswer(CALLS_REAL_METHODS));

        //when & then
        assertEquals(expectQuantity, itemEntity.addAvailableStock(1L));

    }

    @Test
    @DisplayName("이용 가능한 재고 수량이 0미만 이면 예외를 던지는지 검증")
    public void 이용_가능한_재고가_0_미만이면_예외를_던진다(){

        //given
        Long availableStock = 0L;
        ItemEntity itemEntity = mock(ItemEntity.class, withSettings()
                .useConstructor(Vendor.AMADON, availableStock, 10L, 100L, "떡볶이")
                .defaultAnswer(CALLS_REAL_METHODS));

        given(itemEntity.getItemType()).willReturn(ItemType.FOOD);
        given(itemEntity.toStringPk()).willReturn("stub data");

        //when & then
        assertThrows(OutOfStockQuantityException.class, () ->
                itemEntity.removeAvailableStock(1L)
        );

    }
}