package Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kesso on 08.08.17.
 */
public class LabInformation {
    private long id;
    private int labNumber;
    private String keyWord;
    private Set issued_labs;

    public LabInformation(){
        this.issued_labs = new HashSet();
    }

    public LabInformation(long id, int labNumber, String keyWord, Set issued_labs){
        this.id = id;
        this.labNumber = labNumber;
        this.keyWord = keyWord;
        this.issued_labs = issued_labs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLabNumber() {
        return labNumber;
    }

    public void setLabNumber(int labNumber) {
        this.labNumber = labNumber;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Set getIssued_labs() {
        return issued_labs;
    }

    public void setIssued_labs(Set issued_labs) {
        this.issued_labs = issued_labs;
    }
}
