package PawnShop.PawnShop.service.impl;

import PawnShop.PawnShop.model.Agreement;
import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.model.PawnItemCategory;
import PawnShop.PawnShop.model.builders.AgreementBuilder;
import PawnShop.PawnShop.model.builders.Director;
import PawnShop.PawnShop.model.builders.base.Builder;
import PawnShop.PawnShop.model.factroies.base.ItemFactoryImpl;
import PawnShop.PawnShop.model.security.User;
import PawnShop.PawnShop.repository.PawnItemRepository;
import PawnShop.PawnShop.service.ExpectedItemFields;
import PawnShop.PawnShop.service.PawnItemService;
import PawnShop.PawnShop.service.observer.NotificationService;
import PawnShop.PawnShop.service.strategy.EvaluationStrategy;
import PawnShop.PawnShop.service.strategy.impl.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;

import static PawnShop.PawnShop.service.ExpectedItemFields.PHOTO;
import static PawnShop.PawnShop.service.ExpectedItemFields.CATEGORY;


@Service
@RequiredArgsConstructor
public class PawnItemServiceImpl implements PawnItemService {
    private final static Map<PawnItemCategory, EvaluationStrategy> EVALUATION_STRATEGY_MAP = getEvaluationStrategyMap();
    private  PawnItemRepository itemRepository;
    private final ItemFactoryImpl factory;

    private final NotificationService notificationService;

    @Autowired
    public PawnItemServiceImpl(PawnItemRepository itemRepository, ItemFactoryImpl factory, NotificationService notificationService) {
        this.itemRepository = itemRepository;
        this.factory = factory;
        this.notificationService = notificationService;
    }

    @Override
    public PawnItem createItem(Map<String, String> formData) {
        return factory.createPawnItem(formData);
    }

    @Override
    public PawnItem addNewItem(Map<String, String> formData) throws SQLException {
        PawnItem item = createItem(formData);
        item.setCategory(PawnItemCategory.convertStringToPawnItemCategory(formData.get(CATEGORY.getDeclaredName())));
        if (Objects.nonNull(formData.get(PHOTO.getDeclaredName())) && !formData.get(PHOTO.getDeclaredName()).isBlank()){
            byte[] photoBytes = Base64.getDecoder().decode(formData.get(PHOTO.getDeclaredName()));
            Blob photoBlob = new SerialBlob(photoBytes);
            item.setPhoto(photoBlob);
        }
        Agreement agreement = createAgreement(formData, item);

        item.setAgreement(agreement);
        PawnItem result = itemRepository.save(item);
        notificationService.notify("New item", "User posted a new to catalog");
        return result;
    }

    @Override
    public List<? extends PawnItem> getAllItemsByCategory(PawnItemCategory category) {
        return itemRepository.findByCategory(category);
    }

    @Override
    public List<? extends PawnItem> findAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public List<? extends PawnItem> findAllItemsByUser(User user) {
        return itemRepository.findAllByUser(user);
    }

    private Agreement createAgreement(Map<String, String> formData, PawnItem pawnItem) {
        EvaluationStrategy evaluationStrategy = EVALUATION_STRATEGY_MAP.get(pawnItem.getCategory());
        Builder builder = new AgreementBuilder();
        Director director = new Director();
        BigDecimal evaluatedValue = evaluationStrategy.evaluate(pawnItem);
        return director.createAgreement(builder, formData, evaluatedValue);
    }

    private static Map<PawnItemCategory, EvaluationStrategy> getEvaluationStrategyMap() {
        Map<PawnItemCategory, EvaluationStrategy> evaluationStrategyMap = new HashMap<>();
        evaluationStrategyMap.put(PawnItemCategory.JEWELRY, new JewelryEvaluationStrategy());
        evaluationStrategyMap.put(PawnItemCategory.ANTIQUES, new AntiquesEvaluationStrategy());
        evaluationStrategyMap.put(PawnItemCategory.FURNITURE, new FurnitureEvaluationStrategy());
        evaluationStrategyMap.put(PawnItemCategory.CLOTHES, new ClothesEvaluationStrategy());
        evaluationStrategyMap.put(PawnItemCategory.ELECTRONICS, new ElectronicsEvaluationStrategy());
        evaluationStrategyMap.put(PawnItemCategory.HOUSEHOLDGOODS, new HouseholdGoodsEvaluationStrategy());
        evaluationStrategyMap.put(PawnItemCategory.MUSICALINSTRUMENTS, new MusicalInstrumentEvaluationStrategy());
        evaluationStrategyMap.put(PawnItemCategory.SPORTSEQUIPMENT, new SportsEquipmentEvaluationStrategy());
        return evaluationStrategyMap;
    }
}
