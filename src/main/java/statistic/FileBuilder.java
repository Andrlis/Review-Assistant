package statistic;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FileBuilder {
    //private static final Logger logger = Logger.getLogger(FileBuilder.class);

    private File getFile(String file_path) throws IOException {
        File file = new File(file_path);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    void writeStatistic(String f_path, List<StudentStatistic> statistics)
            throws IOException {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("Statistic");

        int rowNum = 0;

        Row headerRow = sheet.createRow(rowNum);
        headerRow.createCell(0).setCellValue("ФИО");
        headerRow.createCell(1).setCellValue("ЧП_ЛР");
        headerRow.createCell(2).setCellValue("НЗ_ЛР");

        for (StudentStatistic statistic : statistics) {
            setRowValues(sheet, ++rowNum, statistic);
        }

        FileOutputStream outputStream = new FileOutputStream(getFile(f_path));
        book.write(outputStream);

    }

    private void setRowValues(Sheet sheet, int rowNumber, StudentStatistic statistic) {
        Row currentRow = sheet.createRow(rowNumber);
        currentRow.createCell(0).setCellValue(statistic.studentFullName);
        currentRow.createCell(1).setCellValue(statistic.numberOfMissedClasses);
        currentRow.createCell(2).setCellValue(statistic.numberOfLabs);
    }

}