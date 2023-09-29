package cm.connect.technology.scratchcard.enums;

import cm.connect.technology.scratchcard.dto.Case;

import java.util.HashMap;
import java.util.Map;

public class InitializeCase {
    public static Case getCase(FormatScratchEnum key){
        Map<FormatScratchEnum,Case> mapOperations = new HashMap();
        mapOperations.put(FormatScratchEnum.FORMAT_2_TO_6, new Case(6, 2));
        mapOperations.put(FormatScratchEnum.FORMAT_3_TO_6, new Case(6, 3));
        mapOperations.put(FormatScratchEnum.FORMAT_3_TO_9, new Case(9, 3));
        return mapOperations.get(key);
    }
}

