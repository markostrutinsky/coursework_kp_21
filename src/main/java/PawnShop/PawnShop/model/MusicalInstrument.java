package PawnShop.PawnShop.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

import javax.sound.midi.Instrument;
import java.util.Map;

@Entity
@Table(name = "musical_instruments")
@PrimaryKeyJoinColumn(name = "product_id")
@NoArgsConstructor
public class MusicalInstrument extends PawnItem {

    @NotBlank
    @Column(name = "instrumentType", nullable = false)
    private String instrumentType; // e.g. "guitar", "piano", "drums"

    @NotBlank
    @Column(name = "brand", nullable = false)
    private String brand;

    @NotBlank
    @Column(name = "condition", nullable = false)
    private String condition;

    @NotBlank
    @Column(name = "age", nullable = false)
    private int age;

    @NotBlank
    @Column(name = "model", nullable = false)
    private String model;

    public MusicalInstrument(Map<String, String> formData) {
        super(formData);
        this.instrumentType = formData.get("instrumentType");
        this.brand = formData.get("brand");
        this.condition = formData.get("condition");
        this.age = Integer.parseInt(formData.get("age"));
        this.model = formData.get("model");
    }
}
