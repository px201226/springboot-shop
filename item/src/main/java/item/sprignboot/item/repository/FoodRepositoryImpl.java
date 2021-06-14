package item.sprignboot.item.repository;


import item.domain.item.IItemRepository;
import item.domain.item.itemtype.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class FoodRepositoryImpl implements IItemRepository<Food> {

    private final IFoodJpaRepository iFoodJpaRepository;

    @Override
    public Optional<Food> findById(Long id) {
        return iFoodJpaRepository.findById(id);
    }

    @Override
    public Food save(Food food) {
        return iFoodJpaRepository.save(food);
    }

}
