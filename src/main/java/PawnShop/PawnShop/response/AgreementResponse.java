package PawnShop.PawnShop.response;

import PawnShop.PawnShop.model.Agreement;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgreementResponse {

    private Long id;
    private String email;
}
