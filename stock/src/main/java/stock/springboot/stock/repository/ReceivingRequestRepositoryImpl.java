package stock.springboot.stock.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import stock.domain.common.ItemType;
import stock.domain.stock.request.IReceivingRequestRepository;
import stock.domain.stock.request.ReceivingRequestEntity;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ReceivingRequestRepositoryImpl implements IReceivingRequestRepository {

    private final IReceivingRequestJpaRepository receivingRequestJpaRepository;

    @Override
    public Optional<ReceivingRequestEntity> findByItemTypeAndItemId(ItemType itemType, Long itemId) {
        return receivingRequestJpaRepository.findByItemTypeAndItemId(itemType,itemId);
    }

    @Override
    public ReceivingRequestEntity save(ReceivingRequestEntity receivingRequestEntity) {
        return receivingRequestJpaRepository.save(receivingRequestEntity);
    }

    @Override
    public void deleteByItemTypeAndItemId(ItemType itemType, Long itemId) {
        receivingRequestJpaRepository.deleteByItemTypeAndItemId(itemType,itemId);
    }

    @Override
    public List<ReceivingRequestEntity> findAll() {
        return receivingRequestJpaRepository.findAll();
    }

    @Override
    public void deleteAll() {
        receivingRequestJpaRepository.deleteAll();
    }
}
