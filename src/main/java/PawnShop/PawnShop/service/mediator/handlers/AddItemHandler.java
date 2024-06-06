package PawnShop.PawnShop.service.mediator.handlers;

import PawnShop.PawnShop.model.Agreement;
import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.model.PawnItemCategory;
import PawnShop.PawnShop.model.builders.AgreementBuilder;
import PawnShop.PawnShop.model.builders.Director;
import PawnShop.PawnShop.model.builders.base.Builder;
import PawnShop.PawnShop.model.factroies.base.ItemFactoryImpl;
import PawnShop.PawnShop.model.security.User;
import PawnShop.PawnShop.repository.PawnItemRepository;
import PawnShop.PawnShop.service.UserService;
import PawnShop.PawnShop.service.mediator.Handler;
import PawnShop.PawnShop.service.mediator.requests.AddItemRequest;
import PawnShop.PawnShop.service.observer.NotificationService;
import PawnShop.PawnShop.service.strategy.EvaluationStrategy;
import PawnShop.PawnShop.service.strategy.impl.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.sql.rowset.serial.SerialBlob;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static PawnShop.PawnShop.service.ExpectedItemFields.CATEGORY;
import static PawnShop.PawnShop.service.ExpectedItemFields.PHOTO;

@Component
@RequiredArgsConstructor
public class AddItemHandler implements Handler<AddItemRequest, PawnItem> {

    private final static Map<PawnItemCategory, EvaluationStrategy> EVALUATION_STRATEGY_MAP = getEvaluationStrategyMap();
    private final PawnItemRepository itemRepository;
    private final ItemFactoryImpl factory;
    private final NotificationService notificationService;
    private final UserService userService;

    @Override
    public PawnItem handle(AddItemRequest addItemRequest) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getByUsername(userDetails.getUsername());
        var formData = addItemRequest.getFormData();
        PawnItem item = createItem(formData);
        item.setCategory(PawnItemCategory.convertStringToPawnItemCategory(formData.get(CATEGORY.getDeclaredName())));
        if (Objects.nonNull(formData.get(PHOTO.getDeclaredName())) && !formData.get(PHOTO.getDeclaredName()).isBlank()){
            byte[] photoBytes = Base64.getDecoder().decode(formData.get(PHOTO.getDeclaredName()));
            Blob photoBlob = null;
            try {
                photoBlob = new SerialBlob(photoBytes);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            item.setPhoto(photoBlob);
        }
        Agreement agreement = createAgreement(formData, item);

        item.setAgreement(agreement);
        item.setUser(user);
        PawnItem result = itemRepository.save(item);
        notificationService.notify("New item", "User posted a new to catalog");
        return result;
    }

    public PawnItem createItem(Map<String, String> formData) {
        return factory.createPawnItem(formData);
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
