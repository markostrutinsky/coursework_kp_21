package PawnShop.PawnShop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "clothes")
@PrimaryKeyJoinColumn(name = "product_id")
@NoArgsConstructor
@Data
public class Clothes extends PawnItem {

    //@NotBlank
    @Column(name = "size")
    private double size;

    //@NotBlank
    @Column(name = "brand")
    private String brand;

    //@NotBlank
    @Column(name = "clothingType")
    private String clothingType; // e.g. "shirt", "pants", "dress"

    //@NotBlank
    @Column(name = "material")
    private String material; // e.g. "cotton", "polyester", "silk"

    //@NotBlank
    @Column(name = "condition")
    private String condition;

    public Clothes(Map<String, String> formData) {
        super(formData);
        this.size = Double.parseDouble(formData.get("size"));
        this.brand = formData.get("brand");
        this.clothingType = formData.get("clothingType");
        this.material = formData.get("material");
        this.condition = formData.get("condition");
    }
}
