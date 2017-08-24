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

    private static final int lab = 0;
    private static final int test = 1;
    private static final int bonus = 2;


    /*Table id must be table-type-group_number-subgroup.*/
    public static final String codeTableClassName = "table";

    private static final String contentMarkCellOpen = "<button class=\">" + TableGenerator.presanceCell + "\">";
    private static final String contentPresanceCellOpen = "<input type=\"text\" class=\"" + TableGenerator.markCell + "\">";
    private static final String contentMarkCellClose = "</button>";
    private static final String contentPresanceCellClose = "</input>";


    private static final String markCell = "mrk-cell-content";
    private static final String presanceCell = "prsnc-cell-content";



    public TableGenerator(){}

    public void createMarksTable(SubGroup subGroup) {


        try {

            String tableId = TableGenerator.codeTableClassName + "-" + "marks" + "-" + subGroup.getGroup().getNumberOfGroup() + "-" + subGroup.getSubGroupNumber();
            TableGeneratorTool tableGeneratorTool = new TableGeneratorTool(tableId);

            this.setHeaderToMarkTable(tableGeneratorTool, subGroup);

            // Pretty print the document to System.out
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer;
            writer = new XMLWriter( System.out, format );
            writer.write(tableGeneratorTool.getDocument());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
    @returns structure of the header, which contains numbers of lab, test and bonus
     */

    private ArrayList<ArrayList<Integer>> setHeaderToMarkTable(TableGeneratorTool tableGeneratorTool, SubGroup subGroup) {
        ArrayList<ArrayList<Integer>> structure = new ArrayList<ArrayList<Integer>>();
        List<IssuedLab> issuedLabList = subGroup.getIssuedLabsList();
        Integer amountOfTests = subGroup.getGroup().getAmountOfTest();

        tableGeneratorTool.addRow();

        structure.add(TableGenerator.lab, new ArrayList<Integer>());
        for (IssuedLab issuedLab: issuedLabList) {
            tableGeneratorTool.addCell(TableGeneratorTool.headerTypeOfContent, "Лабораторная " + issuedLab.getLabDescription().getNumberOfLab());
            structure.get(TableGenerator.lab).add(issuedLab.getLabDescription().getNumberOfLab());
        }

        structure.add(TableGenerator.test, new ArrayList<Integer>());
        for (int i = 1; i <= amountOfTests; i++) {
            tableGeneratorTool.addCell(TableGeneratorTool.headerTypeOfContent, "Тест " + i);
            structure.get(TableGenerator.test).add(i);
        }

        tableGeneratorTool.addCell(TableGeneratorTool.headerTypeOfContent, "Бонус");

        return structure;
    }

}
