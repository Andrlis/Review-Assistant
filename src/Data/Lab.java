package Data;

/**
 * Created by kesso on 08.08.17.
 */
public class Lab {
    private IssuedLab issuedLab;
    private double coefficient;
    private int mark;

    public Lab(IssuedLab issuedLad){
        this.issuedLab = issuedLad;
        this.coefficient = -1;
        this.mark = -1;
    }

    public Lab(IssuedLab issuedLab, double coefficient, int mark) {
        this.issuedLab = issuedLab;
        this.coefficient = coefficient;
        this.mark = mark;
    }

    public IssuedLab getIssuedLad() {
        return issuedLab;
    }

    public void setIssuedLab(IssuedLab issuedLad) {
        this.issuedLab = issuedLad;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
