package PawnShop.PawnShop.service.strategy;

import PawnShop.PawnShop.model.PawnItem;

import java.math.BigDecimal;

public interface EvaluationStrategy {
    BigDecimal evaluate(PawnItem pawnItem);
}
