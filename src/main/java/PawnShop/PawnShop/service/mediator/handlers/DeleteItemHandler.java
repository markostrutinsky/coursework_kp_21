package PawnShop.PawnShop.service.mediator.handlers;

import PawnShop.PawnShop.exception.UserAndProvidedTokenDoesNotMatchException;
import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.model.security.User;
import PawnShop.PawnShop.repository.PawnItemRepository;
import PawnShop.PawnShop.service.UserService;
import PawnShop.PawnShop.service.mediator.Handler;
import PawnShop.PawnShop.service.mediator.requests.DeleteItemRequest;
import PawnShop.PawnShop.service.mediator.requests.GetAllItemsByUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DeleteItemHandler implements Handler<DeleteItemRequest, PawnItem> {

    private final PawnItemRepository itemRepository;

    @Override
    public PawnItem handle(DeleteItemRequest deleteItemRequest) {
        PawnItem deletedItem = itemRepository.getReferenceById(deleteItemRequest.getItemId());
        String persistedUserName = deletedItem.getUser().getUsername();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!Objects.equals(persistedUserName, userDetails.getUsername())){
            throw new UserAndProvidedTokenDoesNotMatchException(
                    "You need to use token provided for the user whose items you are trying to delete");
        }
        itemRepository.deleteById(deleteItemRequest.getItemId());
        return deletedItem;
    }
}
