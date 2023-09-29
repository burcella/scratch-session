package cm.connect.technology.scratchcard.dto;

import cm.connect.technology.scratchcard.entities.PrizesDistribution;
import cm.connect.technology.scratchcard.entities.ScratchFormatGain;
import cm.connect.technology.scratchcard.entities.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateSessionDto {

    private int numberOfSession;
    private int timeInstance;
    private Long id_Instance;
    private Session session;
    private LocalDateTime startTimeInstance;

}
