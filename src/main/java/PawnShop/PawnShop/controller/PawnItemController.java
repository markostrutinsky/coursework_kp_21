package PawnShop.PawnShop.controller;

import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.model.PawnItemCategory;
import PawnShop.PawnShop.response.AgreementResponse;
import PawnShop.PawnShop.response.PawnItemResponse;
import PawnShop.PawnShop.service.PawnItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/pawnshop", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PawnItemController {
    private final PawnItemService pawnItemService;
    private final ConversionService conversionService;

//    @Autowired
//    public PawnItemController(PawnItemService pawnItemService) {
//        this.pawnItemService = pawnItemService;
//    }

    @GetMapping("/items/by-category/{category}")
    public ResponseEntity<List<? extends PawnItem>> getAllByCategory(@PathVariable("category") PawnItemCategory category) {
        return ResponseEntity.ok(pawnItemService.getAllItemsByCategory(category));
    }

    @GetMapping("/all-items")
    public ResponseEntity<List<? extends PawnItem>> getAll() {
        return ResponseEntity.ok(pawnItemService.findAllItems());
    }

    @PostMapping(value = "/add-item", produces = MediaType.APPLICATION_JSON_VALUE)
    public PawnItemResponse addItem(@RequestBody Map<String, String> formData) throws SQLException, IOException {
        PawnItem pawnItem = pawnItemService.addNewItem(formData);

        PawnItemResponse pawnItemResponse = conversionService.convert(pawnItem, PawnItemResponse.class);
        AgreementResponse agreementResponse =
                conversionService.convert(pawnItem.getAgreement(), AgreementResponse.class);
        pawnItemResponse.setAgreement(agreementResponse);
        return pawnItemResponse;
    }
}
