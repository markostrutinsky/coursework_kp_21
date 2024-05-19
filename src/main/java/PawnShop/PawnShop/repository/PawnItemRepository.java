package PawnShop.PawnShop.repository;

import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.model.PawnItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PawnItemRepository extends JpaRepository<PawnItem, Long> {
    List<? extends PawnItem> findByCategory(PawnItemCategory category);
}
