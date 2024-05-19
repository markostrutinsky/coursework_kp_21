package PawnShop.PawnShop.service.impl;

import PawnShop.PawnShop.model.Agreement;
import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.model.PawnItemCategory;
import PawnShop.PawnShop.model.builders.AgreementBuilder;
import PawnShop.PawnShop.model.builders.Director;
import PawnShop.PawnShop.model.builders.base.Builder;
import PawnShop.PawnShop.model.factroies.base.ItemFactoryImpl;
import PawnShop.PawnShop.repository.PawnItemRepository;
import PawnShop.PawnShop.service.PawnItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PawnItemServiceImpl implements PawnItemService {
    private PawnItemRepository itemRepository;

    private final ItemFactoryImpl factory;

    @Autowired
    public PawnItemServiceImpl(PawnItemRepository itemRepository, ItemFactoryImpl factory) {
        this.itemRepository = itemRepository;
        this.factory = factory;
    }

    @Override
    public PawnItem createItem(Map<String, String> formData) {
        return factory.createPawnItem(formData);
    }

    @Override
    public PawnItem addNewItem(Map<String, String> formData) throws SQLException {
        PawnItem item = createItem(formData);
        item.setCategory(PawnItemCategory.convertStringToPawnItemCategory(formData.get("category")));
        if (!formData.get("photo").isEmpty()){
            byte[] photoBytes = Base64.getDecoder().decode(formData.get("photo"));
            Blob photoBlob = new SerialBlob(photoBytes);
            item.setPhoto(photoBlob);
        }
        Agreement agreement = createAgreement(formData);
        item.setAgreement(agreement);

        return itemRepository.save(item);
    }

    @Override
    public List<? extends PawnItem> getAllItemsByCategory(PawnItemCategory category) {
        return itemRepository.findByCategory(category);
    }

    @Override
    public List<? extends PawnItem> findAllItems() {
        return itemRepository.findAll();
    }

    private Agreement createAgreement(Map<String, String> formData) {
        Builder builder = new AgreementBuilder();
        Director director = new Director();
        return director.createAgreement(builder, formData);
    }
}
