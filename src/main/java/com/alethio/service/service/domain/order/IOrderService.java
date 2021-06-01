package com.alethio.service.service.domain.order;


/**
 * 상품 주문 요청을 담당하는 서비스
 */
public interface IOrderService {

    /**
     * 상품 주문을 요청합니다.
     *
     * @param placeOrderRequestDto  주문 상품에 대한 정보를 담는 객체
     * @return                      상품 주문에 대한 정보를 반환합니다
     */
    public OrderEntity placeOrder(PlaceOrderRequestDTO placeOrderRequestDto);
}
