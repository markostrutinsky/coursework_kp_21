package PawnShop.PawnShop.model.factroies;

import PawnShop.PawnShop.model.HouseholdGoods;
import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.model.factroies.base.ItemFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HouseholdGoodsFactory implements ItemFactory {
    @Override
    public PawnItem createPawnItem(Map<String, String> formData) {
        return new HouseholdGoods(formData);
    }
}
