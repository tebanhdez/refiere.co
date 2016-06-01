package co.refiere.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.NoContentException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import co.refiere.dao.CompanyDatabaseDao;
import co.refiere.models.CompanyDatabase;
import co.refiere.resources.base.DatabaseObjectData;

public class DatabaseService {
    private static final Log LOGGER = LogFactory.getLog(DatabaseService.class);
    
    public java.util.List<DatabaseObjectData> getAllDatabases() throws NoContentException{
        CompanyDatabaseDao companyDatabaseDao = new CompanyDatabaseDao();
        List<DatabaseObjectData> databases = getSimplifiedDatabases(companyDatabaseDao.findAllDatabases());
        if(databases == null){
            LOGGER.error("Databases not found");
            throw new NoContentException("\"Error \": \"Prizes not found\"");
        }
        return databases;
    }
    
    private List<DatabaseObjectData> getSimplifiedDatabases(List<CompanyDatabase> allDatabases) {
        List<DatabaseObjectData> simpleDatabases = new ArrayList<>();
        CompanyDatabaseDao companyDatabaseDao = new CompanyDatabaseDao();
        for(CompanyDatabase companyDatabaseInstance : allDatabases){
            DatabaseObjectData database = new DatabaseObjectData(companyDatabaseInstance.getId());
            database.setDatabaseName(companyDatabaseInstance.getName());
            simpleDatabases.add(database);
        }
        return simpleDatabases;
    }
}
