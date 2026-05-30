package PawnShop.PawnShop.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Table(name = "sport_equipment")
@PrimaryKeyJoinColumn(name = "product_id")
@NoArgsConstructor
public class SportEquipment extends PawnItem {

    @Column(name = "sportType")
    private String sportType; // e.g. "soccer", "basketball", "tennis"

    @Column(name = "brand")
    private String brand;

    @Column(name = "equipmentType")
    private String equipmentType; // e.g. "ball", "shoes", "racket"

    @Column(name = "size")
    private double size;

    @Column(name = "condition")
    private String condition;

    @Column(name = "model")
    private String model;

    public SportEquipment(Map<String, String> formData) {
        super(formData);
        this.sportType = formData.getOrDefault("sport_type", "");
        this.brand = formData.getOrDefault("brand", "");
        this.equipmentType = formData.getOrDefault("equipment_type", "");
        this.size = Double.parseDouble(formData.getOrDefault("size", "0"));
        this.condition = formData.getOrDefault("condition", "");
        this.model = formData.getOrDefault("model", "");
    }
}
