package PawnShop.PawnShop.controller;

import PawnShop.PawnShop.dto.AuthResponse;
import PawnShop.PawnShop.dto.RegisterRequestDto;
import PawnShop.PawnShop.dto.RegisterResponseDto;
import PawnShop.PawnShop.model.security.Authority;
import PawnShop.PawnShop.model.security.User;
import PawnShop.PawnShop.dto.LoginRequest;
import PawnShop.PawnShop.security.jwt.JwtService;
import PawnShop.PawnShop.service.UserService;
import PawnShop.PawnShop.validation.Registrar;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final Registrar registrar;


    @CrossOrigin
    @PostMapping("/register-user")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse registerUser(@RequestBody RegisterRequestDto registerRequestDto) {
        System.out.println("registration");
        User user = User.builder()
                .email(registerRequestDto.getEmail())
                .username(registerRequestDto.getUsername())
                .password(registerRequestDto.getPassword())
                .authority(Authority.READ_WRITE)
                .build();
        User result = registrar.registerUser(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .id(result.getId())
                .username(result.getUsername())
                .email(result.getEmail())
                .jwtToken(jwtToken)
                .build();
    }

    @CrossOrigin
    @PostMapping("/login")
    public AuthResponse authenticateUser(@Valid @RequestBody LoginRequest request) {
        User user = userService.authenticateUser(request.getEmail(), request.getPassword());
        String jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .jwtToken(jwtToken)
                .build();
    }
}