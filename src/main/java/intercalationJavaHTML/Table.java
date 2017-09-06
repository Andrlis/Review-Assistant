package intercalationJavaHTML;

//import javafx.scene.control.Tab;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import java.util.List;

public class Table {
    private Document document;

    protected static final String tableClass = "table-ui";
    protected static final String rowClass   = "row-ui";
    protected static final String cellClass  = "cell-ui";

    public static final String addNewColButtonName = "add-col-button";

    /*Classes for cells with lab marks with coefficient*/
    private static final String coef_1_0 = " coef-10";
    private static final String coef_0_8 = " coef-08";
    private static final String coef_0_6 = " coef-06";
    private static final String coef_0_4 = " coef-04";
    private static final String coef_0_0 = " coef-00";

    /*Classes for type of cell */
    public static final String dataCell = "data-cell";
    public static final String headerCell   = "header-cell";

    /*Classes for type of content */
    public static final String labMarkTypeOfContent   = "lab-mark";
    public static final String testMarkTypeOfContent  = "test-mark";
    public static final String studNameTypeOfContent  = "stud-name";
    public static final String bonusMarkTypeOfContent = "bonus-mark";
    public static final String buttonTypeOfContent = "button-cell-div";
    public static final String presenceTypeOfContent = "presence-cell";

    public static final String editableCell = "editable";
    //public static final String markCell = "mark";

    public static final String absentClass = "absent";
    public static final String presentClass = "present";

    private int amountColOfLabs = 0;
    private int amountColOfTests = 0;
    private int amountColOfClass = 0;


    public Table(){
        standInit();
    }

    public Table(String idTable) {
        standInit();
        document.getRootElement().addAttribute("id", idTable);
    }

    private void standInit() {
        document = DocumentHelper.createDocument();
        document.addElement("table")
                .addAttribute("class", Table.tableClass);
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

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

    private Element getElementForPlacingCell(String typeOfContent){

        return this.getLastRow();
    }

    public void addRow() {
        document.getRootElement().addElement("tr")
                .addAttribute("class", Table.rowClass);
        Element row = document.getRootElement().element("tr");
    }

    /*
    *Adds button to cell? that adds new column
     */
    public void addButton() {
        Element cell = this.addCell(Table.buttonTypeOfContent + " " + Table.headerCell, " ");

        Element button = DocumentHelper.createElement("button")
                .addAttribute("class", Table.addNewColButtonName)
                .addText("Добавить");
        Element row = (Element) document.getRootElement().content().get(0);
        Element rootForButton = (Element) row.content().get(row.content().size() - 1);
        rootForButton.add(button);
    }

    public Element addCell(String typeOfContent, String content) {
        return this.addCell(typeOfContent, content, null);
    }

    public Element addCell(String typeOfContent, String content, Double coefficient) {
        Element rootElement;

        try {
            rootElement = this.getElementForPlacingCell(typeOfContent);
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

    private String getCoefficientForLabClass(Double coefficient) {
        if (coefficient == null)
            return "";
        else if (coefficient == -1)
            return "";
        else if (coefficient == 1)
            return Table.coef_1_0;
        else if (coefficient == 0.8)
            return Table.coef_0_8;
        else if (coefficient == 0.6)
            return Table.coef_0_6;
        else if (coefficient == 0.4)
            return Table.coef_0_4;
        else if (coefficient == 0.0)
            return Table.coef_0_0;
        else return "";
    }


    protected Element getLastRow() {
        List<Node> list = document.getRootElement().content();
        return (Element)list.get(list.size() - 1);
    }

    protected void addElementInElement(Element root, Element elem) {
        root.add(elem);
    }
}

