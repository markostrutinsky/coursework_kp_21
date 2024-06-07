package PawnShop.PawnShop.response;

import PawnShop.PawnShop.model.Agreement;
import PawnShop.PawnShop.model.PawnItem;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

@Data
@Builder
public class PawnItemResponse {
    private Long id;
    private String name;
    private String category;
    private String photo;
    private AgreementResponse agreement;
}
