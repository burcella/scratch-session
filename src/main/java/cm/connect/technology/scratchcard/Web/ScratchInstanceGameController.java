package cm.connect.technology.scratchcard.Web;

import cm.connect.technology.scratchcard.dto.CreateGameDto;
import cm.connect.technology.scratchcard.dto.CreateSessionDto;
import cm.connect.technology.scratchcard.dto.ResponseDto;
import cm.connect.technology.scratchcard.entities.ScratchFormatGain;
import cm.connect.technology.scratchcard.entities.ScratchInstanceGame;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("api/scratchInstanceGame")
//@Api(value = "Endpoints to manage ScratchInstanceGame", tags = "ScratchInstanceGame RestController")
public interface ScratchInstanceGameController {
    @GetMapping(value = "/getAll")
    //@ApiOperation(value = "Endpoint to get all ScratchInstanceGame")
    ResponseDto<List<ScratchInstanceGame>> getAll(
            @RequestHeader(name = "Accept-language", required = false) Locale locale
    );
    @PostMapping(value = "/create")
    //@ApiOperation(value = "Endpoint to create a new scratchInstanceGame")
    ResponseDto<ScratchInstanceGame> create(
            @RequestHeader(name = "Accept-language", required = false) Locale locale,
            @RequestBody @Valid CreateGameDto createGameDto
    );

   // ResponseDto<ScratchInstanceGame> createInstanceGame(Locale locale, ScratchInstanceGame formatGain);

 //   ResponseDto<ScratchInstanceGame> createInstanceGame(Locale locale, CreateGameDto createGameDto);

    //@ApiOperation("Endpoint to get an existing scratchInstanceGame by name")
    @GetMapping("/name/{name}")
    ResponseDto<ScratchInstanceGame> getByName(
            @RequestHeader(name = "Accept-Language", required = false) Locale locale,
            @PathVariable("name") String name
    );



    @PostMapping("/startDate/{startDate}")
    ResponseDto<ScratchInstanceGame> getByStartDate(@RequestHeader(name = "Accept-Language", required = false) Locale locale,
                                                    @PathVariable("startDate")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start);
    @PostMapping("/endDate/{endDate}")
    ResponseDto<ScratchInstanceGame> getByEndDate(@RequestHeader(name = "Accept-Language", required = false) Locale locale,
                                                  @PathVariable("endDate")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end);
    @PostMapping("/endSession/{startDate}")
    ResponseDto<ScratchInstanceGame> desactiveSession(@RequestHeader(name = "Accept-Language", required = false) Locale locale,
                                                    @PathVariable("startDate")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start);






}
