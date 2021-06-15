package com.alethio.service.service.domain.order;

import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.stock.IStockService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@DisplayName("[도메인] OrderSerivce 테스트")
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private IOrderRepository orderRepository;

    @Mock
    private IStockService stockService;

    @Test
    @DisplayName("주문 요청을 받으면 StockService에게 주문을 전달하는지 검증")
    public void 주문_요청을_받으면_StockService의_placeOrder_메서드를_호출한다(){

        //given
        PlaceOrderRequestDTO saveRequestDTO =
                getStubOrderSaveRequestDTO("test@test.com","홍길동",1L,ItemType.FOOD,"01011111111");

        OrderService orderService = new OrderService(orderRepository,stockService);

        //when
        orderService.placeOrder(saveRequestDTO);

        //then
        verify(stockService)
                .placeOrder(saveRequestDTO.getItemType(),saveRequestDTO.getItemId(),1L);
    }

    @Test
    @DisplayName("주문 요청을 받으면 주문 내역을 레포지토리에 저장하는지 검증")
    public void 주문_요청을_받으면_주문_데이터를_레포지토리에_save한다(){

        //given
        OrderEntity expectOrderEntity =
                getStubOrderSaveRequestDTO("test@test.com","홍길동",1L,ItemType.FOOD,"01011111111").toEntity();

        PlaceOrderRequestDTO mockDto = mock(PlaceOrderRequestDTO.class);
        given(mockDto.toEntity())
                .willReturn(expectOrderEntity);

        OrderService orderService = new OrderService(orderRepository,stockService);

        //when
        OrderEntity orderEntity = orderService.placeOrder(mockDto);

        //then
        verify(orderRepository).save(expectOrderEntity);
    }

    private PlaceOrderRequestDTO getStubOrderSaveRequestDTO(String contactEmail, String name, Long itemId, ItemType itemType, String mobile){
        return PlaceOrderRequestDTO.builder()
                .contactEmail(contactEmail)
                .contactName(name)
                .itemId(itemId)
                .itemType(itemType)
                .mobile(mobile)
                .build();
    }


}