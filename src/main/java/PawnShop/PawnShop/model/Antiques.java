package PawnShop.PawnShop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "antiques")
@PrimaryKeyJoinColumn(name = "product_id")
@NoArgsConstructor
@Data
public class Antiques extends PawnItem {
    @Column(name = "age")
    private int age;

    @Column(name = "originality")
    private String originality; // e.g. "original", "restored", "reproduction"

    @Column(name = "condition")
    private String condition;

    @Column(name = "material")
    private String material; // e.g. "wood", "metal", "porcelain"

    @Column(name = "maker")
    private String maker;

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
