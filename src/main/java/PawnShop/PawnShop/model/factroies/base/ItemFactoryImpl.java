package PawnShop.PawnShop.model.factroies.base;

import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.model.PawnItemCategory;
import PawnShop.PawnShop.model.factroies.*;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class ItemFactoryImpl implements ItemFactory {
    private final Map<PawnItemCategory, Supplier<ItemFactory>> itemCreators;

    public ItemFactoryImpl() {
        itemCreators = new EnumMap<>(PawnItemCategory.class);
        itemCreators.put(PawnItemCategory.JEWELRY, JewelryFactory::new);
        itemCreators.put(PawnItemCategory.ANTIQUES, AntiquesFactory::new);
        itemCreators.put(PawnItemCategory.ELECTRONICS, ElectronicsFactory::new);
        itemCreators.put(PawnItemCategory.CLOTHES, ClothesFactory::new);
        itemCreators.put(PawnItemCategory.FURNITURE, FurnitureFactory::new);
        itemCreators.put(PawnItemCategory.SPORTSEQUIPMENT, SportEquipmentFactory::new);
        itemCreators.put(PawnItemCategory.HOUSEHOLDGOODS, HouseholdGoodsFactory::new);
        itemCreators.put(PawnItemCategory.MUSICALINSTRUMENTS, MusicalInstrumentFactory::new);
    }

    @Override
    public PawnItem createPawnItem(Map<String, String> formData) {
        return itemCreators.get(PawnItemCategory.convertStringToPawnItemCategory(formData.get("category"))).get().createPawnItem(formData);
    }
}
