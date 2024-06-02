package PawnShop.PawnShop.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExpectedItemFields {
    PHOTO("photo"),
    CATEGORY("category");

    private final String declaredName;
}
