package cm.connect.technology.scratchcard.services;


import cm.connect.technology.scratchcard.dto.CreateGameDto;
import cm.connect.technology.scratchcard.dto.ResponseDto;
import cm.connect.technology.scratchcard.dto.TicketDto;
import cm.connect.technology.scratchcard.entities.ScratchInstanceGame;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public interface scratchTicketService {
    ResponseDto<ScratchInstanceGame> createTicket(TicketDto dto, Locale locale);
}
