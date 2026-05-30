package PawnShop.PawnShop.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import javax.sound.midi.Instrument;
import java.util.Map;

@Entity
@Table(name = "musical_instruments")
@PrimaryKeyJoinColumn(name = "product_id")
@NoArgsConstructor
public class MusicalInstrument extends PawnItem {

    @Column(name = "instrumentType", nullable = false)
    private String instrumentType; // e.g. "guitar", "piano", "drums"

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "condition", nullable = false)
    private String condition;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "model", nullable = false)
    private String model;

    public MusicalInstrument(Map<String, String> formData) {
        super(formData);
        this.instrumentType = formData.getOrDefault("instrumentType", "");
        this.brand = formData.getOrDefault("brand", "");
        this.condition = formData.getOrDefault("condition", "");
        this.age = Integer.parseInt(formData.getOrDefault("age", "0"));
        this.model = formData.getOrDefault("model", "");
    }
}
