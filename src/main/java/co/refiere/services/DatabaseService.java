package co.refiere.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.NoContentException;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import co.refiere.dao.CompanyDatabaseDao;
import co.refiere.models.CompanyDatabase;
import co.refiere.resources.base.DatabaseObjectData;
import co.refiere.resources.base.DatabaseRefRequest;

public class DatabaseService {
    private static final Log LOGGER = LogFactory.getLog(DatabaseService.class);
    
    public java.util.List<DatabaseObjectData> getAllDatabases(String userName) throws NoContentException{
        CompanyDatabaseDao companyDatabaseDao = new CompanyDatabaseDao();
        List<DatabaseObjectData> databases = getSimplifiedDatabases(companyDatabaseDao.findAllDatabasesByCompany(userName));
        if(databases == null){
            LOGGER.error("Databases not found");
            throw new NoContentException("\"Error \": \"The reference to the database not found\"");
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
    
    public Response createDatabaseRef(DatabaseRefRequest databaseRefe) {
        
    }
    
}
