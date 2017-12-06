package resources.TableMaker.Data;

public class Template {
    private String cellClass;
    private String value;

    public Template(String cellClass, String value) {
        this.cellClass = cellClass;
        this.value = value;
    }


    public String getCellClass() {
        return cellClass;
    }

    public void setCellClass(String cellClass) {
        this.cellClass = cellClass;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
