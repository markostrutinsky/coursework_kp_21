package PawnShop.PawnShop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Table(name = "clothes")
@PrimaryKeyJoinColumn(name = "product_id")
@NoArgsConstructor
public class Clothes extends PawnItem{

    @Column(name = "size")
    private double size;

    @Column(name = "brand")
    private String brand;

    @Column(name = "clothingType")
    private String clothingType; // e.g. "shirt", "pants", "dress"

    @Column(name = "material")
    private String material; // e.g. "cotton", "polyester", "silk"

    @Column(name = "condition")
    private String condition;

    public Clothes(Map<String, String> formData) {
        super(formData);
        this.size = Double.parseDouble(formData.getOrDefault("size", "0"));
        this.brand = formData.getOrDefault("brand", "");
        this.clothingType = formData.getOrDefault("clothingType", "");
        this.material = formData.getOrDefault("material", "");
        this.condition = formData.getOrDefault("condition", "");
    }
}
