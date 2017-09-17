package intercalationJavaHTML;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * Class that forms table for subgroup with marks and presents of student;
 **/

public class ControlInformationTable extends Table {

    public static final String addNewColButtonName = "add-col-button";

    /*Classes for cells with lab marks with coefficient*/
    private static final String coef_1_0 = " coef-10";
    private static final String coef_0_8 = " coef-08";
    private static final String coef_0_6 = " coef-06";
    private static final String coef_0_4 = " coef-04";
    private static final String coef_0_0 = " coef-00";

    /*Classes for type of content */
    public static final String labMarkTypeOfContent   = "lab-mark";
    public static final String testMarkTypeOfContent  = "test-mark";
    public static final String studNameTypeOfContent  = "stud-name";
    public static final String bonusMarkTypeOfContent = "bonus-mark";
    public static final String buttonTypeOfContent = "button-cell-div";
    public static final String presenceTypeOfContent = "presence-cell";


    public static final String absentClass = "absent";
    public static final String presentClass = "present";

    private static final String divClassName = "stud-name";
    private static final String divClasseMail = "stud-eMail-ln";
    private static final String divClassGitURL = "stud-gitHub-ln";

    private int amountColOfLabs = 0;
    private int amountColOfTests = 0;
    private int amountColOfClass = 0;

    public ControlInformationTable(String idTable) {
        super(idTable);
    }

    public ControlInformationTable(){}

    public int getAmountColOfClass() {
        return amountColOfClass;
    }

    public void setAmountColOfClass(int amountColOfClass) {
        this.amountColOfClass = amountColOfClass;
    }

    public int getAmountColOfLabs() {
        return amountColOfLabs;
    }

    public int getAmountColOfTests() {
        return amountColOfTests;
    }

    public void setAmountColOfLabs(int amountColOfLabs) {
        this.amountColOfLabs = amountColOfLabs;
    }

    public void setAmountColOfTests(int amountColOfTests) {
        this.amountColOfTests = amountColOfTests;
    }

    public void addStudentInformationCell(String fullName, String eMail, String gitURL) {
        Element rootElement;

        try {
            rootElement = this.getElementForPlacingCell();
        }
        catch (IndexOutOfBoundsException ex) {
            return;
        }

        if (rootElement == null)
            return;

        rootElement = rootElement.addElement("td")
            .addAttribute("class", Table.cellClass +
                    " " + ControlInformationTable.studNameTypeOfContent +
                    " " + Table.dataCell);

        rootElement.add(this.getDivCell(divClassName, fullName));
        rootElement.add(this.getDivCell(divClasseMail, eMail));
        rootElement.add(this.getDivCell(divClassGitURL, gitURL));
        return;
    }

    /*
*Adds button to cell? that adds new column
 */
    public void addButton() {
        Element cell = this.addCell(ControlInformationTable.buttonTypeOfContent + " " + ControlInformationTable.headerCell, " ");

        Element button = DocumentHelper.createElement("button")
                .addAttribute("class", ControlInformationTable.addNewColButtonName)
                .addText("Добавить");
        Element row = (Element) document.getRootElement().content().get(0);
        Element rootForButton = (Element) row.content().get(row.content().size() - 1);
        rootForButton.add(button);
    }

    private String getCoefficientForLabClass(Double coefficient) {
        if (coefficient == null)
            return "";
        else if (coefficient == -1)
            return "";
        else if (coefficient == 1)
            return ControlInformationTable.coef_1_0;
        else if (coefficient == 0.8)
            return ControlInformationTable.coef_0_8;
        else if (coefficient == 0.6)
            return ControlInformationTable.coef_0_6;
        else if (coefficient == 0.4)
            return ControlInformationTable.coef_0_4;
        else if (coefficient == 0.0)
            return ControlInformationTable.coef_0_0;
        else return "";
    }

    public Element addCell(String typeOfContent, String content, Double coefficient) {
        Element rootElement;

        try {
            rootElement = this.getElementForPlacingCell();
        }
        catch (IndexOutOfBoundsException ex) {
            return null;
        }

        String coefficientClass = this.getCoefficientForLabClass(coefficient);

        if (rootElement == null || coefficientClass == null)
            return null;

        return rootElement.addElement("td")
                .addAttribute("class", Table.cellClass + " " + typeOfContent + coefficientClass)
                .addText(content);
    }

    @Override
    public Element addCell(String typeOfContent, String content) {
        return this.addCell(typeOfContent, content, null);
    }
}

