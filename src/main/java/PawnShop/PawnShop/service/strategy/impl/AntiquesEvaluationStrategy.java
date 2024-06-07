package PawnShop.PawnShop.service.strategy.impl;

import PawnShop.PawnShop.model.Antiques;
import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.service.strategy.EvaluationStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AntiquesEvaluationStrategy implements EvaluationStrategy {
    @Override
    public BigDecimal evaluate(PawnItem pawnItem) {
        Antiques item = (Antiques) pawnItem;
        BigDecimal evaluation = BigDecimal.ZERO;

        double ageFactor = 0.01 + (item.getAge() / 500.0);
        evaluation = evaluation.add(new BigDecimal(ageFactor));

        double originalityFactor = switch (item.getOriginality()) {
            case "original" -> 2.0;
            case "restored" -> 1.5;
            case "reproduction" -> 0.5;
            default -> 1.0;
        };
        evaluation = evaluation.add(new BigDecimal(originalityFactor));

        double conditionFactor = switch (item.getCondition()) {
            case "excellent" -> 2.5;
            case "good" -> 2.0;
            case "fair" -> 1.5;
            case "poor" -> 0.5;
            default -> 1.0;
        };
        evaluation = evaluation.add(new BigDecimal(conditionFactor));

        double materialFactor = switch (item.getMaterial()) {
            case "gold" -> 5.0 + (Math.random());
            case "silver" -> 3.0 + (Math.random() * 0.5);
            case "porcelain" -> 2.5 + (Math.random() * 0.3);
            case "brass" -> 2.0 + (Math.random() * 0.2);
            case "copper" -> 1.8 + (Math.random() * 0.1);
            case "wood" -> 1.5 + (Math.random() * 0.1);
            case "ivory" -> 4.0 + (Math.random());
            case "jade" -> 6.0 + (Math.random() * 1.5);
            default -> 1.2 + (Math.random() * 0.2);
        };
        evaluation = evaluation.add(new BigDecimal(materialFactor));

        double makerFactor = switch (item.getMaker()) {
            case "renowned artist" -> 5.0 + (Math.random());
            case "well-known maker" -> 3.5 + (Math.random() * 0.5);
            case "established craftsman" -> 3.0 + (Math.random() * 0.3);
            case "local artisan" -> 2.5 + (Math.random() * 0.2);
            case "unknown maker" -> 1.0 + (Math.random() * 0.2);
            default -> 2.0 + (Math.random() * 0.2);
        };
        evaluation = evaluation.add(new BigDecimal(makerFactor));

        double provenanceFactor = switch (item.getProvenance()) {
            case "royal family" -> 6.0 + (Math.random() * 1.5);
            case "historical figure" -> 5.0 + (Math.random());
            case "museum collection" -> 4.0 + (Math.random() * 0.5);
            case "ancient civilization" -> 3.5 + (Math.random() * 0.3);
            case "private collection" -> 3.0 + (Math.random() * 0.2);
            case "family heirloom" -> 2.5 + (Math.random() * 0.1);
            case "unprovenanced" -> 1.0 + (Math.random() * 0.2);
            default -> 1.0 + (Math.random() * 0.2);
        };
        evaluation = evaluation.add(new BigDecimal(provenanceFactor));

        evaluation = evaluation.setScale(2, BigDecimal.ROUND_HALF_UP);

        return evaluation.multiply(new BigDecimal(100));
    }
}
