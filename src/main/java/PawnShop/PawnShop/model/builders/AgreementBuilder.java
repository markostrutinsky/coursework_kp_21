package PawnShop.PawnShop.model.builders;

import PawnShop.PawnShop.model.Agreement;
import PawnShop.PawnShop.model.builders.base.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
public class AgreementBuilder implements Builder {
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal amount;
    private int interestRate;
    private Date startDate;
    private Date dueDate;
    private boolean isPaid;

    public AgreementBuilder personWithName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AgreementBuilder bySurname(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AgreementBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public AgreementBuilder takesLoanForAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public AgreementBuilder atAnInterestRate(int interestRate) {
        this.interestRate = interestRate;
        this.amount = amount.add(amount.divide(new BigDecimal(interestRate), 2));
        return this;
    }

    public AgreementBuilder startsWith(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public AgreementBuilder expires(Date dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public AgreementBuilder isPaid(boolean isPaid) {
        this.isPaid = isPaid;
        return this;
    }
     public Agreement build() {
        return new Agreement(firstName, lastName, email, amount, interestRate, startDate, dueDate, isPaid);
     }
}
