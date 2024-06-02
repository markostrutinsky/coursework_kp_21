package PawnShop.PawnShop.service.strategy.impl;

import PawnShop.PawnShop.model.Electronics;
import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.service.strategy.EvaluationStrategy;

import java.math.BigDecimal;

public class ElectronicsEvaluationStrategy implements EvaluationStrategy {
    @Override
    public BigDecimal evaluate(PawnItem pawnItem) {
        Electronics electronics = (Electronics) pawnItem;
        BigDecimal evaluation = BigDecimal.ZERO;

        double ageFactor = 1.0 - (electronics.getYear() / 2024.0);
        evaluation = evaluation.add(new BigDecimal(ageFactor));

        // Brand factor ( well-known brands are more valuable )
        double brandFactor = switch (electronics.getBrand()) {
            case "Apple" -> 2.5;
            case "Samsung" -> 2.0;
            case "Google" -> 1.8;
            case "Sony" -> 1.5;
            default -> 1.2;
        };
        evaluation = evaluation.add(new BigDecimal(brandFactor));

        double modelFactor = 1.0;
        if (electronics.getModel().contains("latest")) {
            modelFactor = 2.0;
        } else if (electronics.getModel().contains("new")) {
            modelFactor = 1.8;
        } else if (electronics.getModel().contains("old")) {
            modelFactor = 1.2;
        } else {
            modelFactor = 1.0;
        }
        evaluation = evaluation.add(new BigDecimal(modelFactor));

        // Product type factor ( certain types are more valuable )
        double productTypeFactor = switch (electronics.getProductType()) {
            case "smartphone" -> 3.0;
            case "laptop" -> 2.5;
            case "TV" -> 2.0;
            default -> 1.5;
        };
        evaluation = evaluation.add(new BigDecimal(productTypeFactor));

        double screenSizeFactor = electronics.getScreenSize() / 10.0;
        evaluation = evaluation.add(new BigDecimal(screenSizeFactor));

        double storageCapacityFactor = electronics.getStorageCapacity() / 100.0;
        evaluation = evaluation.add(new BigDecimal(storageCapacityFactor));

        evaluation = evaluation.setScale(2, BigDecimal.ROUND_HALF_UP);

        return evaluation.multiply(new BigDecimal(100));
    }
}
