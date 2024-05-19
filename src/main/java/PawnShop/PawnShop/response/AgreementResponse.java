package PawnShop.PawnShop.response;

import PawnShop.PawnShop.model.Agreement;

public class AgreementResponse {
    private Long id;
    private String email;

    public AgreementResponse(Agreement agreement) {
        this.id = agreement.getId();
        this.email = agreement.getEmail();
    }
}
