package com.alethio.service.service.springboot.order;

import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.item.IItemRepository;
import com.alethio.service.service.domain.item.ItemEntity;
import com.alethio.service.service.domain.item.ItemRepositoryProvider;
import com.alethio.service.service.domain.item.Vendor;
import com.alethio.service.service.domain.item.itemtype.Food;
import com.alethio.service.service.domain.order.IOrderService;
import com.alethio.service.service.domain.order.OrderEntity;
import com.alethio.service.service.domain.order.PlaceOrderRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("[스프링] OrderSerivce 테스트")
@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private IOrderService orderSerivce;

    @Autowired
    private ItemRepositoryProvider itemRepositoryProvider;

    @Test
    @DisplayName("주문 요청을 받으면 주문정보를 저장한다")
    public void 주문_요청을_받으면_주문정보를_저장한다(){

        // given
        Long availableQty = 100L;

        Food food = saveStubFoodEntity("라면", availableQty, 100L, 100L);
        PlaceOrderRequestDTO placeOrderRequestDTO = buildPlaceOrderRequestDTO(food);

        // when
        OrderEntity orderEntity = orderSerivce.placeOrder(placeOrderRequestDTO);

        // verify(sto
        assertEquals(food.getItemType(),orderEntity.getItemType());
        assertEquals(food.getId(),orderEntity.getItemId());

    }

    private PlaceOrderRequestDTO buildPlaceOrderRequestDTO(ItemEntity item){
        return PlaceOrderRequestDTO.builder()
                .contactEmail("test@test.com")
                .contactName("홍길동")
                .mobile("01012341111")
                .itemId(item.getId())
                .itemType(item.getItemType())
                .build();
    }

    private Food saveStubFoodEntity(String name, Long quantity, Long threshold, Long requestQuantity){
        Food food = Food.builder()
                .name(name)
                .availableStockQuantity(quantity)
                .requestStockThreshold(threshold)
                .requestStockQuantity(requestQuantity)
                .vendor(Vendor.AMADON)
                .build();

        IItemRepository itemRepository = itemRepositoryProvider.getRepositoryByItemType(ItemType.FOOD);
        ItemEntity foodEntity = itemRepository.save(food);

        return (Food) foodEntity;
    }
}