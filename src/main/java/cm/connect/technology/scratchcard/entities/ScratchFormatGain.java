package cm.connect.technology.scratchcard.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Table(name = "FORMAT_GAIN")
public class ScratchFormatGain {
    @Id

    @Column(name = "NAME_FORMAT")
    private String name;
    @Column(name = "REPEATING_SYMBOL")
    @NotNull
    @Positive
    private int repeatingNumber;// nombre de symbole repeter
    @Column(name = "TOTAL_SYMBOL")
    @NotNull
    @Positive
    private int totalNumber;// nombre de symbole
}
