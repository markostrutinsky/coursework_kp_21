package PawnShop.PawnShop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Table(name = "furniture")
@PrimaryKeyJoinColumn(name = "product_id")
@NoArgsConstructor
public class Furniture extends PawnItem {

    @NotBlank
    @Column(name = "material")
    private String material; // e.g. "wood", "metal", "fabric"

    @NotBlank
    @Column(name = "style")
    private String style; // e.g. "modern", "traditional", "industrial"

    @NotBlank
    @Column(name = "width")
    private double width;

    @NotBlank
    @Column(name = "height")
    private double height;

    @NotBlank
    @Column(name = "depth")
    private double depth;

    @NotBlank
    @Column(name = "age")
    private int age;

    @NotBlank
    @Column(name = "brand")
    private String brand;

    @NotBlank
    @Column(name = "condition")
    private String condition;

    public Furniture(Map<String, String> formData) {
        super(formData);
        this.material = formData.get("material");
        this.style = formData.get("style");
        this.width = Double.parseDouble(formData.get("width"));
        this.height = Double.parseDouble(formData.get("height"));
        this.depth = Double.parseDouble(formData.get("depth"));
        this.age = Integer.parseInt(formData.get("age"));
        this.brand = formData.get("brand");
        this.condition = formData.get("condition");
    }
}
