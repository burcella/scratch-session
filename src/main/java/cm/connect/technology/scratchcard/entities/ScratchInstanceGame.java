package cm.connect.technology.scratchcard.entities;

import cm.connect.technology.scratchcard.enums.StatusGameEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "INSTANCE_SCRATCH_GAME")
public class ScratchInstanceGame {
    @Id
    @Column(nullable = false, name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ID_SAASGAME", nullable = false)
    private Long idSaasGame;

    @Column(nullable = false, name = "NAME")
    private String name;

    @Column(nullable = false, name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(nullable = false, name = "START_DATE")
    private LocalDateTime startDate;

    @Column(nullable = false, name = "END_DATE")
    private LocalDateTime endDate;

    @Column(nullable = false, name = "POOLS_NUMBER")
    @Positive
    private int numberOfSession;

    @Column(nullable = false, name = "TICKET_PRICE")
    @Positive
    private double ticketPrice;

    @Column(nullable = false, name = "CREATED_BY")
    private String createdBy;

    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name="PRIZES_DISTRIBUTION")
    private List<PrizesDistribution> prizesDistributions;

//    @Column(nullable = false, name = "TOTAL_TICKET_PRICE")
//    private Long totalTicketPrice;
  //  @Column(name = "FORMAT_GAIN")
    @OneToOne(cascade = CascadeType.ALL)
    private ScratchFormatGain scratchFormatGain;
   // @OneToMany(cascade = CascadeType.ALL)

    //@OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(name="SCRATCH_CARD")
    //private List<ScratchCard> scratchCards;

    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(name="INSTANCE_SESSION")
    private List<Session> sessions;

    @Column(nullable = true, name = "STATUS")
    @Enumerated(EnumType.STRING)
    private StatusGameEnum status;
    @NotNull
    @Positive
    private Long totalCard;

}
