package PawnShop.PawnShop.model.builders;

import PawnShop.PawnShop.model.Agreement;
import PawnShop.PawnShop.model.builders.base.Builder;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

public class Director {
    public Agreement createAgreement(Builder builder, Map<String, String> formData,
                                     BigDecimal evaluatedValue) {
        return builder.personWithName(formData.get("firstName"))
                .bySurname(formData.get("lastName"))
                .withEmail(formData.get("email"))
                .takesLoanForAmount(evaluatedValue)
                .atAnInterestRate(Integer.parseInt(formData.get("interestRate")))
                .startsWith(new Date())
                .expires(Date.from(Instant.now().plus(Duration.ofDays(30))))
                .isPaid(false)
                .build();
    }
}
