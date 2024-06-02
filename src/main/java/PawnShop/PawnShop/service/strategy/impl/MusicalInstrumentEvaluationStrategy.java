package PawnShop.PawnShop.service.strategy.impl;

import PawnShop.PawnShop.model.MusicalInstrument;
import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.service.strategy.EvaluationStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MusicalInstrumentEvaluationStrategy implements EvaluationStrategy {
    @Override
    public BigDecimal evaluate(PawnItem pawnItem) {
        MusicalInstrument instrument = (MusicalInstrument)pawnItem;
        BigDecimal evaluation = BigDecimal.ZERO;

        double instrumentTypeFactor = switch (instrument.getInstrumentType()) {
            case "piano" -> 3.0;
            case "guitar" -> 2.5;
            case "violin" -> 2.0;
            case "drums" -> 1.8;
            case "wind instrument" -> 1.5;
            default -> 1.0;
        };
        evaluation = evaluation.add(new BigDecimal(instrumentTypeFactor));

        double brandFactor = switch (instrument.getBrand()) {
            case "Steinway" -> 2.8;
            case "Yamaha" -> 2.2;
            case "Fender" -> 2.0;
            case "Gibson" -> 1.9;
            case "Taylor" -> 1.8;
            case "Ibanez" -> 1.6;
            default -> 1.2;
        };
        evaluation = evaluation.add(new BigDecimal(brandFactor));

        double conditionFactor = switch (instrument.getCondition()) {
            case "excellent" -> 2.5;
            case "good" -> 2.0;
            case "fair" -> 1.5;
            case "poor" -> 0.5;
            default -> 1.0;
        };
        evaluation = evaluation.add(new BigDecimal(conditionFactor));

        double ageFactor = 0.01 + (instrument.getAge() / 100.0);
        evaluation = evaluation.add(new BigDecimal(ageFactor));

        double modelFactor = switch (instrument.getModel()) {
            case "professional model" -> 2.0;
            case "semi-professional model" -> 1.8;
            case "student model" -> 1.5;
            case "entry-level model" -> 1.2;
            default -> 1.0;
        };
        evaluation = evaluation.add(new BigDecimal(modelFactor));

        return evaluation.multiply(new BigDecimal(100));
    }
}
