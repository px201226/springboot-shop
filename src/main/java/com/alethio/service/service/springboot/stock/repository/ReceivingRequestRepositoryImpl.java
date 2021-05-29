package com.alethio.service.service.springboot.stock;


import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.stock.request.IReceivingRequestRepository;
import com.alethio.service.service.domain.stock.request.ReceivingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ReceivingRequestRepository implements IReceivingRequestRepository {

    private final IReceivingRequestJpaRepository iReceivingRequestJpaRepository;

    @Override
    public Optional<ReceivingRequest> findByItemTypeAndItemId(ItemType itemType, Long itemId) {
        return iReceivingRequestJpaRepository.findByItemTypeAndItemId(itemType,itemId);
    }

    @Override
    public ReceivingRequest save(ReceivingRequest receivingRequest) {
        return iReceivingRequestJpaRepository.save(receivingRequest);
    }

    @Override
    public void deleteByItemTypeAndItemId(ItemType itemType, Long itemId) {
        iReceivingRequestJpaRepository.deleteByItemTypeAndItemId(itemType,itemId);
    }
}
