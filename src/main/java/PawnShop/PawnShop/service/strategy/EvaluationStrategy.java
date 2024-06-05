package PawnShop.PawnShop.service.strategy;

import PawnShop.PawnShop.model.PawnItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public interface EvaluationStrategy {
    BigDecimal evaluate(PawnItem pawnItem);
}
