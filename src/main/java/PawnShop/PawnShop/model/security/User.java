package PawnShop.PawnShop.model.security;

import PawnShop.PawnShop.model.Agreement;
import PawnShop.PawnShop.model.PawnItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @Enumerated
    private Authority authority;

    @OneToMany
    private List<PawnItem> pawnItems;
}
