package PawnShop.PawnShop.model;

public enum PawnItemCategory {
    JEWELRY,
    ANTIQUES,
    FURNITURE,
    ELECTRONICS,
    SPORTSEQUIPMENT,
    CLOTHES,
    MUSICALINSTRUMENTS,
    HOUSEHOLDGOODS;

    public static PawnItemCategory convertStringToPawnItemCategory(String categoryString) {
        return PawnItemCategory.valueOf(categoryString.toUpperCase());
    }
}
