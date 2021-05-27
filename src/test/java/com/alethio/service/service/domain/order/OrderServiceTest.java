package com.alethio.service.service.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import static org.junit.jupiter.api.Assertions.*;


@DataJdbcTest
class OrderServiceTest {

    @Autowired
    private IOrderService orderService;


    @Test
    @DisplayName("Food 테이블에 있는 상품을 주문했을 때 주문 테이블이 저장되는지 검증")
    private void should_saveOrder_placeOrderFood(){

    }
}