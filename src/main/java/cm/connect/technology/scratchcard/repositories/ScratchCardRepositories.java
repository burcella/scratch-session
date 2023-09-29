package cm.connect.technology.scratchcard.repositories;

import cm.connect.technology.scratchcard.entities.ScratchCard;
import cm.connect.technology.scratchcard.entities.ScratchFormatGain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ScratchCardRepositories extends JpaRepository<ScratchCard, Long> {
    List<ScratchCard> findByInstanceGameIdAndIsScratched (Long instanceGameId, Boolean isScratched);
}
