package PawnShop.PawnShop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Table(name = "electronics")
@PrimaryKeyJoinColumn(name = "product_id")
@NoArgsConstructor
public class Electronics extends PawnItem {

    @NotBlank
    @Column(name = "year")
    private int year;

    @NotBlank
    @Column(name = "brand")
    private String brand;

    @NotBlank
    @Column(name = "model")
    private String model;

    @NotBlank
    @Column(name = "productType")
    private String productType; // e.g. "TV", "smartphone", "laptop"

    @NotBlank
    @Column(name = "screenSize")
    private double screenSize;

    @NotBlank
    @Column(name = "storageCapacity")
    private double storageCapacity;

    public Electronics(Map<String, String> formData) {
        super(formData);
        this.year = Integer.parseInt(formData.get("year"));
        this.brand = formData.get("brand");
        this.model = formData.get("model");
        this.productType = formData.get("productType");
        this.screenSize = Double.parseDouble(formData.get("screenSize"));
        this.storageCapacity = Double.parseDouble(formData.get("storageCapacity"));
    }
}
