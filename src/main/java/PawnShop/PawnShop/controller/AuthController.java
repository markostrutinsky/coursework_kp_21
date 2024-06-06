package PawnShop.PawnShop.controller;

import PawnShop.PawnShop.dto.LoginResponse;
import PawnShop.PawnShop.dto.RegisterRequestDto;
import PawnShop.PawnShop.dto.RegisterResponseDto;
import PawnShop.PawnShop.model.security.Authority;
import PawnShop.PawnShop.model.security.User;
import PawnShop.PawnShop.dto.LoginRequest;
import PawnShop.PawnShop.security.jwt.JwtService;
import PawnShop.PawnShop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;

    @CrossOrigin
    @PostMapping("/register-user")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponseDto registerUser(@RequestBody RegisterRequestDto registerRequestDto) {
        System.out.println("registration");
        User user = User.builder()
                .email(registerRequestDto.getEmail())
                .username(registerRequestDto.getUsername())
                .password(registerRequestDto.getPassword())
                .authority(Authority.READ_WRITE)
                .build();
        User result = userService.registerUser(user);
        return RegisterResponseDto.builder()
                .id(result.getId())
                .username(result.getUsername())
                .password(result.getPassword())
                .email(result.getEmail())
                .build();
    }

    @CrossOrigin
    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest request) {
        User user = userService.authenticateUser(request.getEmail(), request.getPassword());
        String jwtToken = jwtService.generateToken(user);

        return LoginResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .jwtToken(jwtToken)
                .build();
    }
}