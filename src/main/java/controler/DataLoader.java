package controler;

import java.io.File;
import java.io.IOException;
import model.DatabaseDTO;
import model.Dictionary;
import model.QueryFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author Magdalena
 */
public class DataLoader {

    private Sheet sheet;
    private Integer rows;

    private DatabaseDTO databaseDTO;

    public void importDataFromFileToDatabase(String pathToFile) throws IOException, InvalidFormatException {

        Workbook workbook = WorkbookFactory.create(new File(pathToFile));
        readXlsProperties(workbook);

    }

    private void readXlsProperties(Workbook workbook) {
        sheet = workbook.getSheetAt(0);
        rows = sheet.getPhysicalNumberOfRows();
        iterateForEachRow();
    }

    private void iterateForEachRow() {
        for (int i = 0; i < 10 || i < rows; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                readLineAndCreateDBRecord(row);
            }
        }
    }

    private void readLineAndCreateDBRecord(Row row) {
        databaseDTO = new DatabaseDTO();
        databaseDTO.setQuestion(row.getCell(0).getStringCellValue());
        databaseDTO.setAnswer(row.getCell(1).getStringCellValue());
        databaseDTO.setNumberOfSlide(row.getCell(2).getStringCellValue());
        databaseDTO.setComments(row.getCell(3).getStringCellValue());

        System.out.println(databaseDTO.getQuestion());
        System.out.println(databaseDTO.getAnswer());
        System.out.println(databaseDTO.getNumberOfSlide());
        System.out.println(databaseDTO.getComments());

        new QueryFactory().addQuestionToDatabase(databaseDTO, Dictionary.QUESTIONS_TO_DO_TABLE);
    }

//    public static void main(String[] args) {
//        java.nio.file.Path currentPath = Paths.get("");
//        String s = currentPath.toAbsolutePath().toString();
//        System.out.println("!!!!!!!!!!");
//        System.out.println(s);
//
//    }
}
