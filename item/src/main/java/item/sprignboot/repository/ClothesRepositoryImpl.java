package item.sprignboot.repository;


import item.domain.item.IItemRepository;
import item.domain.item.itemtype.Clothes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ClothesRepositoryImpl implements IItemRepository<Clothes> {

    private final IClothesJpaRepository iClothesJpaRepository;

    @Override
    public Optional<Clothes> findById(Long id) {
        return iClothesJpaRepository.findById(id);
    }

    @Override
    public Clothes save(Clothes clothes) {
        return iClothesJpaRepository.save(clothes);
    }

}
