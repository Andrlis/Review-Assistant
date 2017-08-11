package Data;

import java.util.Date;

/**
 * Created by kesso on 08.08.17.
 */
public class IssuedLab {
    private long id;
    private LabInformation information;
    private SubGroup subGroup;
    private Class classOfIssue;
    private double coefficient;
    private Class classOfDeadline;
    private Date lastCheckDateAndTime;

    public IssuedLab(long id, LabInformation information, SubGroup subGroup, Class classOfIssue, double coefficient, Class classOfDeadline, Date lastCheckDateAndTime) {
        this.id = id;
        this.information = information;
        this.subGroup = subGroup;
        this.classOfIssue = classOfIssue;
        this.coefficient = coefficient;
        this.classOfDeadline = classOfDeadline;
        this.lastCheckDateAndTime = lastCheckDateAndTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LabInformation getInformation() {
        return information;
    }

    public void setInformation(LabInformation information) {
        this.information = information;
    }

    public SubGroup getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(SubGroup subGroup) {
        this.subGroup = subGroup;
    }

    public Class getClassOfIssue() {
        return classOfIssue;
    }

    public void setClassOfIssue(Class classOfIssue) {
        this.classOfIssue = classOfIssue;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public Class getClassOfDeadline() {
        return classOfDeadline;
    }

    public void setClassOfDeadline(Class classOfDeadline) {
        this.classOfDeadline = classOfDeadline;
    }

    public Date getLastCheckDateAndTime() {
        return lastCheckDateAndTime;
    }

    public void setLastCheckDateAndTime(Date lastCheckDateAndTime) {
        this.lastCheckDateAndTime = lastCheckDateAndTime;
    }
}
