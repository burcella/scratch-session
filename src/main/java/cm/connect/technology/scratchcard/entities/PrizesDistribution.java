package cm.connect.technology.scratchcard.entities;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "PRIZES_DISTRIBUTION")
public class PrizesDistribution {
    @Id
    @Column(nullable = false, name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Positive
    private Long numberOfTicket;
    @NotNull
    @Positive
    private double amount; // le montant a gagner pour cette distribution de prix

    private String symbol;

}
