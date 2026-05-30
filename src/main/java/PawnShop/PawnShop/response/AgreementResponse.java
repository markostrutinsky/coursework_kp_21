package PawnShop.PawnShop.response;

import PawnShop.PawnShop.model.Agreement;
import lombok.Getter;

@Getter
public class AgreementResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public AgreementResponse(Agreement agreement) {
        if (agreement != null) {
            this.id = agreement.getId();
            this.firstName = agreement.getFirstName();
            this.lastName = agreement.getLastName();
            this.email = agreement.getEmail();
        }
    }
}
