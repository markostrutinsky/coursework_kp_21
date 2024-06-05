package PawnShop.PawnShop.service.strategy.impl;

import PawnShop.PawnShop.model.Clothes;
import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.service.strategy.EvaluationStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ClothesEvaluationStrategy implements EvaluationStrategy {
    @Override
    public BigDecimal evaluate(PawnItem pawnItem) {
        Clothes clothes = (Clothes) pawnItem;
        BigDecimal evaluation = BigDecimal.ZERO;

        double sizeFactor = 1.0 + (clothes.getSize() / 10.0);
        evaluation = evaluation.add(new BigDecimal(sizeFactor));

        double brandFactor = switch (clothes.getBrand()) {
            case "nike" -> 3.0 + (Math.random() * 0.5);
            case "adidas" -> 2.5 + (Math.random() * 0.3);
            case "puma" -> 2.0 + (Math.random() * 0.2);
            case "budget" -> 1.5 + (Math.random() * 0.1);
            default -> 4.0 + (Math.random() * 0.5);
        };
        evaluation = evaluation.add(new BigDecimal(brandFactor));

        double clothingTypeFactor = switch (clothes.getClothingType()) {
            case "coat" -> 2.5 + (Math.random() * 0.3);
            case "dress" -> 2.0 + (Math.random() * 0.2);
            case "pants" -> 1.8 + (Math.random() * 0.1);
            case "shirt" -> 1.5 + (Math.random() * 0.1);
            default -> 1.0;
        };
        evaluation = evaluation.add(new BigDecimal(clothingTypeFactor));

        double materialFactor = switch (clothes.getMaterial()) {
            case "silk" -> 3.0 + (Math.random() * 0.5);
            case "wool" -> 2.5 + (Math.random() * 0.3);
            case "cotton" -> 2.0 + (Math.random() * 0.2);
            case "polyester" -> 1.5 + (Math.random() * 0.1);
            default -> 1.0;
        };
        evaluation = evaluation.add(new BigDecimal(materialFactor));

        double conditionFactor = switch (clothes.getCondition()) {
            case "excellent" -> 2.5 + (Math.random() * 0.3);
            case "good" -> 2.0 + (Math.random() * 0.2);
            case "fair" -> 1.5 + (Math.random() * 0.1);
            case "poor" -> 0.5 + (Math.random() * 0.1);
            default -> 1.0;
        };
        evaluation = evaluation.add(new BigDecimal(conditionFactor));

        evaluation = evaluation.setScale(2, BigDecimal.ROUND_HALF_UP);

        return evaluation.multiply(new BigDecimal(10));
    }
}
