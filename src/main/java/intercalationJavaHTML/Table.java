package intercalationJavaHTML;

//import javafx.scene.control.Tab;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import java.util.List;

public abstract class Table {

    protected static final String tableClass = "table-ui";
    protected static final String rowClass   = "row-ui";
    protected static final String cellClass  = "cell-ui";

    public static final String addNewColButtonName = "add-col-button";

    Document document;

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

    protected Element getLastRow() {
        List<Node> list = document.getRootElement().content();
        return (Element)list.get(list.size() - 1);
    }

    protected void addElementInElement(Element root, Element elem) {
        root.add(elem);
    }


    public abstract void addRow();

}

