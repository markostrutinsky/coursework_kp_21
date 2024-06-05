package PawnShop.PawnShop.model.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Authority {

    READ("read"),
    READ_WRITE("read_write"),
    ADMIN("admin");

    private final String granted;
}
