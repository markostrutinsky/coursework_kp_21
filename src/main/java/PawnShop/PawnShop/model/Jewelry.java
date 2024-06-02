package PawnShop.PawnShop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "jewelry")
@PrimaryKeyJoinColumn(name = "product_id")
@NoArgsConstructor
@Data
public class Jewelry extends PawnItem {

    @Column(name = "metal_sample", nullable = false)
    private int metalSample;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "size", nullable = false)
    private double size;

    @Column(name = "is_precious_stones", nullable = false)
    private boolean isPreciousStones;

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
