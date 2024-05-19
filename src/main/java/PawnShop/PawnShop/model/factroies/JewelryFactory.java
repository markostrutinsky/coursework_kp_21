package PawnShop.PawnShop.model.factroies;

import PawnShop.PawnShop.model.Jewelry;
import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.model.factroies.base.ItemFactory;
import PawnShop.PawnShop.model.factroies.base.ItemFactoryImpl;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JewelryFactory implements ItemFactory{

    @Override
    public PawnItem createPawnItem(Map<String, String> formData) {
        return new Jewelry(formData);
    }
}
