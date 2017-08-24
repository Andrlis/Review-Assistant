package intercalationJavaHTML;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import java.util.List;


/**
 * Created by Greenwalrus on 23.08.2017.
 */
public class TableGeneratorTool {
    private Document document;
    private Element  root;
    private static final String tableClass = "table-ui";
    private static final String rowClass   = "row-ui";
    private static final String cellClass  = "cell-ui";


    /*For addCell method constants. For param typeOfContent.*/
    public static final String labMarkTypeOfContent   = "lab-mark";
    public static final String testMarkTypeOfContent  = "test-mark";
    public static final String studNameTypeOfContent  = "stud-name";
    public static final String bonusMarkTypeOfContent = "bonus-mark";
    public static final String headerTypeOfContent    = "header-cell";


    /*Classes for cells with lab marks with coefficient*/
    private static final String coef_1_0 = "coef-10";
    private static final String coef_0_8 = "coef-08";
    private static final String coef_0_6 = "coef-06";
    private static final String coef_0_4 = "coef-04";
    private static final String coef_0_0 = "coef-00";


    private void standInitialisationOfFields() {
        document = DocumentHelper.createDocument();
        root = document.addElement("table");
        root.addAttribute("class", TableGeneratorTool.tableClass);
    }

    public TableGeneratorTool() {
        standInitialisationOfFields();
    }

    public TableGeneratorTool(String idTable) {
        standInitialisationOfFields();
        root.addAttribute("id", idTable);
    }

    public Document getDocument() {
        return document;
    }

    public Element getRoot() {
        return root;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public void setRoot(Element root) {
        this.root = root;
    }

    public void addRow() {
        root.addElement("tr")
                .addAttribute("class", TableGeneratorTool.rowClass);
    }

   /* private Element getRowElementById(String idRow) {
        List<Node> list = document.selectNodes("tr");
        Element    e;
        for (Node n : list) {
            e = (Element) n;
            if (e.attributeValue("id").equals(idRow))
                return e;
        }
        return null;
    }*/

    private String getCoefficientForLabClass(Double coefficient) {
        if (coefficient == null)
            return "";
        else if (coefficient == 1)
            return TableGeneratorTool.coef_1_0;
        else if (coefficient == 0.8)
            return TableGeneratorTool.coef_0_8;
        else if (coefficient == 0.6)
            return TableGeneratorTool.coef_0_6;
        else if (coefficient == 0.4)
            return TableGeneratorTool.coef_0_4;
        else if (coefficient == 0.0)
            return TableGeneratorTool.coef_0_0;
        else return null;
    }

    private Element getLastRow() {
        List<Node> list = document.selectNodes("tr");
        return (Element)list.get(list.size() - 1);
    }

    public void addCell(String idRowWhereAdd, String typeOfContent, String content, Double coefficient) {
        //Element element = this.getRowElementById(idRowWhereAdd);
        Element element;

        try {
            element = this.getLastRow();
        }
        catch (IndexOutOfBoundsException ex) {
            return;
        }

        String coefficientClass = this.getCoefficientForLabClass(coefficient);

        if (element == null || coefficientClass == null)
            return;

        element.addElement("td")
                .addAttribute("class", TableGeneratorTool.cellClass + " " + typeOfContent + " " + coefficientClass)
                .addText(content);
    }

    public void addCell(String idRowWhereAdd, String typeOfContent, String content) {
        this.addCell(idRowWhereAdd, typeOfContent, content, null);
    }
}
