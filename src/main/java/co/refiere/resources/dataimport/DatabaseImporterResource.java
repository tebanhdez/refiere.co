package co.refiere.resources.dataimport;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

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
            XSSFSheet sheet = workbook.getSheetAt(0);
            System.out.println(sheet.getSheetName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}