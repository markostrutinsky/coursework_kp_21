package PawnShop.PawnShop.model.builders.base;

import PawnShop.PawnShop.model.builders.AgreementBuilder;

import java.math.BigDecimal;
import java.util.Date;

public interface Builder {
    AgreementBuilder personWithName(String firstName);

    AgreementBuilder bySurname(String lastName);

    AgreementBuilder withEmail(String email);

    AgreementBuilder takesLoanForAmount(BigDecimal amount);

    AgreementBuilder atAnInterestRate(int interestRate);

    AgreementBuilder startsWith(Date startDate);

    AgreementBuilder expires(Date dueDate);

    AgreementBuilder isPaid(boolean isPaid);
}
