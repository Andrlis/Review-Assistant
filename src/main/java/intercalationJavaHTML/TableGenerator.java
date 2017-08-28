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
import Data.Lab.Lab;
import Data.Lab.LabsKeeper;
import Data.Mark.LabMark;
import Data.Mark.TestMark;
import Data.Student;
import Data.UniversityClass;
import Resources.HibernateShell;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import Data.Group.GroupsKeeper;
import Data.Group.SubGroup;
import org.omg.CORBA.Request;


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

    private void addMarkCell(MarkTable markTable, String attrClass, String data) {
        markTable.addCell(MarkTable.dataCell + " " + MarkTable.editableCell + " " +
                attrClass, data);
    }

    private void addPresenceCell(MarkTable markTable, String attrClass, String data) {
        markTable.addCell(MarkTable.dataCell + " " + MarkTable.presenceTypeOfContent + " " +
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
            MarkTable markTable = new MarkTable(tableId);
            this.setHeaderToMarkTable(markTable, subGroup);

            List<Student> studentList = subGroup.getStudentsList();
            for (Student student: studentList)
                this.addStudentToTable(markTable, student);

            // Pretty print the document to System.out
            //file.
            FileOutputStream fileOutputStream = this.getFileOutputStream(tableId);
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter( fileOutputStream, format );
            writer.write(markTable.getDocument());
            fileOutputStream.close();
            writer.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addStudentToTable(MarkTable markTable, Student student) {
        markTable.addRow();

        markTable.addCell(MarkTable.studNameTypeOfContent + " " +
                MarkTable.dataCell, student.getFulName());

        for(int i = 1; i <= markTable.getAmountColOfLabs(); i++) {
            LabMark labMark = student.getLabMark(HibernateShell.getLabsKeeper().getLab(i));
            Integer markInt = labMark.getMark();

            String markString = "";
            if (markInt != -1)
                markString = markInt.toString();

            this.addMarkCell(markTable, MarkTable.labMarkTypeOfContent, markString);
        }

        for(int i = 1; i <= markTable.getAmountColOfTests(); i++) {
            TestMark testMark = student.getTestMark(i);
            Integer markInt = testMark.getMark();
            String markString = "";
            if (markInt != -1)
                markString = markInt.toString();

            this.addMarkCell(markTable, MarkTable.testMarkTypeOfContent, markString);
        }

        String bonusMarkToString = "";
        Integer bonusMark = student.getBonusMark();
        if (bonusMark != null)
            bonusMarkToString = bonusMark.toString();
        this.addMarkCell(markTable, MarkTable.bonusMarkTypeOfContent, bonusMarkToString);

        List<UniversityClass> missedClassesList = student.getMissedUniversityClassesList();
        for (int i = 0; i < markTable.getAmountColOfClass(); i++) {
            if (missedClassesList == null || !missedClassesList.contains(student.getSubGroup().getUniversityClassesList().get(i)))
                this.addPresenceCell(markTable, MarkTable.presentClass, "");
            else
                this.addPresenceCell(markTable, MarkTable.absentClass, "н");
        }
    }

    /*
    @returns structure of the header, which contains numbers of lab, test and bonus
     */

    private ArrayList<ArrayList<Integer>> setHeaderToMarkTable(MarkTable markTable, SubGroup subGroup) {
        ArrayList<ArrayList<Integer>> structure = new ArrayList<ArrayList<Integer>>();
        List<IssuedLab> issuedLabList = subGroup.getIssuedLabsList();
        markTable.setAmountColOfLabs(subGroup.getIssuedLabsList().size());
        markTable.setAmountColOfTests(subGroup.getGroup().getAmountOfTest());
        markTable.setAmountColOfClass(subGroup.getUniversityClassesList().size());

        markTable.addRow();

        markTable.addCell(MarkTable.studNameTypeOfContent + " " + MarkTable.headerCell, "Студент");
       // structure.add(TableGenerator.lab, new ArrayList<Integer>());
        for (int i = 1; i <= markTable.getAmountColOfLabs(); i++) {
            markTable.addCell(MarkTable.labMarkTypeOfContent + " " + MarkTable.headerCell, "Лабораторная " + i);
          //  structure.get(TableGenerator.lab).add(issuedLab.getLabDescription().getNumberOfLab());
        }

        //structure.add(TableGenerator.test, new ArrayList<Integer>());
        for (int i = 1; i <= markTable.getAmountColOfTests(); i++) {
            markTable.addCell(MarkTable.testMarkTypeOfContent  + " " + MarkTable.headerCell, "Тест " + i);
            //structure.get(TableGenerator.test).add(i);
        }

        markTable.addCell(MarkTable.bonusMarkTypeOfContent  + " " + MarkTable.headerCell, "Бонус");
        markTable.addButton();


        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
        for (int i = 0; i < markTable.getAmountColOfClass(); i++)
            markTable.addCell(MarkTable.presenceTypeOfContent + " " + MarkTable.headerCell, dateFormat.format(subGroup.getUniversityClassesList().get(i).getDate()));


        return structure;
    }
}
