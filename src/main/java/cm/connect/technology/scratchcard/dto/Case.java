package cm.connect.technology.scratchcard.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Case implements Serializable {
    private static final long serialVersionUID = 1L;

    private int caseNumber;

    private int trueCaseNumber;
}
