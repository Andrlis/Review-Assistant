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
        ArrayList<Student> studInfoList = new ArrayList<>();

        FileInputStream fileStream = new FileInputStream(file);
        Workbook workbook = new HSSFWorkbook(fileStream);

        int numOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numOfSheets; i++) {
            Sheet dataSheet = workbook.getSheetAt(i);

            for (Row currentRow : dataSheet) {
                Student student = new Student();

                student.setFulName(currentRow.getCell(0).getStringCellValue());
                student.seteMail(currentRow.getCell(1).getStringCellValue());
                student.setGitURL(currentRow.getCell(2).getStringCellValue());

                studInfoList.add(student);
            }
        }

        return studInfoList;
    }
}