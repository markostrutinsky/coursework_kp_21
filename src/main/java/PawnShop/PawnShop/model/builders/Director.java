package PawnShop.PawnShop.model.builders;

import PawnShop.PawnShop.model.Agreement;
import PawnShop.PawnShop.model.builders.base.Builder;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

public class Director {
    public Agreement createAgreement(Builder builder, Map<String, String> formData) {
        String loanAmount  = formData.getOrDefault("loanAmount",  "0");
        String interestRate = formData.getOrDefault("interestRate", "1");
        return builder
                .personWithName(formData.getOrDefault("firstName", ""))
                .bySurname(formData.getOrDefault("lastName", ""))
                .withEmail(formData.getOrDefault("email", ""))
                .takesLoanForAmount(new BigDecimal(loanAmount.isEmpty() ? "0" : loanAmount))
                .atAnInterestRate(Integer.parseInt(interestRate.isEmpty() ? "1" : interestRate))
                .startsWith(new Date())
                .expires(Date.from(Instant.now().plus(Duration.ofDays(30))))
                .isPaid(false)
                .build();
    }
}
