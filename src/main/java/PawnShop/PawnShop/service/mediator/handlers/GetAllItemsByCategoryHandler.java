package PawnShop.PawnShop.service.mediator.handlers;

import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.repository.PawnItemRepository;
import PawnShop.PawnShop.service.PawnItemService;
import PawnShop.PawnShop.service.mediator.Handler;
import PawnShop.PawnShop.service.mediator.requests.GetAllItemsByCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class GetAllItemsByCategoryHandler implements Handler<GetAllItemsByCategoryRequest, List<? extends PawnItem>> {
    private final PawnItemRepository itemRepository;

    @Autowired
    public GetAllItemsByCategoryHandler(PawnItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<? extends PawnItem> handle(GetAllItemsByCategoryRequest getAllItemsByCategoryRequest) {
        return itemRepository.findByCategory(getAllItemsByCategoryRequest.getCategory());
    }
}
