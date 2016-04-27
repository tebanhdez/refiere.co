package co.refiere.models;

import org.junit.Test;

import co.refiere.dao.RefiereLapseDao;
import co.refiere.dao.RefierePlanDao;

public class RefierePlanTest {
    
    @Test
    public void testCreateRefierePlanStructure() {

        RefierePlanDao planDao = new RefierePlanDao();
        RefiereLapseDao lapseDao = new RefiereLapseDao();

        Lapse semanal = lapseDao.findBylapseById(11);
        Lapse quincenal = lapseDao.findBylapseById(12);
        Lapse mensual = lapseDao.findBylapseById(13);
        Lapse bimestral = lapseDao.findBylapseById(14);

        Plan basic = planDao.findByPlanById(10);
        Plan enterprice = planDao.findByPlanById(11);
        Plan corporate = planDao.findByPlanById(12);

        basic.setLapseByCampaignLapseRef(bimestral);
        enterprice.setLapseByCampaignLapseRef(bimestral);
        corporate.setLapseByCampaignLapseRef(mensual);

        basic.setLapseByReportLapseId(mensual);
        enterprice.setLapseByReportLapseId(quincenal);
        corporate.setLapseByReportLapseId(semanal);

        planDao.save(basic);
        planDao.save(enterprice);
        planDao.save(corporate);
    }
}