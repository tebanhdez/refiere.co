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
        System.out.println(fileDetail.getFileName());
        
        try {

            XSSFWorkbook workbook = new XSSFWorkbook(uploadedInputStream);
            excelReader(workbook);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void excelReader(XSSFWorkbook workbook){
      PersonDao personDao = new PersonDao();
      Person personToIntroduce = new Person();
      Sheet firstSheet = workbook.getSheetAt(0);
      Iterator<Row> iterator = firstSheet.iterator();
       
      while (iterator.hasNext()) {
          Row nextRow = iterator.next();          
          personToIntroduce.setName(nextRow.getCell(0).getStringCellValue());
          personToIntroduce.setLastName(nextRow.getCell(1).getStringCellValue());
          personToIntroduce.setEmail(nextRow.getCell(2).getStringCellValue());
          nextRow.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
          personToIntroduce.setPhoneNumber(nextRow.getCell(3).getStringCellValue());
          nextRow.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
          personToIntroduce.setIdentificationCardNumber(nextRow.getCell(4).getStringCellValue());          
          personDao.save(personToIntroduce);
      }
       
      try {
        workbook.close();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
}