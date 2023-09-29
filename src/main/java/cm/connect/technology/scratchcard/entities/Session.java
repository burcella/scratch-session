package cm.connect.technology.scratchcard.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "SCRATCH_GAME_SESSION")
public class Session {
    @Id
    @Column(nullable = false, name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "INSTANCE_GAME")
    private Long instanceGameId;

    @Column(nullable = false, name = "START_TIME")
    private LocalDateTime startTime;

    @Column(nullable = false, name = "END_TIME")
    private LocalDateTime endTime;

    @Column(nullable = false, name = "IS_OPEN")
    private Boolean ouvert;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ScratchCard> scratchCards;
}
