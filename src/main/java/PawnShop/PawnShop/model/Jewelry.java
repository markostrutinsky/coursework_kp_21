package PawnShop.PawnShop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Table(name = "jewelry")
@PrimaryKeyJoinColumn(name = "product_id")
@NoArgsConstructor
public class Jewelry extends PawnItem {

    @NotBlank
    @Column(name = "metal_sample", nullable = false)
    private int metalSample;

    @NotBlank
    @Column(name = "weight", nullable = false)
    private double weight;

    @NotBlank
    @Column(name = "size", nullable = false)
    private double size;

    @NotBlank
    @Column(name = "is_precious_stones", nullable = false)
    private boolean isPreciousStones;

    @NotBlank
    @Column(name = "stones_count", nullable = false)
    private int stonesCount;

    public Jewelry(Map<String, String> fromData) {
        super(fromData);
        this.metalSample = Integer.parseInt(fromData.get("metal_sample"));
        this.weight = Double.parseDouble(fromData.get("weight"));
        this.size = Double.parseDouble(fromData.get("size"));
        this.isPreciousStones = Boolean.parseBoolean(fromData.get("is_precious_stones"));
        this.stonesCount = Integer.parseInt(fromData.get("stones_count"));
    }
}
