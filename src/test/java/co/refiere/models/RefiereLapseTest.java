package co.refiere.models;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import co.refiere.dao.RefiereLapseDao;

import java.util.List;

public class RefiereLapseTest {

    @Test
    public void testCreateRefiereLapse() {

        RefiereLapseDao refiereDao = new RefiereLapseDao();
        List<Lapse> systemLapses = refiereDao.getAllLapses();
        Assert.assertTrue("Lapses not found", systemLapses.size() == 9);
        Assert.assertEquals("Lapses not retrieved", RefiereLapse.values().length, systemLapses.size());

    }
}
