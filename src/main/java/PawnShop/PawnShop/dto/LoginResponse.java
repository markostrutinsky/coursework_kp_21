package PawnShop.PawnShop.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private String username;
    private String email;
    private String jwtToken;
}
