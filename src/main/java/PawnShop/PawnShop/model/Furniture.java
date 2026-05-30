package PawnShop.PawnShop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Table(name = "furniture")
@PrimaryKeyJoinColumn(name = "product_id")
@NoArgsConstructor
public class Furniture extends PawnItem {

    @Column(name = "material")
    private String material; // e.g. "wood", "metal", "fabric"

    @Column(name = "style")
    private String style; // e.g. "modern", "traditional", "industrial"

    @Column(name = "width")
    private double width;

    @Column(name = "height")
    private double height;

    @Column(name = "depth")
    private double depth;

    @Column(name = "age")
    private int age;

    @Column(name = "brand")
    private String brand;

    @Column(name = "condition")
    private String condition;

    public Furniture(Map<String, String> formData) {
        super(formData);
        this.material = formData.getOrDefault("material", "");
        this.style = formData.getOrDefault("style", "");
        this.width = Double.parseDouble(formData.getOrDefault("width", "0"));
        this.height = Double.parseDouble(formData.getOrDefault("height", "0"));
        this.depth = Double.parseDouble(formData.getOrDefault("depth", "0"));
        this.age = Integer.parseInt(formData.getOrDefault("age", "0"));
        this.brand = formData.getOrDefault("brand", "");
        this.condition = formData.getOrDefault("condition", "");
    }
}
