package PawnShop.PawnShop.service.mediator.requests;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
public class AddItemRequest {
    private final Map<String, String> formData;

    public AddItemRequest(final Map<String, String> formData) {
        this.formData = formData;
    }
}
