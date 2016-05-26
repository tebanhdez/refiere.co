package co.refiere.resources.dataimport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import co.refiere.dao.PersonDao;
import co.refiere.models.Person;

@Path("/database")
public class DatabaseImporterResource {

  private static final String CSV_SPLIT_COMMA = ",";
  private static final String CSV_EXTENSION = ".csv";
  private static final String EXCEL_EXTENSION = ".xlsx";
  private static final int NAME_POSITION_CSV = 0;
  private static final int LAST_NAME_POSITION_CSV = 1;
  private static final int EMAIL_POSITION_CSV = 2;
  private static final int PHONE_NUMBER_POSITION_CSV = 3;
  private static final int IDENTIFICATION_CARD_NUMBER_POSITION_CSV = 4;
  private static final int IDENTIFICATION_CARD_NUMBER_ROW_NUMBER = 4;
  private static final int PHONENUMBER_ROW_NUMBER = 3;
  private static final int EMAIL_ROW_NUMBER = 2;
  private static final int LASTNAME_ROW_NUMBER = 1;
  private static final int NAME_ROW_NUMBER = 0;
  private static final int FIRST_SHEET = 0;

  @POST
  @Path("/import")
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  public void insertDataInDB2(@FormDataParam("file") InputStream uploadedInputStream,
      @FormDataParam("file") FormDataContentDisposition fileDetail) {
    try {
      if (isExcelFormat(fileDetail)) {
        excelTranslationType(uploadedInputStream);
      } else if (isCSVFormat(fileDetail)) {
        Reader reader = new InputStreamReader(uploadedInputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        CSVTranslationType(bufferedReader);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void CSVTranslationType(BufferedReader bufferedReader) throws IOException {
    String line = "";
    PersonDao personDao = new PersonDao();
    Person personToIntroduce = new Person();
    while ((line = bufferedReader.readLine()) != null) {
      String[] personData = line.split(CSV_SPLIT_COMMA);
      setValuesToDatabaseFromCSV(personDao, personToIntroduce, personData);
    }
  }

  private void setValuesToDatabaseFromCSV(PersonDao personDao, Person personToIntroduce, String[] personData) {
    personToIntroduce.setName(getNameFromCSV(personData));
    personToIntroduce.setLastName(getLastNameFromCSV(personData));
    personToIntroduce.setEmail(getEmailFromCSV(personData));
    personToIntroduce.setPhoneNumber(getPhoneNumberFromCSV(personData));
    personToIntroduce.setIdentificationCardNumber(getIdentificationCardNumberFromCSV(personData));
    personDao.save(personToIntroduce);
  }

  private String getIdentificationCardNumberFromCSV(String[] personData) {
    return personData[IDENTIFICATION_CARD_NUMBER_POSITION_CSV];
  }

  private String getPhoneNumberFromCSV(String[] personData) {
    return personData[PHONE_NUMBER_POSITION_CSV];
  }

  private String getEmailFromCSV(String[] personData) {
    return personData[EMAIL_POSITION_CSV];
  }

  private String getLastNameFromCSV(String[] personData) {
    return personData[LAST_NAME_POSITION_CSV];
  }

  private String getNameFromCSV(String[] personData) {
    return personData[NAME_POSITION_CSV];
  }

  private boolean isCSVFormat(FormDataContentDisposition fileDetail) {
    return fileDetail.getFileName().contains(CSV_EXTENSION);
  }

  private void excelTranslationType(InputStream uploadedInputStream) throws IOException {
    XSSFWorkbook workbook = new XSSFWorkbook(uploadedInputStream);
    excelReader(workbook);
  }

  private boolean isExcelFormat(FormDataContentDisposition fileDetail) {
    return fileDetail.getFileName().contains(EXCEL_EXTENSION);
  }

  private void excelReader(XSSFWorkbook workbook) {
    Sheet sheet = workbook.getSheetAt(FIRST_SHEET);
    Iterator<Row> sheetRows = sheet.iterator();
    while (sheetRows.hasNext()) {
      Row nextRow = sheetRows.next();
      setValuesToDatabaseFromExcel(nextRow);
    }
  }

  private void setValuesToDatabaseFromExcel(Row nextRow) {
    PersonDao personDao = new PersonDao();
    Person personToIntroduce = new Person();
    personToIntroduce.setName(getNameFromExcel(nextRow));
    personToIntroduce.setLastName(getLastNameFromExcel(nextRow));
    personToIntroduce.setEmail(getEmailFromExcel(nextRow));
    personToIntroduce.setPhoneNumber(getPhoneNumberFromExcel(nextRow));
    personToIntroduce.setIdentificationCardNumber(getIdentificationCardNumberFromExcel(nextRow));
    personDao.save(personToIntroduce);
  }

  private String getIdentificationCardNumberFromExcel(Row nextRow) {
    nextRow.getCell(IDENTIFICATION_CARD_NUMBER_ROW_NUMBER).setCellType(Cell.CELL_TYPE_STRING);
    return nextRow.getCell(IDENTIFICATION_CARD_NUMBER_ROW_NUMBER).getStringCellValue();
  }

  private String getPhoneNumberFromExcel(Row nextRow) {
    nextRow.getCell(PHONENUMBER_ROW_NUMBER).setCellType(Cell.CELL_TYPE_STRING);
    return nextRow.getCell(PHONENUMBER_ROW_NUMBER).getStringCellValue();
  }

  private String getEmailFromExcel(Row nextRow) {
    return nextRow.getCell(EMAIL_ROW_NUMBER).getStringCellValue();
  }

  private String getLastNameFromExcel(Row nextRow) {
    return nextRow.getCell(LASTNAME_ROW_NUMBER).getStringCellValue();
  }

  private String getNameFromExcel(Row nextRow) {
    return nextRow.getCell(NAME_ROW_NUMBER).getStringCellValue();
  }
}