package PawnShop.PawnShop.service;

import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.model.PawnItemCategory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PawnItemService {
    PawnItem createItem(Map<String, String> formData);
    PawnItem addNewItem(Map<String, String> fromData) throws IOException, SQLException;
    List<? extends PawnItem> getAllItemsByCategory(PawnItemCategory category);
    List<? extends PawnItem> findAllItems();
}
