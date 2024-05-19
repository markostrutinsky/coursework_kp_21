package PawnShop.PawnShop.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Table(name = "sport_equipment")
@PrimaryKeyJoinColumn(name = "product_id")
@NoArgsConstructor
public class SportEquipment extends PawnItem {

    @NotBlank
    @Column(name = "sportType")
    private String sportType; // e.g. "soccer", "basketball", "tennis"

    @NotBlank
    @Column(name = "brand")
    private String brand;

    @NotBlank
    @Column(name = "equipmentType")
    private String equipmentType; // e.g. "ball", "shoes", "racket"

    @NotBlank
    @Column(name = "size")
    private double size;

    @NotBlank
    @Column(name = "condition")
    private String condition;

    @NotBlank
    @Column(name = "model")
    private String model;

    public SportEquipment(Map<String, String> formData) {
        super(formData);
        this.sportType = formData.get("sport_type");
        this.brand = formData.get("brand");
        this.equipmentType = formData.get("equipment_type");
        this.size = Double.parseDouble(formData.get("size"));
        this.condition = formData.get("condition");
        this.model = formData.get("model");
    }
}
