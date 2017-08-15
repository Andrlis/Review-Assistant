package Data.Mark;

/**
 * Lab mark
 */
public class LabMark extends Mark {
    private Double coefficient;

    public LabMark(){
        super();
    }

    public LabMark(Long id, Integer mark, Double coefficient) {
        super(id, mark);
        this.coefficient = coefficient;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }
}
