package Data.Mark;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Lab mark
 */

public class LabMark extends Mark {
    private Double coefficient;

    public LabMark(){
        super();
    }

    public LabMark(Integer mark, Double coefficient) {
        super(mark);
        this.coefficient = coefficient;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }
}
