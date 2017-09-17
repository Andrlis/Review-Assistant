package intercalationJavaHTML;

//import javafx.scene.control.Tab;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import java.util.List;

public abstract class Table {
    protected Document document;

    protected static final String tableClass = "table-ui";
    protected static final String rowClass   = "row-ui";
    protected static final String cellClass  = "cell-ui";


    /*Classes for type of cell */
    public static final String dataCell = "data-cell";
    public static final String headerCell   = "header-cell";


    public static final String editableCell = "editable";
    //public static final String markCell = "mark";


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


    protected Element getElementForPlacingCell(){

        return this.getLastRow();
    }

    //crete div element
    protected Element getDivCell(String classType, String content) {
        Element el = DocumentHelper.createElement("div")
                .addAttribute("class", classType)
                .addText(content);
        return el;
    }

    public void addRow() {
        document.getRootElement().addElement("tr")
                .addAttribute("class", Table.rowClass);
        Element row = document.getRootElement().element("tr");
    }



    public abstract  Element addCell(String typeOfContent, String content);


    protected Element getLastRow() {
        List<Node> list = document.getRootElement().content();
        return (Element)list.get(list.size() - 1);
    }

/*
    protected void addElementInElement(Element root, Element elem) {
        root.add(elem);
    }
*/
}

