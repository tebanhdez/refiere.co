package co.refiere.models;

import org.junit.Test;

import co.refiere.dao.RefiereLapseDao;
import co.refiere.dao.RefierePlanDao;

public class RefierePlanTest {
    
    @Test
    public void testCreateRefierePlanStructure() {

        RefierePlanDao planDao = new RefierePlanDao();
        RefiereLapseDao lapseDao = new RefiereLapseDao();

        Lapse semanal = lapseDao.findBylapseById(1);
        Lapse quincenal = lapseDao.findBylapseById(2);
        Lapse mensual = lapseDao.findBylapseById(3);
        Lapse bimestral = lapseDao.findBylapseById(4);

        Plan basic = planDao.findByPlanById(0);
        Plan enterprice = planDao.findByPlanById(1);
        Plan corporate = planDao.findByPlanById(2);

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