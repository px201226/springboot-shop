package com.alethio.service.service.springboot.stock.repository;

import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.stock.request.ReceivingRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IReceivingRequestJpaRepository extends JpaRepository<ReceivingRequestEntity, Long> {

    public Optional<ReceivingRequestEntity> findByItemTypeAndItemId(ItemType itemType, Long itemId);
    public void deleteByItemTypeAndItemId(ItemType itemType, Long itemId);
}
