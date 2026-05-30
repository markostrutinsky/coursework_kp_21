package PawnShop.PawnShop.response;

import PawnShop.PawnShop.model.PawnItem;
import lombok.Getter;

@Getter
public class PawnItemResponse {
    private Long id;
    private String name;
    private String category;
    private String photo;
    private AgreementResponse agreement;

    public PawnItemResponse(PawnItem pawnItem) {
        this.id = pawnItem.getId();
        this.name = pawnItem.getPawnItemName();
        this.category = pawnItem.getCategory().name();
        this.photo = pawnItem.getPhoto();
        this.agreement = new AgreementResponse(pawnItem.getAgreement());
    }
}
