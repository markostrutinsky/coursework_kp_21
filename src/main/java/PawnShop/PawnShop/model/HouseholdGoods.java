package PawnShop.PawnShop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "household_goods")
@PrimaryKeyJoinColumn(name = "product_id")
@NoArgsConstructor
@Data
public class HouseholdGoods extends PawnItem {

    //@NotBlank
    @Column(name = "productType", nullable = false)
    private String productType; // e.g. "kitchenware", "bedding", "appliances"

    //@NotBlank
    @Column(name = "brand", nullable = false)
    private String brand;

    //@NotBlank
    @Column(name = "material", nullable = false)
    private String material; // e.g. "stainless steel", "glass", "fabric"

    //@NotBlank
    @Column(name = "size", nullable = false)
    private double size;

    //@NotBlank
    @Column(name = "condition", nullable = false)
    private String condition;

    //@NotBlank
    @Column(name = "model", nullable = false)
    private String model;

    public HouseholdGoods(Map<String, String> formData) {
        super(formData);
        this.productType = formData.get("productType");
        this.brand = formData.get("brand");
        this.material = formData.get("material");
        this.size = Double.parseDouble(formData.get("size"));
        this.condition = formData.get("condition");
        this.model = formData.get("model");
    }
}
