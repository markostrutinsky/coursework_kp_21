package PawnShop.PawnShop.model;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class ErrorResponse {

    private int status;

    @Singular
    private List<String> errors;
}
