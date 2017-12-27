package intercalationJavaHTML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.List;

import data.mark.LabMark;
import data.mark.TestMark;
import data.Student;
import data.UniversityClass;
import resources.Hibernate.HibernateCore;
import resources.Hibernate.HibernateShell;

import data.group.SubGroup;
import resources.Hibernate.HibernateShellQueryException;


/**
 * Created by Greenwalrus on 23.08.2017.
 * Class for generating tables with studing statistics.
 */
public class ControlInformationTableGenerator extends TableGenerator{

    public static final int name = 0;
    public static final int lab = 1;
    public static final int test = 2;
    public static final int bonus = 3;

    public ControlInformationTableGenerator(){}

    private void addMarkCell(ControlInformationTable table, String attrClass, String data) {
        table.addCell(ControlInformationTable.dataCell + " " + ControlInformationTable.editableCell + " " +
                attrClass, data);
    }

    private void addPresenceCell(ControlInformationTable table, String attrClass, String data) {
        table.addCell(ControlInformationTable.dataCell + " " + ControlInformationTable.presenceTypeOfContent + " " +
                attrClass, data);
    }

    @Override
    protected FileOutputStream getFileOutputStream(String fileName) throws IOException {
        File file = new File("src\\main\\webapp\\tables\\marks_and_classes\\" + fileName + ".html");

        if (!file.exists())
            file.createNewFile();

        return new FileOutputStream(file, false);
    }

    /**
     * Creates table and saves it into file with special prepared name
     * in ../webapp/tables/marks_and_classes folder
     **/
    @Override
    public void createTable(SubGroup subGroup) {
        ControlInformationTable table = new ControlInformationTable();
        setHeaderToTable(table, subGroup);
        formTable(table, subGroup);

    }

    @Override
    protected void addStudentToTable(Table tab, Student student) throws HibernateShellQueryException {
        HibernateCore hibernateCore = HibernateCore.getInstance();

        ControlInformationTable table = (ControlInformationTable) tab;
        table.addRow();
        table.addStudentInformationCell(student.getFulName(), student.geteMail(), student.getGitURL());

        for(int i = 1; i <= table.getAmountColOfLabs(); i++) {
            LabMark labMark = student.getLabMark(hibernateCore.getLabsKeeper().getLab(i));
            Integer markInt = labMark.getMark();

            String markString = "";
            if (markInt != -1)
                markString = markInt.toString();

            this.addMarkCell(table, ControlInformationTable.labMarkTypeOfContent, markString);
        }

        for(int i = 1; i <= table.getAmountColOfTests(); i++) {
            TestMark testMark = student.getTestMark(i);
            Integer markInt = testMark.getMark();
            String markString = "";
            if (markInt != -1)
                markString = markInt.toString();

            this.addMarkCell(table, ControlInformationTable.testMarkTypeOfContent, markString);
        }

        String bonusMarkToString = "";
        Integer bonusMark = student.getBonusMark();
        if (bonusMark != null)
            bonusMarkToString = bonusMark.toString();
        this.addMarkCell(table, ControlInformationTable.bonusMarkTypeOfContent, bonusMarkToString);

        List<UniversityClass> missedClassesList = student.getMissedUniversityClassesList();
        for (int i = 0; i < table.getAmountColOfClass(); i++) {
            if (missedClassesList == null || !missedClassesList.contains(student.getSubGroup().getUniversityClassesList().get(i)))
                this.addPresenceCell(table, ControlInformationTable.presentClass, "");
            else
                this.addPresenceCell(table, ControlInformationTable.absentClass, "н");
        }
    }


    /*
    @returns structure of the header, which contains numbers of lab, test and bonus
     */

    protected void setHeaderToTable(ControlInformationTable table, SubGroup subGroup) {
        table.setAmountColOfLabs(subGroup.getIssuedLabsList().size());
        table.setAmountColOfTests(subGroup.getGroup().getAmountOfTest());
        table.setAmountColOfClass(subGroup.getUniversityClassesList().size());

        table.addRow();

        table.addCell(ControlInformationTable.studNameTypeOfContent + " " + ControlInformationTable.headerCell, "Студент");
        // structure.add(ControlInformationTableGenerator.lab, new ArrayList<Integer>());
        for (int i = 1; i <= table.getAmountColOfLabs(); i++) {
            table.addCell(ControlInformationTable.labMarkTypeOfContent + " " + ControlInformationTable.headerCell, "Лабораторная " + i);
            //  structure.get(ControlInformationTableGenerator.lab).add(issuedLab.getLabDescription().getNumberOfLab());
        }

        //structure.add(ControlInformationTableGenerator.test, new ArrayList<Integer>());
        for (int i = 1; i <= table.getAmountColOfTests(); i++) {
            table.addCell(ControlInformationTable.testMarkTypeOfContent  + " " + ControlInformationTable.headerCell, "Тест " + i);
            //structure.get(ControlInformationTableGenerator.test).add(i);
        }

        table.addCell(ControlInformationTable.bonusMarkTypeOfContent  + " " + ControlInformationTable.headerCell, "Бонус");
        table.addButton();

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
        for (int i = 0; i < table.getAmountColOfClass(); i++)
            table.addCell(ControlInformationTable.presenceTypeOfContent + " " + ControlInformationTable.headerCell, dateFormat.format(subGroup.getUniversityClassesList().get(i).getDate()));
    }
}
