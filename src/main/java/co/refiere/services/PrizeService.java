package co.refiere.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.NoContentException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import co.refiere.dao.PrizeDao;
import co.refiere.models.Prize;
import co.refiere.resources.base.PrizeObjectData;

public class PrizeService {
    private static final Log LOGGER = LogFactory.getLog(PrizeService.class);
    
    public java.util.List<PrizeObjectData> getAllPrizes() throws NoContentException{
        PrizeDao prizeDao = new PrizeDao();
        List<PrizeObjectData> prizes = getSimplifiedPrizes(prizeDao.findAllPrizes());
        if(prizes == null){
            LOGGER.error("Plans not found");
            throw new NoContentException("\"Error \": \"Prizes not found\"");
        }
        return prizes;
    }
    
    private List<PrizeObjectData> getSimplifiedPrizes(List<Prize> allPrizes) {
        List<PrizeObjectData> simplePrizes = new ArrayList<>();
        PrizeDao prizeDao = new PrizeDao();
        for(Prize prizeInstance : allPrizes){
            PrizeObjectData prize = new PrizeObjectData(prizeInstance.getId());
            prize.setDescription(prizeInstance.getDescription());
            simplePrizes.add(prize);
        }
        return simplePrizes;
    }
}
