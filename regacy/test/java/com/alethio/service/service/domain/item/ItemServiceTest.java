package com.alethio.service.service.domain.item;

import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.exception.business.NoSuchItemException;
import com.alethio.service.service.domain.exception.business.OutOfStockQuantityException;
import com.alethio.service.service.domain.item.itemtype.Food;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@DisplayName("[도메인] ItemService 테스트")
@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepositoryProvider itemRepositoryProvider;

    @Mock
    private IItemRepository iItemRepository;

    @Test
    @DisplayName("아이템 재고가 갯수만큼 증가되는지 검증")
    public void 아이템_재고가_증가된다() {

        //given
        ItemType itemType = ItemType.FOOD;
        Long itemId = 1L;
        Long expectQuantity = 100L;
        Food orderFood = getStubFoodItem(expectQuantity);

        given(itemRepositoryProvider.getRepositoryByItemType(any())).willReturn(iItemRepository);
        given(iItemRepository.findById(any())).willReturn(Optional.of(orderFood));
        ItemService itemService = new ItemService(itemRepositoryProvider);

        //when & then
        for(long qty=1; qty<=10; qty++){
            Long afterQty = itemService.addAvailableStock(itemType,itemId, qty).getAvailableStockQuantity();
            expectQuantity += qty;
            assertEquals(expectQuantity,afterQty);
        }
    }

    @Test
    @DisplayName("아이템 재고가 갯수만큼 감소되는지 검증")
    public void 아이템_재고가_감소된다() {

        //given
        ItemType itemType = ItemType.FOOD;
        Long itemId = 1L;
        Long expectQuantity = 100L;
        Food orderFood = getStubFoodItem(expectQuantity);

        given(itemRepositoryProvider.getRepositoryByItemType(any())).willReturn(iItemRepository);
        given(iItemRepository.findById(any())).willReturn(Optional.of(orderFood));
        ItemService itemService = new ItemService(itemRepositoryProvider);

        //when & then
        for(long qty=1; qty<=10; qty++){
            Long afterQty = itemService.removeAvailableStock(itemType,itemId, qty).getAvailableStockQuantity();
            expectQuantity -= qty;
            assertEquals(expectQuantity,afterQty);
        }

    }

    @Test
    @DisplayName("아이템 재고가 부족하면 예외를 던지는지 검증 ")
    public void 재고가_0미만이_되면_예외를_던진다() {

        //given
        ItemType itemType = ItemType.FOOD;
        Long itemId = 1L;
        Long expectQuantity = 100L;
        Food orderFood = getStubFoodItem(expectQuantity);

        given(itemRepositoryProvider.getRepositoryByItemType(any())).willReturn(iItemRepository);
        given(iItemRepository.findById(any())).willReturn(Optional.of(orderFood));
        ItemService itemService = new ItemService(itemRepositoryProvider);

        //when & then
        assertThrows(OutOfStockQuantityException.class, ()->
                itemService.removeAvailableStock(itemType,itemId,101L)
        );
    }

    @Test
    @DisplayName("존재하지 않은 아이템의 재고를 수정하려고 하면 예외를 던지는지 검증 ")
    public void 존재하지_않은_아이템의_재고를_수정하면_예외를_던진다() {

        //given
        ItemType itemType = ItemType.FOOD;
        Long itemId = 1L;

        given(itemRepositoryProvider.getRepositoryByItemType(any())).willReturn(iItemRepository);
        given(iItemRepository.findById(any())).willReturn(Optional.empty());
        ItemService itemService = new ItemService(itemRepositoryProvider);

        //when & then
        assertThrows(NoSuchItemException.class, ()->
                itemService.removeAvailableStock(itemType,itemId,1L)
        );

        assertThrows(NoSuchItemException.class, ()->
                itemService.addAvailableStock(itemType,itemId,1L)
        );
    }

    private Food getStubFoodItem(Long availableQuantity) {
        return Food.builder()
                .availableStockQuantity(availableQuantity)
                .requestStockThreshold(10L)
                .requestStockQuantity(100L)
                .vendor(Vendor.AMADON)
                .name("라면")
                .build();
    }

}