package cm.connect.technology.scratchcard.dto;

import cm.connect.technology.scratchcard.entities.PrizesDistribution;
import cm.connect.technology.scratchcard.entities.ScratchFormatGain;
import cm.connect.technology.scratchcard.entities.Session;
import cm.connect.technology.scratchcard.enums.FormatScratchEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateGameDto {
    private Long idSaasGame;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int numberOfSession;
    private ScratchFormatGain scratchFormatGain;
    private double ticketPrice;
    private List<PrizesDistribution> prizesDistributions;
    private Long totalCard;
    @Enumerated(EnumType.STRING)
    private FormatScratchEnum format;




}
