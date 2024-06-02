package PawnShop.PawnShop.service.strategy.impl;

import PawnShop.PawnShop.model.Furniture;
import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.service.strategy.EvaluationStrategy;

import java.math.BigDecimal;

public class FurnitureEvaluationStrategy implements EvaluationStrategy {
    @Override
    public BigDecimal evaluate(PawnItem pawnItem) {
        Furniture item = (Furniture) pawnItem;
        BigDecimal evaluation = BigDecimal.ZERO;

        double materialFactor = switch (item.getMaterial()) {
            case "wood" -> 2.0 + (Math.random() * 0.5);
            case "metal" -> 1.8 + (Math.random() * 0.3);
            case "fabric" -> 1.5 + (Math.random() * 0.2);
            default -> 1.2 + (Math.random() * 0.2);
        };
        evaluation = evaluation.add(new BigDecimal(materialFactor));

        double styleFactor = switch (item.getStyle()) {
            case "modern" -> 2.5 + (Math.random() * 0.5);
            case "traditional" -> 2.0 + (Math.random() * 0.3);
            case "industrial" -> 1.8 + (Math.random() * 0.2);
            default -> 1.5 + (Math.random() * 0.2);
        };
        evaluation = evaluation.add(new BigDecimal(styleFactor));

        // Size factor
        double sizeFactor = (item.getWidth() * item.getHeight() * item.getDepth()) / 1000.0;
        evaluation = evaluation.add(new BigDecimal(sizeFactor));

        // Age factor
        double ageFactor = 0.01 + (item.getAge() / 500.0);
        evaluation = evaluation.add(new BigDecimal(ageFactor));

        // Brand factor
        double brandFactor = switch (item.getBrand()) {
            case "high-end brand" -> 3.0 + (Math.random() * 0.5);
            case "mid-range brand" -> 2.0 + (Math.random() * 0.3);
            case "budget brand" -> 1.5 + (Math.random() * 0.2);
            default -> 1.0 + (Math.random() * 0.2);
        };
        evaluation = evaluation.add(new BigDecimal(brandFactor));

        double conditionFactor = switch (item.getCondition()) {
            case "excellent" -> 2.5;
            case "good" -> 2.0;
            case "fair" -> 1.5;
            case "poor" -> 0.5;
            default -> 1.0;
        };
        evaluation = evaluation.add(new BigDecimal(conditionFactor));

        evaluation = evaluation.setScale(2, BigDecimal.ROUND_HALF_UP);

        return evaluation.multiply(new BigDecimal(100));
    }
}
