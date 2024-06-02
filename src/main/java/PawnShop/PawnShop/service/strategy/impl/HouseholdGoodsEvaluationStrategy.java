package PawnShop.PawnShop.service.strategy.impl;

import PawnShop.PawnShop.model.HouseholdGoods;
import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.service.strategy.EvaluationStrategy;

import java.math.BigDecimal;

public class HouseholdGoodsEvaluationStrategy implements EvaluationStrategy {
    @Override
    public BigDecimal evaluate(PawnItem pawnItem) {

        HouseholdGoods householdGoods = (HouseholdGoods)pawnItem;
        BigDecimal evaluation = BigDecimal.ZERO;

        double productTypeFactor = switch (householdGoods.getProductType()) {
            case "appliances" -> 2.5;
            case "furniture" -> 2.2;
            case "kitchenware" -> 1.8;
            case "bedding" -> 1.5;
            case "decorations" -> 1.3;
            case "textiles" -> 1.2;
            default -> 1.0;
        };
        evaluation = evaluation.add(new BigDecimal(productTypeFactor));

        double brandFactor = switch (householdGoods.getBrand()) {
            case "Bosch" -> 2.8;
            case "Siemens" -> 2.5;
            case "LG" -> 2.2;
            case "Samsung" -> 2.0;
            case "Whirlpool" -> 1.8;
            case "Indesit" -> 1.5;
            case "Ariston" -> 1.3;
            default -> 1.0;
        };
        evaluation = evaluation.add(new BigDecimal(brandFactor));

        double materialFactor = switch (householdGoods.getMaterial()) {
            case "titanium" -> 2.5;
            case "stainless steel" -> 2.0;
            case "ceramic" -> 1.9;
            case "glass" -> 1.8;
            case "copper" -> 1.7;
            case "fabric" -> 1.5;
            case "plastic" -> 1.3;
            default -> 1.2;
        };
        evaluation = evaluation.add(new BigDecimal(materialFactor));

        double sizeFactor = 0.1 + (householdGoods.getSize() / 10.0);
        evaluation = evaluation.add(new BigDecimal(sizeFactor));

        double conditionFactor = switch (householdGoods.getCondition()) {
            case "excellent" -> 2.5;
            case "good" -> 2.0;
            case "fair" -> 1.5;
            case "poor" -> 0.5;
            default -> 1.0;
        };
        evaluation = evaluation.add(new BigDecimal(conditionFactor));

        double modelFactor = switch (householdGoods.getModel()) {
            case "latest model" -> 2.2;
            case "previous model" -> 1.8;
            case "older model" -> 1.3;
            case "discontinued model" -> 0.8;
            default -> 1.0;
        };
        evaluation = evaluation.add(new BigDecimal(modelFactor));

        return evaluation.multiply(new BigDecimal(10));
    }
}
