package com.alethio.service.service.domain.stock.request;

import com.alethio.service.service.domain.common.ItemType;

import java.util.List;
import java.util.Optional;

public interface IReceivingRequestRepository {

    public Optional<ReceivingRequestEntity> findByItemTypeAndItemId(ItemType itemType, Long itemId);
    public ReceivingRequestEntity save(ReceivingRequestEntity receivingRequestEntity);
    public List<ReceivingRequestEntity> findAll();
    public void deleteByItemTypeAndItemId(ItemType itemType, Long itemId);
    public void deleteAll();
}
