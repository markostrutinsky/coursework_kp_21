package PawnShop.PawnShop.configuration;

import PawnShop.PawnShop.service.mediator.Mediator;
import PawnShop.PawnShop.service.mediator.MediatorImpl;
import PawnShop.PawnShop.service.mediator.handlers.*;
import PawnShop.PawnShop.service.mediator.requests.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class MediatorConfig {

    private final AddItemHandler addItemHandler;
    private final GetAllItemsByCategoryHandler getAllItemsByCategoryHandler;
    private final GetAllItemsHandler getAllItemsHandler;
    private final GetAllItemsByUserHandler getAllItemsByUserHandler;
    private final DeleteItemHandler deleteItemHandler;

    @Autowired
    public MediatorConfig(
            AddItemHandler addItemHandler,
            GetAllItemsByCategoryHandler getAllItemsByCategoryHandler,
            GetAllItemsHandler getAllItemsHandler,
            GetAllItemsByUserHandler getAllItemsByUserHandler,
            DeleteItemHandler deleteItemHandler
    ) {
        this.addItemHandler = addItemHandler;
        this.getAllItemsByCategoryHandler = getAllItemsByCategoryHandler;
        this.getAllItemsHandler = getAllItemsHandler;
        this.getAllItemsByUserHandler = getAllItemsByUserHandler;
        this.deleteItemHandler = deleteItemHandler;
    }

    @Bean
    public Mediator mediator() {
        MediatorImpl mediator = new MediatorImpl();
        mediator.registerHandler(AddItemRequest.class, addItemHandler);
        mediator.registerHandler(GetAllItemsByCategoryRequest.class, getAllItemsByCategoryHandler);
        mediator.registerHandler(GetAllItemsRequest.class, getAllItemsHandler);
        mediator.registerHandler(GetAllItemsByUserRequest.class, getAllItemsByUserHandler);
        mediator.registerHandler(DeleteItemRequest.class, deleteItemHandler);
        return mediator;
    }
}
