package cm.connect.technology.scratchcard.Web.Impl;

import cm.connect.technology.scratchcard.Web.ScratchInstanceGameController;
import cm.connect.technology.scratchcard.dto.CreateGameDto;
import cm.connect.technology.scratchcard.dto.ResponseDto;
import cm.connect.technology.scratchcard.entities.ScratchInstanceGame;
import cm.connect.technology.scratchcard.services.ScratchInstanceGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Component
public class ScratchInstanceGameControllerImpl implements ScratchInstanceGameController {

    @Autowired
    ScratchInstanceGameService service;


    @Override
    public ResponseDto<List<ScratchInstanceGame>> getAll(Locale locale) {
        return service.getAll(locale);
    }

    @Override
    public ResponseDto<ScratchInstanceGame> create(Locale locale, CreateGameDto createGameDto) {
        return service.createInstanceGame(createGameDto, locale);
    }

    @Override
    public ResponseDto<ScratchInstanceGame> getByName(Locale locale, String name) {
        return service.findInstanceByName(name, locale);
    }


    @Override
    public ResponseDto<ScratchInstanceGame> getByStartDate(Locale locale, LocalDateTime startDate) {
        return service.startInstance(startDate, locale);
    }

    @Override
    public ResponseDto<ScratchInstanceGame> getByEndDate(Locale locale, LocalDateTime end) {
        return service.endInstance(end, locale);
    }

    @Override
    public ResponseDto<ScratchInstanceGame> desactiveSession(Locale locale, LocalDateTime start) {
        return service.desactiveSession(start,locale);
    }
}

    //  @Override
  //  public ResponseDto<ScratchInstanceGame> getByStartDate(Locale locale, String name) {
   //     return service.startInstance(name, locale);
   // }


