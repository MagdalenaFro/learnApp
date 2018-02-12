/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import model.DatabaseDTO;
import model.Dictionary;
import model.QueryFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Magdalena
 */
public class Exporter {

    private DatabaseControler databaseControler;

    public Boolean exportDoneQuestionToXls() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Done question");

        Integer rowNumer = 0;

        databaseControler = new DatabaseControler();
        List<DatabaseDTO> doneQuestions = databaseControler.findRecord(Dictionary.QUESTIONS_DONE, new QueryFactory().findAllRecords());

        for (DatabaseDTO record : doneQuestions) {
            Row row = sheet.createRow(rowNumer++);
            Integer columnNumber = 0;
            String[] columnsValues = convertFromDatabaseDTOtoArray(record);

            for (String columns : columnsValues) {
                Cell cell = row.createCell(columnNumber++);
                cell.setCellValue(columns);
            }
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(createFileName());
            workbook.write(fileOutputStream);
            workbook.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private String[] convertFromDatabaseDTOtoArray(DatabaseDTO databaseDTO) {
        String[] arrayWithValues = new String[10];
        arrayWithValues[0] = databaseDTO.getId().toString();
        arrayWithValues[1] = databaseDTO.getQuestion();
        arrayWithValues[2] = databaseDTO.getAnswer();
        arrayWithValues[3] = databaseDTO.getNumberOfSlide();
        arrayWithValues[4] = databaseDTO.getComments();
        return arrayWithValues;

    }

    private String createFileName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        StringBuilder builder = new StringBuilder();
        builder.append(Dictionary.DONE_QUESTION_EXPORT);
        builder.append("_");
        builder.append(sdf.format(timestamp));
        builder.append(Dictionary.FILE_EXTENTION);
        return builder.toString();
    }

}
