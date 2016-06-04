package co.refiere.models;

import org.junit.Assert;
import org.junit.Test;

import co.refiere.dao.PrizeDao;

import java.util.List;

public class RefierePrizeTest {
    
    @Test
    public void testCreateRefierePrizeStructure() {
        PrizeDao prizeDao = new PrizeDao();
        List<Prize> prizes = prizeDao.findAllPrizes();
        Assert.assertTrue("Prizes not found", prizes.size() == 5);
    }
}
