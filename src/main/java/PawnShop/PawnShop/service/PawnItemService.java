package PawnShop.PawnShop.service;

import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.model.PawnItemCategory;

import java.util.List;
import java.util.Map;

public interface PawnItemService {
    PawnItem createItem(Map<String, String> formData);
    PawnItem addNewItem(Map<String, String> fromData);
    List<? extends PawnItem> getAllItemsByCategory(PawnItemCategory category);
    List<? extends PawnItem> findAllItems();
}
