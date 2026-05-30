package PawnShop.PawnShop.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Table(name = "jewelry")
@PrimaryKeyJoinColumn(name = "product_id")
@NoArgsConstructor
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
        this.metalSample = Integer.parseInt(fromData.getOrDefault("metal_sample", "0"));
        this.weight = Double.parseDouble(fromData.getOrDefault("weight", "0"));
        this.size = Double.parseDouble(fromData.getOrDefault("size", "0"));
        this.isPreciousStones = Boolean.parseBoolean(fromData.getOrDefault("is_precious_stones", "false"));
        this.stonesCount = Integer.parseInt(fromData.getOrDefault("stones_count", "0"));
    }
}
