package com.alethio.service.service.domain.order;

import com.alethio.service.service.domain.item.AbstractItemRepositoryProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class OrderEntityServiceTest {

    @Mock
    private IOrderRepository iOrderRepository;

    @Mock
    private AbstractItemRepositoryProvider abstractItemRepositoryProvider;

    @Test
    @DisplayName("Food 테이블에 있는 상품을 주문했을 때 주문 테이블이 저장되는지 검증")
    private void should_saveOrder_placeOrderFood(){

    }
}