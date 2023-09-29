package cm.connect.technology.scratchcard.services.impl;

import cm.connect.technology.scratchcard.dto.ResponseDto;
import cm.connect.technology.scratchcard.entities.ScratchFormatGain;
import cm.connect.technology.scratchcard.exceptions.DAOException;
import cm.connect.technology.scratchcard.repositories.ScratchFormatGainRepository;
import cm.connect.technology.scratchcard.services.ScratchFormatGainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.util.*;

@Component
public class ScratchFormatGainServiceImpl implements ScratchFormatGainService {
    @Autowired
    private ScratchFormatGainRepository repository;


    @Override
    public ResponseDto<List<ScratchFormatGain>> getAll(Locale locale) throws DAOException {
        ResponseDto<List<ScratchFormatGain>> response = new ResponseDto<>();
        List<String> messages = new ArrayList<>();
        try {
            List<ScratchFormatGain> scratchFormatGains = repository.findAll();
            response.setBody(scratchFormatGains);
        } catch (DAOException e) {
            messages.add(e.getMessage());
            throw new DAOException(e);
        }
        if (!messages.isEmpty()) response.setStatus(HttpStatus.BAD_REQUEST);
        else response.setStatus(HttpStatus.OK);
        response.setMessages(messages);
        return response;
    }

    @Override
    public ResponseDto<ScratchFormatGain> create(ScratchFormatGain formatGain, Locale locale) throws DAOException {
        ResponseDto<ScratchFormatGain> response = new ResponseDto<>();
        List<String> messages = new ArrayList<>();
        try {
            ScratchFormatGain scratchFormatGain = repository.findByName(formatGain.getName());

            if (repository.findByName(formatGain.getName()) != null){
                messages.add("VALUE ALREADY EXISTS");

            }else {
                    response.setBody(formatGain);
                }
        }catch ( DAOException e) {
            response.setStatus(e.getStatus());
            messages.add(e.getMessage());
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        }
        if(messages.isEmpty()){
            response.setStatus(HttpStatus.OK);
        }else if (response.getStatus() == null){
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        response.setMessages(messages);
        repository.save(formatGain);
        return response;
    }

    @Override
    public ResponseDto<ScratchFormatGain> delete(String name, Locale locale) throws DAOException {
        ResponseDto<ScratchFormatGain> response = new ResponseDto<>();
        List<String> messages = new ArrayList<>();
        try {
            ScratchFormatGain scratchFormatGain = repository.findByName(name);
            if (scratchFormatGain != null){
                repository.delete(scratchFormatGain);
                response.setBody(scratchFormatGain);
            }else {
                messages.add("Value was not registered in Database");
            }
        }catch (DAOException e){
            response.setStatus(e.getStatus());
            messages.add(e.getMessage());
        }
        if(messages.isEmpty()){
            response.setStatus(HttpStatus.OK);
        }else if (response.getStatus() == null){
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        response.setMessages(messages);
        return response;
    }

    @Override
    public ResponseDto<ScratchFormatGain> findByName(String name, Locale locale) {
        ResponseDto<ScratchFormatGain> response = new ResponseDto<>();
        List<String> messages = new ArrayList<>();
        try{
            ScratchFormatGain formatGain = repository.findByName(name);
            if (formatGain != null){
                response.setBody(formatGain);
            }else {
                messages.add("Value was not registered in Database");
//                messages.add(messageSource.getMessage("errors.not-found.gameSaas", null, locale));
                response.setBody(null);
            }
        }catch (DAOException e){
            response.setStatus(e.getStatus());
            messages.add(e.getMessage());
        }
        if(messages.isEmpty()){
            response.setStatus(HttpStatus.OK);
        }else if (response.getStatus() == null){
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        response.setMessages(messages);
        return response;
    }
}
