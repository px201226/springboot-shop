package com.alethio.service.service.domain.stock.request;

import com.alethio.service.service.domain.common.ItemType;

import java.util.Optional;

public interface IReceivingRequestRepository {

    public Optional<ReceivingRequest> findByItemTypeAndItemId(ItemType itemType, Long itemId);
    public ReceivingRequest  save(ReceivingRequest receivingRequest);
    public void deleteByItemTypeAndItemId(ItemType itemType, Long itemId);
}
