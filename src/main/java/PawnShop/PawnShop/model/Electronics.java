package PawnShop.PawnShop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Table(name = "electronics")
@PrimaryKeyJoinColumn(name = "product_id")
@NoArgsConstructor
public class Electronics extends PawnItem {

    @Column(name = "year")
    private int year;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "productType")
    private String productType; // e.g. "TV", "smartphone", "laptop"

    @Column(name = "screenSize")
    private double screenSize;

    @Column(name = "storageCapacity")
    private double storageCapacity;

    public Electronics(Map<String, String> formData) {
        super(formData);
        this.year = Integer.parseInt(formData.getOrDefault("year", "0"));
        this.brand = formData.getOrDefault("brand", "");
        this.model = formData.getOrDefault("model", "");
        this.productType = formData.getOrDefault("productType", "");
        this.screenSize = Double.parseDouble(formData.getOrDefault("screenSize", "0"));
        this.storageCapacity = Double.parseDouble(formData.getOrDefault("storageCapacity", "0"));
    }
}
