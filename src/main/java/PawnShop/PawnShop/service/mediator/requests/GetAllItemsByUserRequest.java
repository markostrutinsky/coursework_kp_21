package PawnShop.PawnShop.service.mediator.requests;

import PawnShop.PawnShop.model.PawnItemCategory;
import PawnShop.PawnShop.model.security.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetAllItemsByUserRequest {

    private final Long userId;
}
