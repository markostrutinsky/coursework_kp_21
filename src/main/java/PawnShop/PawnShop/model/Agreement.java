package PawnShop.PawnShop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pawn_item_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "interest_rate")
    private int interestRate;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date dueDate;

    @Column(name = "is_paid")
    private boolean isPaid;

    //@OneToOne
    //@JoinColumn(name = "pawn_item_id")
    //private PawnItem item;

    public Agreement(String firstName, String lastName, String email, BigDecimal amount, int interestRate, Date startDate, Date dueDate, boolean isPaid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.amount = amount;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.isPaid = isPaid;
    }


}
