package PawnShop.PawnShop.converter;

import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.response.PawnItemResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Objects;

@Component
public class PawnItemToPawnItemResponseConverter implements Converter<PawnItem, PawnItemResponse> {

    @Override
    public PawnItemResponse convert(PawnItem source) {
        return PawnItemResponse.builder()
                .id(source.getId())
                .name(source.getPawnItemName())
                .category(source.getCategory().toString())
                .photo(Objects.nonNull(source.getPhoto())
                        ? encodeBlobToBase64(source.getPhoto())
                        : null)
                .build();
    }

    private String encodeBlobToBase64(Blob blob) {
        byte[] bytes;
        try (InputStream inputStream = blob.getBinaryStream()) {
            bytes = inputStream.readAllBytes();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(bytes);
    }
}