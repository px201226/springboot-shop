package com.alethio.service.springboot.remote;

import com.alethio.service.common.ItemStatusDTO;
import com.alethio.service.common.ItemType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 상품 주문 메시지를 받아 재고 관련 처리를 하는 서비스
 */
@FeignClient(name = "stock", url = "http://localhost:8081/stock")
public interface IStockRemoteService {

    /**
     * 해당 상품을 갯수만큼 주문합니다
     *
     * @param itemType      주문 상품의 ItemType
     * @param itemId        주문 상품의 Id
     * @param quantity      주문 상품량
     * @return              상품 주문 후, 해당 상품의 상태 객체
     */
    @PostMapping(consumes = "application/json")
    public String placeOrder(@RequestParam("itemType") ItemType itemType,
                             @RequestParam("itemId")      Long itemId,
                             @RequestParam("quantity")    Long quantity);


    /**
     * 해당 상품의 상태를 조회합니다
     *
     * @param itemType      조회할 상품의 ItemType
     * @param itemId        조회할 상품의 id
     * @return              조회한 상품의 상태 객체
     */
    @GetMapping
    public ItemStatusDTO getItemStatus(@RequestParam("itemType") ItemType itemType,
                                       @RequestParam("itemId")   Long itemId);


    /**
     * 테스트용 함수
     * 해당 상품의 재고를 갯수만큼 증가시킨다
     *
     * @param itemType      상품의 ItemType
     * @param itemId        상품의 Id
     * @param quantity      증가량
     * @return              증가 후, 해당 상품의 상태 객체
     */
    @GetMapping
    public ItemStatusDTO addAvailableStock(@RequestParam("itemType") ItemType itemType,
                                           @RequestParam("itemId")   Long itemId,
                                           @RequestParam("quantity") Long quantity);


}

