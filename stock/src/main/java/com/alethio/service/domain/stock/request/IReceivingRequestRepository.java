package com.alethio.service.domain.stock.request;


import com.alethio.service.common.ItemType;

import java.util.List;
import java.util.Optional;

public interface IReceivingRequestRepository {

    /**
     * ItemType 과 ItemId에 해당하는 입고 요청을 찾는다
     *
     * @param itemType                  조회할 상품의 ItemType
     * @param itemId                    조회할 상품의 ItemId
     * @return                          조회 상품에 해당하는 입고 요청 데이터를 반환한다
     */
    public Optional<ReceivingRequestEntity> findByItemTypeAndItemId(ItemType itemType, Long itemId);

    /**
     * 입고 요청 데이터를 저장한다.
     *
     * @param receivingRequestEntity    저장할 입고 요청 Entity
     * @return                          저장된 입고 요청 Entity
     */
    public ReceivingRequestEntity save(ReceivingRequestEntity receivingRequestEntity);

    /**
     * 모든 입고 요청 데이터를 찾는다.
     *
     * @return                          입고 요청 List
     */
    public List<ReceivingRequestEntity> findAll();

    /**
     * ItemType 과 ItemId에 해당하는 상품을 입고 요청 테이블에서 삭제한다.
     *
     * @param itemType                  조회할 상품의 ItemType
     * @param itemId                    조회할 상품의 ItemId
     */
    public void deleteByItemTypeAndItemId(ItemType itemType, Long itemId);

    /**
     * 모든 입고 요청 데이터를 삭제한다
      */
    public void deleteAll();
}
