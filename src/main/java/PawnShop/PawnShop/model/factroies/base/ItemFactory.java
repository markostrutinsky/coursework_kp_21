package PawnShop.PawnShop.model.factroies.base;

import PawnShop.PawnShop.model.PawnItem;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface ItemFactory {
    PawnItem createPawnItem(Map<String, String> formData);
}
