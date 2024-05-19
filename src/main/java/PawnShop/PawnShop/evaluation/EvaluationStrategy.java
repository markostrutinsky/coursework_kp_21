package PawnShop.PawnShop.evaluation;

import PawnShop.PawnShop.model.PawnItem;

import java.math.BigDecimal;

public interface EvaluationStrategy {
    BigDecimal evaluate(PawnItem pawnItem);
}
