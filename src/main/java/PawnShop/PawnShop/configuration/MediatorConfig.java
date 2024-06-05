package PawnShop.PawnShop.configuration;

import PawnShop.PawnShop.service.mediator.Mediator;
import PawnShop.PawnShop.service.mediator.MediatorImpl;
import PawnShop.PawnShop.service.mediator.handlers.AddItemHandler;
import PawnShop.PawnShop.service.mediator.handlers.GetAllItemsByCategoryHandler;
import PawnShop.PawnShop.service.mediator.handlers.GetAllItemsHandler;
import PawnShop.PawnShop.service.mediator.requests.AddItemRequest;
import PawnShop.PawnShop.service.mediator.requests.GetAllItemsByCategoryRequest;
import PawnShop.PawnShop.service.mediator.requests.GetAllItemsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class MediatorConfig {

    private final AddItemHandler addItemHandler;
    private final GetAllItemsByCategoryHandler getAllItemsByCategoryHandler;
    private final GetAllItemsHandler getAllItemsHandler;

    @Autowired
    public MediatorConfig(
            AddItemHandler addItemHandler,
            GetAllItemsByCategoryHandler getAllItemsByCategoryHandler,
            GetAllItemsHandler getAllItemsHandler
    ) {
        this.addItemHandler = addItemHandler;
        this.getAllItemsByCategoryHandler = getAllItemsByCategoryHandler;
        this.getAllItemsHandler = getAllItemsHandler;
    }

    @Bean
    public Mediator mediator() {
        MediatorImpl mediator = new MediatorImpl();
        mediator.registerHandler(AddItemRequest.class, addItemHandler);
        mediator.registerHandler(GetAllItemsByCategoryRequest.class, getAllItemsByCategoryHandler);
        mediator.registerHandler(GetAllItemsRequest.class, getAllItemsHandler);
        return mediator;
    }
}
