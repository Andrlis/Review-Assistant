package intercalationJavaHTML;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import Data.Lab.IssuedLab;
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


    /*
    @returns structure of the header, which contains numbers of lab, test and bonus
     */

    private ArrayList<ArrayList<Integer>> setHeaderToMarkTable(MarkTable markTable, SubGroup subGroup) {
        ArrayList<ArrayList<Integer>> structure = new ArrayList<ArrayList<Integer>>();
        List<IssuedLab> issuedLabList = subGroup.getIssuedLabsList();
        Integer amountOfTests = subGroup.getGroup().getAmountOfTest();
        Integer amountOfLabs = subGroup.getIssuedLabsList().size();

        markTable.addRow();

        //return structure;

        markTable.addCell(MarkTable.studNameTypeOfContent + " " + MarkTable.headerCell, "Студент");

       // structure.add(TableGenerator.lab, new ArrayList<Integer>());
        for (int i = 1; i <= amountOfLabs; i++) {
            markTable.addCell(MarkTable.labMarkTypeOfContent + " " + MarkTable.headerCell, "Лабораторная " + i);
          //  structure.get(TableGenerator.lab).add(issuedLab.getLabDescription().getNumberOfLab());
        }

        //structure.add(TableGenerator.test, new ArrayList<Integer>());
        for (int i = 1; i <= amountOfTests; i++) {
            markTable.addCell(MarkTable.testMarkTypeOfContent  + " " + MarkTable.headerCell, "Тест " + i);
            //structure.get(TableGenerator.test).add(i);
        }

        markTable.addCell(MarkTable.bonusMarkTypeOfContent  + " " + MarkTable.headerCell, "Бонус");

        return structure;
    }

}
