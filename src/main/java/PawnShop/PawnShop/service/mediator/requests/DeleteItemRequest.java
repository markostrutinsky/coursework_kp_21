package PawnShop.PawnShop.service.mediator.requests;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteItemRequest {

    private final Long itemId;
}
