package PawnShop.PawnShop.model.factroies;

import PawnShop.PawnShop.model.Clothes;
import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.model.factroies.base.ItemFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ClothesFactory implements ItemFactory {
    @Override
    public PawnItem createPawnItem(Map<String, String> formData) {
        return new Clothes(formData);
    }
}
