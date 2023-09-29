package cm.connect.technology.scratchcard.services;

import cm.connect.technology.scratchcard.dto.ResponseDto;
import cm.connect.technology.scratchcard.entities.ScratchFormatGain;
import cm.connect.technology.scratchcard.exceptions.DAOException;
import cm.connect.technology.scratchcard.repositories.ScratchFormatGainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public interface ScratchFormatGainService {
    ResponseDto<List<ScratchFormatGain>> getAll(Locale locale) throws DAOException;
    ResponseDto<ScratchFormatGain> create (ScratchFormatGain formatGain, Locale locale) throws DAOException;
    ResponseDto<ScratchFormatGain> delete(String name, Locale locale) throws DAOException;
    ResponseDto<ScratchFormatGain> findByName(String name, Locale locale);

}
