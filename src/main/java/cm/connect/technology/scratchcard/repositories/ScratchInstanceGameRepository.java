package cm.connect.technology.scratchcard.repositories;

import cm.connect.technology.scratchcard.entities.ScratchFormatGain;
import cm.connect.technology.scratchcard.entities.ScratchInstanceGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
@Repository
public interface ScratchInstanceGameRepository extends JpaRepository<ScratchInstanceGame,Long> {
    ScratchInstanceGame findByName(String name);

    ScratchInstanceGame findByStartDate(LocalDateTime startDate);

    ScratchInstanceGame findByEndDate(LocalDateTime endDate);
}
