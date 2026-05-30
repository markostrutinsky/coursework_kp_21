package PawnShop.PawnShop.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Table(name = "antiques")
@PrimaryKeyJoinColumn(name = "product_id")
@NoArgsConstructor
public class Antiques extends PawnItem {
    @Column(name = "age")
    private int age;

    @Column(name = "originality")
    private String originality; // e.g. "original", "restored", "reproduction"

    @Column(name = "condition")
    private String condition;

    private String material; // e.g. "wood", "metal", "porcelain"

    @Column(name = "maker")
    private String maker;

    @Column(name = "provenance")
    private String provenance;

    public Antiques(Map<String, String> formData) {
        super(formData);
        this.age = Integer.parseInt(formData.getOrDefault("age", "0"));
        this.originality = formData.getOrDefault("originality", "");
        this.condition = formData.getOrDefault("condition", "");
        this.material = formData.getOrDefault("material", "");
        this.maker = formData.getOrDefault("maker", "");
        this.provenance = formData.getOrDefault("provenance", "");
    }
}
