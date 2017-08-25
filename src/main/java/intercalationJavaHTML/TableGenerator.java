package intercalationJavaHTML;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import Data.Lab.IssuedLab;
import Data.Lab.Lab;
import Data.Lab.LabsKeeper;
import Data.Mark.LabMark;
import Data.Mark.TestMark;
import Data.Student;
import Resources.HibernateShell;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import Data.Group.GroupsKeeper;
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

    public void createMarksTable(SubGroup subGroup) {

        try {

            String tableId = "table-marks" + "-" + subGroup.getGroup().getNumberOfGroup() + "-" + subGroup.getSubGroupNumber();
            MarkTable markTable = new MarkTable(tableId);
            this.setHeaderToMarkTable(markTable, subGroup);

            List<Student> studentList = subGroup.getStudentsList();
            for (Student student: studentList)
                this.addStudentToTable(markTable, student);

            // Pretty print the document to System.out
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer;
            writer = new XMLWriter( System.out, format );
            writer.write(markTable.getDocument());
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addStudentToTable(MarkTable markTable, Student student) {
        markTable.addRow();

        markTable.addCell(MarkTable.studNameTypeOfContent, student.getFulName());

        for(int i = 1; i <= markTable.getAmountColOfLabs(); i++) {
            LabMark labMark = student.getLabMark(HibernateShell.getLabsKeeper().getLab(i));
            Integer markInt = labMark.getMark();

            String markString = "";
            if (markInt != -1)
                markString = markInt.toString();

            markTable.addCell(MarkTable.labMarkTypeOfContent, markString, labMark.getCoefficient());
        }

        for(int i = 1; i <= markTable.getAmountColOfTests(); i++) {
            TestMark testMark = student.getTestMark(i);
            Integer markInt = testMark.getMark();
            String markString = "";
            if (markInt != -1)
                markString = markInt.toString();

            markTable.addCell(MarkTable.testMarkTypeOfContent, markString);
        }

        String bonusMarkToString = "";
        Integer bonusMark = student.getBonusMark();
        if (bonusMark != null)
            bonusMarkToString = bonusMark.toString();
        markTable.addCell(MarkTable.bonusMarkTypeOfContent, bonusMarkToString);

    }

    /*
    @returns structure of the header, which contains numbers of lab, test and bonus
     */

    private ArrayList<ArrayList<Integer>> setHeaderToMarkTable(MarkTable markTable, SubGroup subGroup) {
        ArrayList<ArrayList<Integer>> structure = new ArrayList<ArrayList<Integer>>();
        List<IssuedLab> issuedLabList = subGroup.getIssuedLabsList();
        markTable.setAmountColOfLabs(subGroup.getIssuedLabsList().size());
        markTable.setAmountColOfTests(subGroup.getGroup().getAmountOfTest());

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
        return structure;
    }
}
