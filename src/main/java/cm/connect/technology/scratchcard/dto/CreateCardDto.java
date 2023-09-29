package cm.connect.technology.scratchcard.dto;

import cm.connect.technology.scratchcard.entities.PrizesDistribution;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CreateCardDto {
    private Long instanceGameId;
    private  List<PrizesDistribution> prizesDistributions;
    private Long totalCard;
}
