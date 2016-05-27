package co.refiere.models;

import org.junit.Test;

import co.refiere.dao.PrizeDao;

public class RefierePrizeTest {
    
    @Test
    public void testCreateRefierePrizeStructure() {
        
        PrizeDao prizeDao = new PrizeDao();
        
        Prize prize = new Prize();
        
        prize.setDescription("Improve service/product");
        
        prizeDao.save(prize);
    }
}
