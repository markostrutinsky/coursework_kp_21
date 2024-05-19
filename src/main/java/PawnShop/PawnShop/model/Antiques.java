package PawnShop.PawnShop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Table(name = "antiques")
@PrimaryKeyJoinColumn(name = "product_id")
@NoArgsConstructor
public class Antiques extends PawnItem {
    @NotBlank
    @Column(name = "age")
    private int age;

    @NotBlank
    @Column(name = "originality")
    private String originality; // e.g. "original", "restored", "reproduction"

    @NotBlank
    @Column(name = "condition")
    private String condition;

    @NotBlank
    private String material; // e.g. "wood", "metal", "porcelain"

    @NotBlank
    @Column(name = "maker")
    private String maker;

    @NotBlank
    @Column(name = "provenance")
    private String provenance;

    public Antiques(Map<String, String> formData) {
        super(formData);
        this.age = Integer.parseInt(formData.get("age"));
        this.originality = formData.get("originality");
        this.condition = formData.get("condition");
        this.material = formData.get("material");
        this.maker = formData.get("maker");
        this.provenance = formData.get("provenance");
    }
}
