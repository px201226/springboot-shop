package stock.springboot.stock.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stock.domain.common.ItemType;
import stock.domain.stock.request.ReceivingRequestEntity;

import java.util.Optional;

@Repository
public interface IReceivingRequestJpaRepository extends JpaRepository<ReceivingRequestEntity, Long> {

    public Optional<ReceivingRequestEntity> findByItemTypeAndItemId(ItemType itemType, Long itemId);
    public void deleteByItemTypeAndItemId(ItemType itemType, Long itemId);
}
