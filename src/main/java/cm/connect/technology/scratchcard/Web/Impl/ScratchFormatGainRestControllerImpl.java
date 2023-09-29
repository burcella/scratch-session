package cm.connect.technology.scratchcard.Web.Impl;

import cm.connect.technology.scratchcard.Web.ScratchFormatGainRestController;
import cm.connect.technology.scratchcard.dto.ResponseDto;
import cm.connect.technology.scratchcard.entities.ScratchFormatGain;
import cm.connect.technology.scratchcard.services.ScratchFormatGainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class ScratchFormatGainRestControllerImpl implements ScratchFormatGainRestController {
    @Autowired
    private ScratchFormatGainService service;
    @Override
   public ResponseDto<List<ScratchFormatGain>> getAll(Locale locale) {
        return service.getAll(locale);
    }

    @Override
    public ResponseDto<ScratchFormatGain> create(Locale locale, ScratchFormatGain formatGain) {
        return service.create(formatGain, locale);
    }

    @Override
    public ResponseDto<ScratchFormatGain> getByName(Locale locale, String name) {
        return service.findByName(name, locale);
    }

    @Override
    public ResponseDto<ScratchFormatGain> delete(Locale locale, String name) {
        return service.delete(name, locale);
    }
}
