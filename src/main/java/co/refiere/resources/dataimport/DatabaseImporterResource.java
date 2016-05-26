package co.refiere.resources.dataimport;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import co.refiere.dao.CompanyDatabaseDao;
import co.refiere.dao.PersonDao;
import co.refiere.models.Person;

@Path("/database")
public class DatabaseImporterResource {

    
    @POST
    @Path("/import")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void insertDataInDB2(
        @FormDataParam("file") InputStream uploadedInputStream,
        @FormDataParam("file") FormDataContentDisposition fileDetail) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(uploadedInputStream);
            excelReader(workbook);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void excelReader(XSSFWorkbook workbook){
      
      Sheet sheet = workbook.getSheetAt(0);
      Iterator<Row> sheetRows = sheet.iterator();
       
      while (sheetRows.hasNext()) {
          Row nextRow = sheetRows.next();          
          setValuesForPerson(nextRow);
      }
       
      try {
        workbook.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    private void setValuesForPerson( Row nextRow) {
      PersonDao personDao = new PersonDao();
      Person personToIntroduce = new Person();
      personToIntroduce.setName(getNameToIntroduce(nextRow));
      personToIntroduce.setLastName(getLastNameToIntroduce(nextRow));
      personToIntroduce.setEmail(getEmailToIntroduce(nextRow));
      personToIntroduce.setPhoneNumber(getPhoneNumberToIntroduce(nextRow));
      personToIntroduce.setIdentificationCardNumber(getIdentificationCardNumberToIntroduce(nextRow));
      personDao.save(personToIntroduce);
    }

    private String getIdentificationCardNumberToIntroduce(Row nextRow) {
      nextRow.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
      return nextRow.getCell(4).getStringCellValue();
    }

    private String getPhoneNumberToIntroduce(Row nextRow) {
      nextRow.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
      return nextRow.getCell(3).getStringCellValue();
    }

    private String getEmailToIntroduce(Row nextRow) {
      return nextRow.getCell(2).getStringCellValue();
    }

    private String getLastNameToIntroduce(Row nextRow) {
      return nextRow.getCell(1).getStringCellValue();
    }

    private String getNameToIntroduce(Row nextRow) {
      return nextRow.getCell(0).getStringCellValue();
    }
}