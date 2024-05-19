package PawnShop.PawnShop.response;

import PawnShop.PawnShop.model.Agreement;
import PawnShop.PawnShop.model.PawnItem;
import jakarta.persistence.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class PawnItemResponse {
    private Long id;
    private String name;
    private String category;
    private String photo;
    private AgreementResponse agreement;

    public PawnItemResponse(PawnItem pawnItem) throws IOException, SQLException {
        this.id = pawnItem.getId();
        this.name = pawnItem.getPawnItemName();
        this.category = pawnItem.getCategory().name();
        this.photo = encodeBlobToBase64(pawnItem.getPhoto());
        this.agreement = new AgreementResponse(pawnItem.getAgreement());
    }

    private String encodeBlobToBase64(Blob blob) throws IOException, SQLException {
        InputStream inputStream = blob.getBinaryStream();
        byte[] bytes = inputStream.readAllBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }
}
