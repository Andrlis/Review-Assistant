package intercalationJavaHTML;

import org.dom4j.Element;

public class StudentListTable extends Table {

    public static final String nameCell = "stud-name-cell";
    public static final String eMailCell = "stud-eMail-ln-cell";
    public static final String gitHubCell = "stud-gitHub-ln-cell";

    public StudentListTable(String idTable) {
        super(idTable);
    }

    public StudentListTable(){}

    @Override
    public Element addCell(String typeOfContent, String content) {
        Element rootElement;

        try {
            rootElement = this.getElementForPlacingCell();
        }
        catch (IndexOutOfBoundsException ex) {
            return null;
        }
        if (rootElement == null)
            return null;

        return rootElement.addElement("td")
                .addAttribute("class", Table.cellClass + " " + typeOfContent)
                .addText(content);
    }
}
