package PawnShop.PawnShop.service.strategy.impl;


import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.model.SportEquipment;
import PawnShop.PawnShop.service.strategy.EvaluationStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SportsEquipmentEvaluationStrategy implements EvaluationStrategy {
    @Override
    public BigDecimal evaluate(PawnItem pawnItem) {
        SportEquipment item = (SportEquipment) pawnItem;

        BigDecimal evaluation = BigDecimal.ZERO;

        System.out.println(pawnItem.toString());
        double sportTypeFactor = switch (item.getSportType()) {
            case "soccer" -> 1.2;
            case "basketball" -> 1.1;
            case "tennis" -> 1.0;
            case "baseball" -> 1.1;
            case "football" -> 1.2;
            case "golf" -> 1.0;
            case "hockey" -> 1.1;
            case "lacrosse" -> 1.0;
            case "skating" -> 1.0;
            case "swimming" -> 1.0;
            case "track and field" -> 1.0;
            case "volleyball" -> 1.0;
            case "wrestling" -> 1.0;
            default -> 0.9;
        };
        evaluation = evaluation.add(new BigDecimal(sportTypeFactor));

        double brandFactor = switch (item.getBrand()) {
            case "Nike" -> 1.5;
            case "Adidas" -> 1.3;
            case "Reebok" -> 1.2;
            case "Under Armour" -> 1.2;
            case "New Balance" -> 1.1;
            case "ASICS" -> 1.1;
            case "Brooks" -> 1.0;
            case "Saucony" -> 1.0;
            default -> 0.9;
        };
        evaluation = evaluation.add(new BigDecimal(brandFactor));

        double equipmentTypeFactor = switch (item.getEquipmentType()) {
            case "ball" -> 0.8;
            case "shoes" -> 1.2;
            case "racket" -> 1.5;
            case "bat" -> 1.2;
            case "club" -> 1.2;
            case "stick" -> 1.1;
            case "glove" -> 1.0;
            case "helmet" -> 1.0;
            default -> 0.9;
        };
        evaluation = evaluation.add(new BigDecimal(equipmentTypeFactor));

        double sizeFactor;
        if (item.getSize() < 5) {
            sizeFactor = 0.7;
        } else if (item.getSize() >= 5 && item.getSize() < 10) {
            sizeFactor = 0.9;
        } else if (item.getSize() >= 10 && item.getSize() < 15) {
            sizeFactor = 1.1;
        } else {
            sizeFactor = 1.3;
        }
        evaluation = evaluation.add(new BigDecimal(sizeFactor));

        double conditionFactor = switch (item.getCondition()) {
            case "excellent" -> 1.5;
            case "good" -> 1.2;
            case "fair" -> 0.9;
            case "poor" -> 0.5;
            default -> 1.0;
        };
        evaluation = evaluation.add(new BigDecimal(conditionFactor));

        double modelFactor;
        if (item.getModel().contains("pro")) {
            modelFactor = 1.2;
        } else if (item.getModel().contains("elite")) {
            modelFactor = 1.1;
        } else if (item.getModel().contains("advanced")) {
            modelFactor = 1.0;
        } else if (item.getModel().contains("standard")) {
            modelFactor = 0.9;
        } else {
            modelFactor = 0.8;
        }
        evaluation = evaluation.add(new BigDecimal(modelFactor));

        return evaluation.multiply(new BigDecimal(100));
    }
}

