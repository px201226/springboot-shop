package com.alethio.service.service.domain.item;

import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.exception.NoSuchItemException;
import com.alethio.service.service.domain.exception.OutOfStockQuantityException;
import com.alethio.service.service.domain.item.category.Food;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ItemEntityServiceTest {

    @Mock
    private AbstractItemRepositoryProvider abstractItemRepositoryProvider;

    @Mock
    private IItemRepository iItemRepository;

    @Test
    @DisplayName("아이템 재고가 갯수만큼 증가되는지 검증")
    public void should_stock_quantity_increase() {

        //given
        ItemType itemType = ItemType.FOOD;
        Long itemId = 1L;
        Long expectQuantity = 100L;
        Food orderFood = getStubFoodItem(expectQuantity);

        given(abstractItemRepositoryProvider.getRepositoryByItemType(any())).willReturn(iItemRepository);
        given(iItemRepository.findById(any())).willReturn(Optional.of(orderFood));
        ItemService itemService = new ItemService(abstractItemRepositoryProvider);

        //when & then
        for(int qty=0; qty<=10; qty++){
            Long afterQty = itemService.increaseAvailableStock(itemType,itemId, qty);
            expectQuantity += qty;
            assertEquals(expectQuantity,afterQty);
        }
    }

    @Test
    @DisplayName("아이템 재고가 갯수만큼 감소되는지 검증")
    public void should_stock_quantity_decrease() {

        //given
        ItemType itemType = ItemType.FOOD;
        Long itemId = 1L;
        Long expectQuantity = 100L;
        Food orderFood = getStubFoodItem(expectQuantity);

        given(abstractItemRepositoryProvider.getRepositoryByItemType(any())).willReturn(iItemRepository);
        given(iItemRepository.findById(any())).willReturn(Optional.of(orderFood));
        ItemService itemService = new ItemService(abstractItemRepositoryProvider);

        //when & then
        for(int qty=0; qty<=10; qty++){
            Long afterQty = itemService.decreaseAvailableStock(itemType,itemId, qty);
            expectQuantity -= qty;
            assertEquals(expectQuantity,afterQty);
        }

    }

    @Test
    @DisplayName("아이템 재고가 부족하면 예외를 던지는지 검증 ")
    public void should_exception_when_not_enough_quantity() {

        //given
        ItemType itemType = ItemType.FOOD;
        Long itemId = 1L;
        Long expectQuantity = 100L;
        Food orderFood = getStubFoodItem(expectQuantity);

        given(abstractItemRepositoryProvider.getRepositoryByItemType(any())).willReturn(iItemRepository);
        given(iItemRepository.findById(any())).willReturn(Optional.of(orderFood));
        ItemService itemService = new ItemService(abstractItemRepositoryProvider);

        //when & then
        assertThrows(OutOfStockQuantityException.class, ()->
                itemService.decreaseAvailableStock(itemType,itemId,101)
        );
    }

    @Test
    @DisplayName("존재하지 않은 아이템의 재고를 수정하려고 하면 예외를 던지는지 검증 ")
    public void should_exception_when_not_found_itemIdentifier() {

        //given
        ItemType itemType = ItemType.FOOD;
        Long itemId = 1L;

        given(abstractItemRepositoryProvider.getRepositoryByItemType(any())).willReturn(iItemRepository);
        given(iItemRepository.findById(any())).willReturn(Optional.empty());
        ItemService itemService = new ItemService(abstractItemRepositoryProvider);

        //when & then
        assertThrows(NoSuchItemException.class, ()->
                itemService.decreaseAvailableStock(itemType,itemId,1)
        );

        assertThrows(NoSuchItemException.class, ()->
                itemService.increaseAvailableStock(itemType,itemId,1)
        );
    }


    @Test
    @DisplayName("아이템 재고가 임계치보다 작은 상태를 검사할 수 있는지 검증")
    public void should_state_check_when_stock_quatity_less_threshld() {

        //given
        ItemType itemType = ItemType.FOOD;
        Long itemId = 1L;
        Food orderFood = getStubFoodItem(10L);

        given(abstractItemRepositoryProvider.getRepositoryByItemType(any())).willReturn(iItemRepository);
        given(iItemRepository.findById(any())).willReturn(Optional.of(orderFood));
        ItemService itemService = new ItemService(abstractItemRepositoryProvider);

        //when & then
        assertEquals(false, itemService.isAvailableStockLessThreshold(itemType,itemId));
        itemService.decreaseAvailableStock(itemType,itemId,1);
        assertEquals(true, itemService.isAvailableStockLessThreshold(itemType,itemId));

    }

    private Food getStubFoodItem(Long expectQuantity) {
        return Food.builder()
                .name("라면")
                .availableStockQuantity(expectQuantity)
                .requestStockThreshold(10L)
                .build();
    }

}