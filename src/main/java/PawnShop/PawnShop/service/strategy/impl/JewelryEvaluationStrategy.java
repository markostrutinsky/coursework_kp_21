package PawnShop.PawnShop.service.strategy.impl;

import PawnShop.PawnShop.model.Jewelry;
import PawnShop.PawnShop.service.strategy.EvaluationStrategy;
import PawnShop.PawnShop.model.PawnItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JewelryEvaluationStrategy implements EvaluationStrategy {
    @Override
    public BigDecimal evaluate(PawnItem pawnItem) {
        Jewelry item = (Jewelry) pawnItem;
        BigDecimal evaluation = BigDecimal.ZERO;

        double metalQualityFactor = 0.5 + (Math.random() * 0.2);
        evaluation = evaluation.add(new BigDecimal(metalQualityFactor * item.getMetalSample()));
        double weightFactor = 0.01 + (Math.random() * 0.005);
        evaluation = evaluation.add(new BigDecimal(weightFactor * item.getWeight()));
        double sizeFactor = 0.005 + (Math.random() * 0.002);
        evaluation = evaluation.add(new BigDecimal(sizeFactor * item.getSize()));
        if (item.isPreciousStones()) {
            double preciousStonesFactor = 0.2 + (Math.random() * 0.1);
            evaluation = evaluation.add(new BigDecimal(preciousStonesFactor));
        }
        double stonesCountFactor = 0.01 + (Math.random() * 0.005);
        evaluation.add(new BigDecimal(stonesCountFactor * item.getStonesCount()));
        double randomSum = 100 + Math.random() * 100;
        evaluation = new BigDecimal(randomSum);

        return evaluation;
    }
}
