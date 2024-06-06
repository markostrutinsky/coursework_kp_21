package PawnShop.PawnShop.service.mediator.handlers;

import PawnShop.PawnShop.exception.UserAndProvidedTokenDoesNotMatchException;
import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.model.security.User;
import PawnShop.PawnShop.repository.PawnItemRepository;
import PawnShop.PawnShop.service.UserService;
import PawnShop.PawnShop.service.mediator.Handler;
import PawnShop.PawnShop.service.mediator.requests.GetAllItemsByUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class GetAllItemsByUserHandler implements Handler<GetAllItemsByUserRequest, List<? extends PawnItem>> {

    private final PawnItemRepository itemRepository;
    private final UserService userService;

    @Override
    public List<? extends PawnItem> handle(GetAllItemsByUserRequest getAllItemsByUserRequest) {
        User user = userService.getById(getAllItemsByUserRequest.getUserId());
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!Objects.equals(user.getUsername(), userDetails.getUsername())){
            throw new UserAndProvidedTokenDoesNotMatchException(
                    "You need to use token provided for the user whose items you are trying to access");
        }

        return itemRepository.findAllByUser(user);
    }
}
