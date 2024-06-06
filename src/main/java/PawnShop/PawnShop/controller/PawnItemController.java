package PawnShop.PawnShop.controller;

import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.model.PawnItemCategory;
import PawnShop.PawnShop.response.AgreementResponse;
import PawnShop.PawnShop.response.PawnItemDeleteResponse;
import PawnShop.PawnShop.response.PawnItemResponse;
import PawnShop.PawnShop.service.PawnItemService;
import PawnShop.PawnShop.service.mediator.Mediator;
import PawnShop.PawnShop.service.mediator.requests.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/pawnshop", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PawnItemController {
    private final ConversionService conversionService;
    private final Mediator mediator;

    @CrossOrigin
    @GetMapping("/items/by-category/{category}")
    public ResponseEntity<List<? extends PawnItem>> getAllByCategory(@PathVariable("category") PawnItemCategory category) {
        return ResponseEntity.ok(mediator.send(new GetAllItemsByCategoryRequest(category)));
    }

    @CrossOrigin
    @GetMapping("/all-items")
    public ResponseEntity<List<? extends PawnItem>> getAll() {
        return ResponseEntity.ok(mediator.send(new GetAllItemsRequest()));
    }

    @CrossOrigin
    @PostMapping(value = "/add-item", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PawnItemResponse addItem(@RequestBody Map<String, String> formData) {
        PawnItem pawnItem = mediator.send(new AddItemRequest(formData));

        PawnItemResponse pawnItemResponse = conversionService.convert(pawnItem, PawnItemResponse.class);
        AgreementResponse agreementResponse =
                conversionService.convert(pawnItem.getAgreement(), AgreementResponse.class);
        pawnItemResponse.setAgreement(agreementResponse);
        return pawnItemResponse;
    }

    @CrossOrigin
    @GetMapping("/all-items/{userId}")
    public ResponseEntity<List<? extends PawnItem>> getAllByUserId(@PathVariable("userId") long userId) {
        return ResponseEntity.ok(mediator.send(new GetAllItemsByUserRequest(userId)));
    }

    @CrossOrigin
    @DeleteMapping("/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PawnItemDeleteResponse> delete(@PathVariable("itemId") long itemId) {
        PawnItem deletedItem = mediator.send(new DeleteItemRequest(itemId));
        return ResponseEntity.ok(PawnItemDeleteResponse.builder().id(deletedItem.getId()).build());
    }
}
