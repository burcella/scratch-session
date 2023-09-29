package cm.connect.technology.scratchcard.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TicketDto {
    private  double amount;

    private Long instanceId;

    private Long userId;
}
