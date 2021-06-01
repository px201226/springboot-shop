package com.alethio.service.service.domain.stock;

import com.alethio.service.service.domain.common.ItemStatusDTO;
import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.item.IItemService;
import com.alethio.service.service.domain.item.Vendor;
import com.alethio.service.service.domain.item.itemtype.Food;
import com.alethio.service.service.domain.stock.request.IReceivingRequestRepository;
import com.alethio.service.service.domain.stock.request.ReceivingRequestEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@DisplayName("[도메인] StockService 테스트")
@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @Mock
    IItemService itemService;

    @Mock
    IReceivingRequestRepository receivingRequestRepository;

    @Test
    @DisplayName("StockService가 재고 추가 메시지를 받으면 ItemService에게 재고 추가 메시지를 보내는지 검증")
    public void addAvailableStock_메시즈를_받으면_ItemService_에게_재고추가_메시지를_보낸다() {

        //given
        Long tempItemId= 100L;
        Long addQty = 10L;
        Food item = getStubFoodItem("떡볶이", 10L, 10L, 10L);

        given(itemService.addAvailableStock(any(), anyLong(),anyLong())).willReturn(ItemStatusDTO.of(item));
        StockService stockService = new StockService(itemService, receivingRequestRepository);

        //when
        stockService.addAvailableStock(item.getItemType(), tempItemId, addQty);

        //then
        verify(itemService).addAvailableStock(item.getItemType(), tempItemId, addQty);
    }

    @Test
    @DisplayName("주문 요청 시, ItemService에게 재고 감소 메시지를 보내는지 검증")
    public void placeOrder_메시지를_받으면_ItemService_에게_재고감소_메시지를_보낸다() {

        //given
        Long tempItemId= 100L;
        Long removeQty = 10L;
        Food food = getStubFoodItem("떡볶이", 10L, 10L, 10L);

        given(itemService.removeAvailableStock(any(), anyLong(),anyLong())).willReturn(ItemStatusDTO.of(food));
        StockService stockService = new StockService(itemService, receivingRequestRepository);

        //when
        stockService.placeOrder(food.getItemType(), tempItemId, removeQty);

        //then
        verify(itemService).removeAvailableStock(food.getItemType(), tempItemId, removeQty);
    }

    @Test
    @DisplayName("주문을 하고 재고가 임계값 보다 작으면 입고 요청을 하는지 검증")
    public void 재고가_임계값보다_작으면_ReceivingRequest에_입고_데이터를_저장한다() {

        //given
        Long tempItemId = 100L;
        Long removeQty = 1L;
        Food food = getStubFoodItem("떡볶이", 9L, 10L, 10L);
        ReceivingRequestEntity receivingRequestEntity = getStubReceivingRequest(food);

        given(itemService.removeAvailableStock(any(), anyLong(),anyLong())).willReturn(ItemStatusDTO.of(food));
        given(receivingRequestRepository.findByItemTypeAndItemId(any(),isNull())).willReturn(Optional.empty());
        given(receivingRequestRepository.save(any())).willReturn(receivingRequestEntity);

        StockService stockService = new StockService(itemService, receivingRequestRepository);

        //when
        stockService.placeOrder(food.getItemType(), tempItemId, removeQty);

        //then
        verify(receivingRequestRepository).save(any());
    }

    @Test
    @DisplayName("이미 입고 요청한 아이템은 입고 요청 테이블에 저장되지 않는지 검증")
    public void 이미_입고_요청한_아이템은_저장하지_않는다() {

        //given
        Long removeQty = 1L;
        Food food = getStubFoodItem("떡볶이", 9L, 10L, 10L);
        ReceivingRequestEntity receivingRequestEntity = getStubReceivingRequest(food);

        given(itemService.removeAvailableStock(any(), isNull(), anyLong())).willReturn(ItemStatusDTO.of(food));
        given(receivingRequestRepository.findByItemTypeAndItemId(any(),isNull())).willReturn(Optional.of(receivingRequestEntity));

        StockService stockService = new StockService(itemService, receivingRequestRepository);

        //when
        stockService.placeOrder(ItemType.FOOD, null, removeQty);

        //then
        verify(receivingRequestRepository,never()).save(any());
    }

    private ReceivingRequestEntity getStubReceivingRequest(Food food) {
        return ReceivingRequestEntity.builder()
                .itemType(food.getItemType())
                .itemName(food.getName())
                .encryptKey(food.getVendor().encryptKey(food.getName()))
                .itemId(10L)
                .requestStockQuantity(food.getRequestStockQuantity())
                .build();
    }

    private Food getStubFoodItem(String name, Long quantity, Long threshold, Long requestQuantity) {
        return Food.builder()
                .name(name)
                .availableStockQuantity(quantity)
                .requestStockThreshold(threshold)
                .requestStockQuantity(requestQuantity)
                .vendor(Vendor.AMADON)
                .build();
    }

}