package com.alethio.service.service.domain.item;

import com.alethio.service.service.domain.common.ItemStatusDTO;
import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.exception.business.OutOfStockQuantityException;

/**
* 상품 정보를 관리하는 서비스
* */
public interface IItemService {

    /**
     * 해당 아이템 재고를 감소시킵니다
     *
     * @param itemType      재고를 줄일 상품의 ItemType
     * @param itemId        재고를 줄일 상품의 id
     * @param quantity      줄일 양
     * @return              재고를 줄인 후 상품의 상태 객체
     * @throws OutOfStockQuantityException  이용 가능한 재고가 즐일 양보다 부족한 경우 예외를 던집니다.
     */
    public ItemStatusDTO removeAvailableStock(ItemType itemType, Long itemId, Long quantity) throws OutOfStockQuantityException;


    /**
     * 해당 아이템의 상태를 조회합니다.
     *
     * @param itemType      조회할 상품의 ItemType
     * @param itemId        조회할 상품의 id
     * @return              조회할 상품의 상태 객체
     */
    public ItemStatusDTO getItemStatus(ItemType itemType, Long itemId);


    /*
    * 테스트용 메서드
    * @Deprecated 현재 사용되지 않는 메서드입니다
    *  */
    @Deprecated
    public ItemStatusDTO addAvailableStock(ItemType itemType, Long itemId, Long quantity);
}
