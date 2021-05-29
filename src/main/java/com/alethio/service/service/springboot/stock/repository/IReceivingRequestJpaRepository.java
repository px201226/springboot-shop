package com.alethio.service.service.springboot.stock;

import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.stock.request.ReceivingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IReceivingRequestJpaRepository extends JpaRepository<ReceivingRequest, Long> {

    public Optional<ReceivingRequest> findByItemTypeAndItemId(ItemType itemType, Long itemId);
    public void deleteByItemTypeAndItemId(ItemType itemType, Long itemId);
}
