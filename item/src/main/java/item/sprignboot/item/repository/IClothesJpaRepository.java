package item.sprignboot.item.repository;


import item.domain.item.itemtype.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface IClothesJpaRepository extends JpaRepository<Clothes, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Optional<Clothes> findById(Long id);
}
