package cm.connect.technology.scratchcard.services.impl;

import cm.connect.technology.scratchcard.dto.*;
import cm.connect.technology.scratchcard.entities.*;
import cm.connect.technology.scratchcard.enums.StatusGameEnum;
import cm.connect.technology.scratchcard.enums.SymbolEnnum;
import cm.connect.technology.scratchcard.exceptions.DAOException;
import cm.connect.technology.scratchcard.repositories.ScratchCardRepositories;
import cm.connect.technology.scratchcard.repositories.ScratchInstanceGameRepository;
import cm.connect.technology.scratchcard.services.ScratchInstanceGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.util.Locale.filter;

@Component
public class ScratchInstanceGameServiceImpl implements ScratchInstanceGameService {
    @Autowired
    private ScratchInstanceGameRepository scratchInstanceGameRepository;
    @Autowired
    private ScratchCardRepositories scratchCardRepositories;




    @Override
    @Scheduled(fixedRate = 60000)
    public ResponseDto<ScratchInstanceGame> createInstanceGame(CreateGameDto dto, Locale locale) {


        ResponseDto<ScratchInstanceGame> response = new ResponseDto<>();
        List<String> messages = new ArrayList<String>();
        List<PrizesDistribution> prizesDistributions= dto.getPrizesDistributions();
        try {
            for (PrizesDistribution prizesDistribution:prizesDistributions){
                if (dto.getScratchFormatGain().getRepeatingNumber() <= (dto.getScratchFormatGain().getTotalNumber())/2 && dto.getScratchFormatGain().getRepeatingNumber()>((dto.getScratchFormatGain().getTotalNumber())/2)-1 && prizesDistribution.getNumberOfTicket()< dto.getTotalCard()/3){
                    if (dto.getStartDate().isBefore(dto.getEndDate()) && dto.getStartDate().isAfter(LocalDateTime.now())) {
                        // Calculer le nombre de minute entre la date de début et la date de fin de l'instance

                        long nombreMinutes = Duration.between(dto.getStartDate(), dto.getEndDate()).toMinutes();

                        // Calculer le nombre de minute par sessions à générer
                        long nombreMinutesPerSession = nombreMinutes / dto.getNumberOfSession();
                        long numberOfCardSession = dto.getTotalCard() / dto.getNumberOfSession();
                        int intValue = (int) numberOfCardSession;
                        // on construire l'instance
                        ScratchInstanceGame instanceGame = new ScratchInstanceGame();
                        instanceGame = ScratchInstanceGame.builder()
                                .idSaasGame(dto.getIdSaasGame())
                                .createDate(LocalDateTime.now())
                                .name(dto.getName())
                                .startDate(dto.getStartDate())
                                .endDate(dto.getEndDate())
                                .numberOfSession(dto.getNumberOfSession())
                                .ticketPrice(dto.getTicketPrice())
                                .prizesDistributions(dto.getPrizesDistributions())
                                .scratchFormatGain(dto.getScratchFormatGain())
                                .status(StatusGameEnum.TRAITEMENT)
                                .createdBy("du token on recupere le nom de celui qui cree")
                                .totalCard(dto.getTotalCard())
                                .build();
                        ScratchInstanceGame instanceGame1 = scratchInstanceGameRepository.save(instanceGame);


                        // instanceGame1.setScratchCards(cardList);
                        response.setBody(instanceGame1);

                        // On genere toutes les sessions de l'instance et on les stocke en base de données
                        // On initialise la date de debut de la premiere session
                        LocalDateTime debutDeSession = dto.getStartDate();

                        List<ScratchCard> cardList = new ArrayList<>(createCard(dto.getScratchFormatGain(), instanceGame1.getId(), dto.getPrizesDistributions(), dto.getTotalCard(), dto.getNumberOfSession(), locale));


//****************************************************************************************************************
                        // determine le nombre de carte par session
                        int nbrCard = cardList.size() / dto.getNumberOfSession();
                        List<Session> sessionList = new ArrayList<>();

                        System.out.println("nombre de carte par session = " + nbrCard);
                        // boucle de session
                        for (int i = 1; i <= dto.getNumberOfSession(); i++) {
                            System.out.println("nombre de carte par shion = " + nbrCard);
                            // tu definis la liste vide des carte de la session
                            List<ScratchCard> sessionListCard = new ArrayList<>();

                            // tu remplie la liste de carte de tes sessions
                            for (int t = 0; t < nbrCard; t++) {
                                if (i == dto.getNumberOfSession()) {
                                    sessionListCard = cardList.subList(0,cardList.size());
                                } else {
                                    sessionListCard.add(cardList.get(0));
                                    cardList.remove(0);

                                }
                            }
                            LocalDateTime finDeSession = debutDeSession.plus(nombreMinutesPerSession, ChronoUnit.MINUTES);
                            Session instanceGameSession = Session.builder()
                                    //                 .id(UUID.randomUUID().toString())
                                    .instanceGameId(instanceGame1.getId())
                                    .startTime(debutDeSession)
                                    .endTime(finDeSession)
                                    .ouvert(false)
                                    .scratchCards(sessionListCard)
                                    //.scratchCards(new ArrayList<>(createCard(dto.getScratchFormatGain(), instanceGame1.getId(), dto.getPrizesDistributions(), dto.getTotalCard(), dto.getNumberOfSession(), locale)))
                                    .build();
                            sessionList.add(instanceGameSession);

                            debutDeSession = finDeSession;

                        }
//*****************************************************************************************************************

                        instanceGame1.setSessions(sessionList);
                        response.setBody(scratchInstanceGameRepository.save(instanceGame1));

                    } else {
                        // messages.add("the startDate is not correct");
                        response.setMessages(Collections.singletonList("the startDate is not correct"));
                        response.setBody(null);

                    }
                    // response.setBody(instanceGame1);

                }else{
                    response.setMessages(Collections.singletonList("the repeatingNumber or numberOfTicket is not correct"));
                    response.setBody(null);
                }
            }
        }catch(Exception e){
           throw new RuntimeException(e.getMessage());
        }


        return response;
    }





    public List<ScratchCard> createCard(ScratchFormatGain scratchFormatGain, Long instanceGameId, List<PrizesDistribution> prizesDistributions, Long totalCard,int numberOfSession, Locale locale) {
        ResponseDto<ScratchInstanceGame> response = new ResponseDto<>();
        List<String> messages = new ArrayList<String>();
        List<ScratchCard> scratchCards = new ArrayList<>();
        try {


                for (PrizesDistribution prizesDistribution : prizesDistributions) {
                    Long cardNumber =  prizesDistribution.getNumberOfTicket(); ;
                    System.out.println( cardNumber);
                    for (int n = 0; n < cardNumber; n++) {
                            System.out.println( "cardNumber" +cardNumber);

                            String[] value = new String[scratchFormatGain.getTotalNumber()];// je crais le nombre de case
                            List<String> listOfSymbol = new ArrayList<>(SymbolEnnum.distribution); //j ennumere tous les symboles
                            listOfSymbol.remove(prizesDistribution.getSymbol());// je retire le montant a gagner dans la liste de symbole
                            for (int e = 0; e < scratchFormatGain.getRepeatingNumber(); e++) {// je positione le symbol qui se repete
                                value[e] = prizesDistribution.getSymbol();
                            }
                            Collections.shuffle(listOfSymbol);// je fais un melange aleatoire
                            int i = 0;
                            for (int e = scratchFormatGain.getRepeatingNumber(); e < scratchFormatGain.getTotalNumber(); e++) {
                                value[e] = listOfSymbol.get(i);
                                i++;
                                if (i == 4) i = 0;
                            }
                            List<String> list = Arrays.asList(value);
                            Collections.shuffle(list);
                            String delim = ", ";
                            String boxValue1 = String.join(delim, list);

                            ScratchCard scratchCard = ScratchCard.builder()
                                    .boxValue(boxValue1)
                                    .isScratched(false)
                                    .instanceGameId(instanceGameId)
                                    .isWinning(true)
                                    .prizeAmount(prizesDistribution.getAmount())
                                    .build();
                            System.out.println(scratchCard);
                            // scratchCardRepositories.save(scratchCard);
                            scratchCards.add(scratchCard);

                    }

                }
                List<String> value2 = new ArrayList<>();
                Long winNumber = 0L;
                List<String> listOfSymbol = new ArrayList<>(SymbolEnnum.distribution); //j ennumere tous les symboles
                for (PrizesDistribution prizesDistribution : prizesDistributions) {
                    winNumber = winNumber + prizesDistribution.getNumberOfTicket();

                    for (Long n = winNumber; n < totalCard; n++) {
                        String[] value = new String[scratchFormatGain.getTotalNumber()];
                        int f = 0;

                        for (int e = 0; e < scratchFormatGain.getTotalNumber(); e++) {
                            // value2.add(listOfSymbol.get(f));
                            value[e] = listOfSymbol.get(f);
                            f++;
                            if (f == 6) f = 0;

                        }
                        List<String> list1 = Arrays.asList(value);
                        Collections.shuffle(list1);
                        String delim = ", ";
                        String boxValue = String.join(delim, list1);

                        ScratchCard scratchCard = ScratchCard.builder()
                                .boxValue(boxValue)
                                .isScratched(false)
                                .instanceGameId(instanceGameId)
                                .isWinning(false)
                                .prizeAmount(null)
                                .build();
                        scratchCards.add(scratchCard);
                        System.out.println(scratchCard);
                        scratchCardRepositories.save(scratchCard);
                        System.out.println(scratchCard);


                    }
                    Collections.shuffle(scratchCards);
                }

        }catch(Exception e){
            //  throw new RuntimeException(e.getMessage());
        }

        return scratchCards;
    }
    @Override
    @Scheduled(fixedRate = 60000)
    public ResponseDto<ScratchInstanceGame> startInstance(LocalDateTime startDate, Locale locale) {
        ResponseDto<ScratchInstanceGame> response = new ResponseDto<>();
        List<String> messages = new ArrayList<String>();



        try {
            ScratchInstanceGame scratchInstanceGame = scratchInstanceGameRepository.findByStartDate(startDate);
            if (scratchInstanceGame != null){
                scratchInstanceGame.setStatus(StatusGameEnum.EN_COURS);
                scratchInstanceGame.getSessions().get(0).setOuvert(true);
                scratchInstanceGameRepository.save(scratchInstanceGame);
                response.setBody(scratchInstanceGame);
            }else{
                messages.add("date was not registered in Database");
//                messages.add(messageSource.getMessage("errors.not-found.gameSaas", null, locale));
                response.setBody(null);
            }

        }catch (Exception e) {
            response.setStatus( HttpStatus.BAD_REQUEST);
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
    public ResponseDto<List<ScratchInstanceGame>> getAll(Locale locale) throws DAOException {
        ResponseDto<List<ScratchInstanceGame>> response = new ResponseDto<>();
        List<String> messages = new ArrayList<>();
        try {
            List<ScratchInstanceGame> scratchFormatGains = scratchInstanceGameRepository.findAll();
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
    public ResponseDto<ScratchInstanceGame> endInstance(LocalDateTime endDate, Locale locale) {
        ResponseDto<ScratchInstanceGame> response = new ResponseDto<>();
        List<String> messages = new ArrayList<String>();
        try {
            System.out.println("scratchInstanceGame");
            ScratchInstanceGame scratchInstanceGame = scratchInstanceGameRepository.findByEndDate(endDate);

            if (scratchInstanceGame != null){
                System.out.println(scratchInstanceGame);

                scratchInstanceGame.setStatus(StatusGameEnum.TERMINER);
                scratchInstanceGame.getSessions().get(scratchInstanceGame.getNumberOfSession()).setOuvert(false);
                scratchInstanceGameRepository.save(scratchInstanceGame);
                response.setBody(scratchInstanceGame);
            }else{
                messages.add("date was not registered in Database");
//                messages.add(messageSource.getMessage("errors.not-found.gameSaas", null, locale));
                response.setBody(null);

        }
        }catch (Exception e) {
            response.setStatus( HttpStatus.BAD_REQUEST);
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
    @Scheduled(fixedRate = 60000)
    public ResponseDto<ScratchInstanceGame> desactiveSession(LocalDateTime startDate, Locale locale) {

        ResponseDto<ScratchInstanceGame> response = new ResponseDto<>();
        List<String> messages = new ArrayList<String>();
        try{
           ScratchInstanceGame scratchInstanceGame = scratchInstanceGameRepository.findByStartDate(startDate);


           if (scratchInstanceGame!= null){

            List<Session> session=scratchInstanceGame.getSessions();

            for (int i = 0 ;i<(scratchInstanceGame.getNumberOfSession()-1);i++){
                if (session.get(i).getEndTime().equals(LocalDateTime.now())){
                    session.get(i).setOuvert(false);
                    session.get(i+1).setOuvert(true);
                } 
                 else {
                   messages.add("the name was not registered in Database");
//                messages.add(messageSource.getMessage("errors.not-found.gameSaas", null, locale));
                   response.setBody(null);
               }
            }
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
    public ResponseDto<List<ScratchInstanceGame>> getAllInstance(Locale locale) {
        ResponseDto<List<ScratchInstanceGame>> response = new ResponseDto<>();
        List<String> messages = new ArrayList<>();
        try {
            List<ScratchInstanceGame> scratchInstanceGames = scratchInstanceGameRepository.findAll();
            response.setBody(scratchInstanceGames);
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
    public ResponseDto<ScratchInstanceGame> findInstanceByName(String name, Locale locale) {
        ResponseDto<ScratchInstanceGame> responseDto = new ResponseDto<>();
        List<String> messages = new ArrayList<>();
        try{
            ScratchInstanceGame InstanceGame= scratchInstanceGameRepository.findByName(name);
            if (InstanceGame != null){
                responseDto.setBody(InstanceGame);
            }else {
                messages.add("the name was not registered in Database");
//                messages.add(messageSource.getMessage("errors.not-found.gameSaas", null, locale));
               responseDto.setBody(null);
            } }catch (DAOException e){
            responseDto.setStatus(e.getStatus());
            messages.add(e.getMessage());
        }
        if(messages.isEmpty()){
            responseDto.setStatus(HttpStatus.OK);
        }else if (responseDto.getStatus() == null){
            responseDto.setStatus(HttpStatus.BAD_REQUEST);
        }
        responseDto.setMessages(messages);
        return responseDto;
    }



    @Override
    public ResponseDto<ScratchInstanceGame> findInstanceByStartDate(LocalDateTime startDate, Locale locale) {
        ResponseDto<ScratchInstanceGame> responseDto = new ResponseDto<>();
        List<String> messages = new ArrayList<>();
        try{
            ScratchInstanceGame InstanceGame= scratchInstanceGameRepository.findByStartDate(startDate);
            if (InstanceGame != null){
                responseDto.setBody(InstanceGame);
            }else {
                messages.add("no instance was started in this starDate on the dataBase");

                responseDto.setBody(null);
            } }catch (DAOException e){
            responseDto.setStatus(e.getStatus());
            messages.add(e.getMessage());
        }
        if(messages.isEmpty()){
            responseDto.setStatus(HttpStatus.OK);
        }else if (responseDto.getStatus() == null){
            responseDto.setStatus(HttpStatus.BAD_REQUEST);
        }
        responseDto.setMessages(messages);
        return responseDto;
    }



    @Override
    public ResponseDto<ScratchInstanceGame> findInstanceByEndDate(LocalDateTime endDate, Locale locale) {
        ResponseDto<ScratchInstanceGame> responseDto = new ResponseDto<>();
        List<String> messages = new ArrayList<>();
        try{
            ScratchInstanceGame InstanceGame= scratchInstanceGameRepository.findByEndDate(endDate);
            if (InstanceGame != null){
                responseDto.setBody(InstanceGame);
            }else {
                messages.add("no instance was ended in this endDate on the dataBase");

                responseDto.setBody(null);
            } }catch (DAOException e){
            responseDto.setStatus(e.getStatus());
            messages.add(e.getMessage());
        }
        if(messages.isEmpty()){
            responseDto.setStatus(HttpStatus.OK);
        }else if (responseDto.getStatus() == null){
            responseDto.setStatus(HttpStatus.BAD_REQUEST);
        }
        responseDto.setMessages(messages);
        return responseDto;
    }





}

