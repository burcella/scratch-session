package cm.connect.technology.scratchcard.services.impl;


import cm.connect.technology.scratchcard.dto.ResponseDto;
import cm.connect.technology.scratchcard.dto.TicketDto;
import cm.connect.technology.scratchcard.entities.ScratchCard;
import cm.connect.technology.scratchcard.entities.ScratchInstanceGame;
import cm.connect.technology.scratchcard.entities.Ticket;
import cm.connect.technology.scratchcard.enums.StatusTicketEnum;
import cm.connect.technology.scratchcard.repositories.ScratchCardRepositories;
import cm.connect.technology.scratchcard.repositories.ScratchInstanceGameRepository;
import cm.connect.technology.scratchcard.services.scratchTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ScratchTicketServiceImpl implements scratchTicketService {
    @Autowired
    private ScratchCardRepositories scratchCardRepositories;

    @Autowired
    private ScratchInstanceGameRepository scratchInstanceGameRepository;
    @Override
    public ResponseDto<ScratchInstanceGame> createTicket(TicketDto dto, Locale locale) {

        ResponseDto<ScratchInstanceGame> response = new ResponseDto<>();
        List<String> messages = new ArrayList<String>();


        try {

            List<ScratchCard> cardList = scratchCardRepositories.findByInstanceGameIdAndIsScratched(dto.getInstanceId(), false);
            if (cardList != null) {
                Random random = new Random();
                int min = 0;
                int max = cardList.size() - 1;
                int nombreAleatoire = min + random.nextInt() % (max - min + 1);

                ScratchCard card = cardList.get(nombreAleatoire);
                card.setIsScratched(true);

                Ticket ticket = Ticket.builder()
                        .scratchCard(card)
                        .id(Long.valueOf(UUID.randomUUID().toString()))
                        .amount(dto.getAmount())
                        .idTransaction(null)
                        .instanceId(dto.getInstanceId())
                        .numero_serie("ffv")
                        .status(StatusTicketEnum.TRAITMENT)
                        .userId(2233L)
                        .build();

                scratchCardRepositories.save(card);
                return null;
            } else {
                messages.add("cardList was not registered in Database");
                response.setBody(null);

            }
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            messages.add(e.getMessage());
        }
        if (messages.isEmpty()) {
            response.setStatus(HttpStatus.OK);
        } else if (response.getStatus() == null) {
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        response.setMessages(messages);
        return response;
    }
}
