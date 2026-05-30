package PawnShop.PawnShop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Table(name = "household_goods")
@PrimaryKeyJoinColumn(name = "product_id")
@NoArgsConstructor
public class HouseholdGoods extends PawnItem {

    @Column(name = "metalSample", nullable = false)
    private int metalSample;

    @Column(name = "productType", nullable = false)
    private String productType; // e.g. "kitchenware", "bedding", "appliances"

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "material", nullable = false)
    private String material; // e.g. "stainless steel", "glass", "fabric"

    @Column(name = "size", nullable = false)
    private double size;

    @Column(name = "condition", nullable = false)
    private String condition;

    @Column(name = "model", nullable = false)
    private String model;

    public HouseholdGoods(Map<String, String> formData) {
        super(formData);
        this.metalSample = Integer.parseInt(formData.getOrDefault("metalSample", "0"));
        this.productType = formData.getOrDefault("productType", "");
        this.brand = formData.getOrDefault("brand", "");
        this.material = formData.getOrDefault("material", "");
        this.size = Double.parseDouble(formData.getOrDefault("size", "0"));
        this.condition = formData.getOrDefault("condition", "");
        this.model = formData.getOrDefault("model", "");
    }
}
