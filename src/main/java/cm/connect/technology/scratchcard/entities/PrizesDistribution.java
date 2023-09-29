package cm.connect.technology.scratchcard.entities;


import lombok.*;

import javax.persistence.*;
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
    private Long numberOfTicket;

    private double amount; // le montant a gagner pour cette distribution de prix

    private String symbol;

}
