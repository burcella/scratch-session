package cm.connect.technology.scratchcard.Web.Impl;


import cm.connect.technology.scratchcard.Web.ScratchTicketController;
import cm.connect.technology.scratchcard.dto.ResponseDto;
import cm.connect.technology.scratchcard.dto.TicketDto;
import cm.connect.technology.scratchcard.entities.ScratchInstanceGame;
import cm.connect.technology.scratchcard.services.impl.ScratchTicketServiceImpl;
import cm.connect.technology.scratchcard.services.scratchTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ScratchTicketControllerImpl implements ScratchTicketController {

    @Autowired
    private scratchTicketService service;
    @Override
    public ResponseDto<ScratchInstanceGame> create(Locale locale, TicketDto dto) {
        return service.createTicket(dto,locale);
    }
}
