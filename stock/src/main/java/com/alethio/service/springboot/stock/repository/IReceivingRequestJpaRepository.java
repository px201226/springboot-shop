package com.alethio.service.springboot.stock.repository;


import com.alethio.service.common.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alethio.service.domain.stock.request.ReceivingRequestEntity;

import java.util.Optional;

@Repository
public interface IReceivingRequestJpaRepository extends JpaRepository<ReceivingRequestEntity, Long> {

    public Optional<ReceivingRequestEntity> findByItemTypeAndItemId(ItemType itemType, Long itemId);
    public void deleteByItemTypeAndItemId(ItemType itemType, Long itemId);
}
