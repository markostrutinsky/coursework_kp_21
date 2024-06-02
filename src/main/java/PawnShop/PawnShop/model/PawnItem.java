package PawnShop.PawnShop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.util.Map;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "items")
@Data
@NoArgsConstructor
public class PawnItem {
    @Id
    @GeneratedValue()
    @Column(name = "product_id")
    private Long id;

    @Column(name = "name"/*, nullable = false*/)
    private String pawnItemName;

    @Enumerated(EnumType.STRING)
    @Column(name = "type"/*, nullable = false*/)
    private PawnItemCategory category;

    @Lob
    @Column(name = "photo"/*, nullable = false*/)
    private Blob photo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agreement_id")
    private Agreement agreement;

    public PawnItem(Map<String, String> fromData) {
        this.pawnItemName = fromData.get("name");
        this.category = PawnItemCategory.valueOf(fromData.get("category"));
    }
}
