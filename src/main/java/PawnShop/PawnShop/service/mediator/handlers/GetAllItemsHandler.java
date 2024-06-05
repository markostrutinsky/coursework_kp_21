package PawnShop.PawnShop.service.mediator.handlers;

import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.repository.PawnItemRepository;
import PawnShop.PawnShop.service.mediator.Handler;
import PawnShop.PawnShop.service.mediator.requests.GetAllItemsRequest;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllItemsHandler implements Handler<GetAllItemsRequest, List<? extends PawnItem>> {

    private final PawnItemRepository itemRepository;

    @Autowired
    public GetAllItemsHandler(PawnItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<? extends PawnItem> handle(GetAllItemsRequest getAllItemsRequest) {
        return itemRepository.findAll();
    }
}
