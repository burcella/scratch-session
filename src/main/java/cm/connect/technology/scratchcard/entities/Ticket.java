package cm.connect.technology.scratchcard.entities;

import cm.connect.technology.scratchcard.enums.StatusTicketEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Ticket implements Serializable {
    public static final long serializableUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // private String categoryName;
    @Enumerated(EnumType.STRING)
    private StatusTicketEnum status;
    @NotNull
    @Positive
    private  double amount; // prix du ticket //

    private String numero_serie;

    private Long instanceId; //

    private String idTransaction;

    @OneToOne(cascade = CascadeType.ALL)
    private ScratchCard scratchCard;
    private Long sessionId;

    private Long userId; //

}
