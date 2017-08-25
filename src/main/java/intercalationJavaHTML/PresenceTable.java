package intercalationJavaHTML;

public class PresenceTable extends Table {

    public PresenceTable(String idTable) {
        super(idTable);
    }

    @Override
    public void addRow() {
        document.getRootElement().addElement("tr")
                .addAttribute("class", Table.rowClass);
    }


    public void addCell(String content) {

    }
}
