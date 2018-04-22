package resources;


import data.Student;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class StudInfoParser {
    private static final Logger logger = Logger.getLogger(StudInfoParser.class);

    public static ArrayList<Student> parseStudentInfo(File file) throws IOException {
        logger.info("Start parse students` information from file:" + file.getName());
        
        ArrayList<Student> studInfoList = new ArrayList<>();

        FileInputStream fileStream = new FileInputStream(file);
        Workbook workbook = new HSSFWorkbook(fileStream);

        int numOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numOfSheets; i++) {
            Sheet dataSheet = workbook.getSheetAt(i);

            for (Row currentRow : dataSheet) {
                Student student = new Student();

                student.setFulName(getValidValue(currentRow.getCell(0)));
                student.seteMail(getValidValue(currentRow.getCell(1)));
                student.setGitURL(getValidValue(currentRow.getCell(2)));

                studInfoList.add(student);
            }
        }

        return studInfoList;
    }

    private static String getValidValue(Cell cell) {
        if (cell == null || cell.getCellTypeEnum() != CellType.STRING)
            return "";
        else return cell.getStringCellValue();
    }
}