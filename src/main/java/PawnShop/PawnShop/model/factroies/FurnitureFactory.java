package PawnShop.PawnShop.model.factroies;

import PawnShop.PawnShop.model.Furniture;
import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.model.factroies.base.ItemFactory;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FurnitureFactory implements ItemFactory {
    @Override
    public PawnItem createPawnItem(Map<String, String> formData) {
        return new Furniture(formData);
    }
}
