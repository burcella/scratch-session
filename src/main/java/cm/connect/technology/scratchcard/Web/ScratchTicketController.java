package cm.connect.technology.scratchcard.Web;


import cm.connect.technology.scratchcard.dto.CreateGameDto;
import cm.connect.technology.scratchcard.dto.ResponseDto;
import cm.connect.technology.scratchcard.dto.TicketDto;
import cm.connect.technology.scratchcard.entities.ScratchInstanceGame;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping("api/scratchTicket")
public interface ScratchTicketController {
    @PostMapping(value = "/create")
        //@ApiOperation(value = "Endpoint to create a new scratchInstanceGame")
    ResponseDto<ScratchInstanceGame> create(
            @RequestHeader(name = "Accept-language", required = false) Locale locale,
            @RequestBody @Valid TicketDto dto
            );
}
