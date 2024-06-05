package PawnShop.PawnShop.service.mediator.requests;

import PawnShop.PawnShop.model.PawnItemCategory;
import lombok.Getter;

@Getter
public class GetAllItemsByCategoryRequest {
    private final PawnItemCategory category;

    public GetAllItemsByCategoryRequest(final PawnItemCategory category) {
        this.category = category;
    }
}
