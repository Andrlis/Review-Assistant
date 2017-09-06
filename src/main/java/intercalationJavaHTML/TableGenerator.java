package intercalationJavaHTML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import Data.Lab.IssuedLab;
import Data.Mark.LabMark;
import Data.Mark.TestMark;
import Data.Student;
import Data.UniversityClass;
import Resources.HibernateShell;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import Data.Group.SubGroup;


/**
 * Created by Greenwalrus on 23.08.2017.
 * Class for generating tables with studing statistics.
 */
public class TableGenerator {

    public static final int name = 0;
    public static final int lab = 1;
    public static final int test = 2;
    public static final int bonus = 3;

    public TableGenerator(){}

    private void addMarkCell(Table table, String attrClass, String data) {
        table.addCell(Table.dataCell + " " + Table.editableCell + " " +
                attrClass, data);
    }

    private void addPresenceCell(Table table, String attrClass, String data) {
        table.addCell(Table.dataCell + " " + Table.presenceTypeOfContent + " " +
                attrClass, data);
    }

    private FileOutputStream getFileOutputStream(String fileName) throws IOException {
        File file = new File("src\\main\\webapp\\tables\\" + fileName + ".html");

        if (!file.exists())
            file.createNewFile();

        return new FileOutputStream(file, false);
    }

    public void createMarksTable(SubGroup subGroup) {

        try {

            String tableId = "table-" + subGroup.getGroup().getNumberOfGroup() + "-" + subGroup.getSubGroupNumber();
            Table table = new Table(tableId);
            this.setHeaderToTable(table, subGroup);

            List<Student> studentList = subGroup.getStudentsList();
            for (Student student: studentList)
                this.addStudentToTable(table, student);

            // Pretty print the document to System.out
            //file.
            FileOutputStream fileOutputStream = this.getFileOutputStream(tableId);
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter( fileOutputStream, format );
            writer.write(table.getDocument());
            fileOutputStream.close();
            writer.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addStudentToTable(Table table, Student student) {
        table.addRow();

        table.addCell(Table.studNameTypeOfContent + " " +
                Table.dataCell, student.getFulName());

        for(int i = 1; i <= table.getAmountColOfLabs(); i++) {
            LabMark labMark = student.getLabMark(HibernateShell.getLabsKeeper().getLab(i));
            Integer markInt = labMark.getMark();

            String markString = "";
            if (markInt != -1)
                markString = markInt.toString();

            this.addMarkCell(table, Table.labMarkTypeOfContent, markString);
        }

        for(int i = 1; i <= table.getAmountColOfTests(); i++) {
            TestMark testMark = student.getTestMark(i);
            Integer markInt = testMark.getMark();
            String markString = "";
            if (markInt != -1)
                markString = markInt.toString();

            this.addMarkCell(table, Table.testMarkTypeOfContent, markString);
        }

        String bonusMarkToString = "";
        Integer bonusMark = student.getBonusMark();
        if (bonusMark != null)
            bonusMarkToString = bonusMark.toString();
        this.addMarkCell(table, Table.bonusMarkTypeOfContent, bonusMarkToString);

        List<UniversityClass> missedClassesList = student.getMissedUniversityClassesList();
        for (int i = 0; i < table.getAmountColOfClass(); i++) {
            if (missedClassesList == null || !missedClassesList.contains(student.getSubGroup().getUniversityClassesList().get(i)))
                this.addPresenceCell(table, Table.presentClass, "");
            else
                this.addPresenceCell(table, Table.absentClass, "н");
        }
    }

    /*
    @returns structure of the header, which contains numbers of lab, test and bonus
     */

    private ArrayList<ArrayList<Integer>> setHeaderToTable(Table table, SubGroup subGroup) {
        ArrayList<ArrayList<Integer>> structure = new ArrayList<ArrayList<Integer>>();

        table.setAmountColOfLabs(subGroup.getIssuedLabsList().size());
        table.setAmountColOfTests(subGroup.getGroup().getAmountOfTest());
        table.setAmountColOfClass(subGroup.getUniversityClassesList().size());

        table.addRow();

        table.addCell(Table.studNameTypeOfContent + " " + Table.headerCell, "Студент");
       // structure.add(TableGenerator.lab, new ArrayList<Integer>());
        for (int i = 1; i <= table.getAmountColOfLabs(); i++) {
            table.addCell(Table.labMarkTypeOfContent + " " + Table.headerCell, "Лабораторная " + i);
          //  structure.get(TableGenerator.lab).add(issuedLab.getLabDescription().getNumberOfLab());
        }

        //structure.add(TableGenerator.test, new ArrayList<Integer>());
        for (int i = 1; i <= table.getAmountColOfTests(); i++) {
            table.addCell(Table.testMarkTypeOfContent  + " " + Table.headerCell, "Тест " + i);
            //structure.get(TableGenerator.test).add(i);
        }

        table.addCell(Table.bonusMarkTypeOfContent  + " " + Table.headerCell, "Бонус");
        table.addButton();


        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
        for (int i = 0; i < table.getAmountColOfClass(); i++)
            table.addCell(Table.presenceTypeOfContent + " " + Table.headerCell, dateFormat.format(subGroup.getUniversityClassesList().get(i).getDate()));


        return structure;
    }
}
