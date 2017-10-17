package data.Lab;

import java.util.List;

/**
 * Class consisting list of information about lab
 */
public class LabsKeeper {
    private List<Lab> labList;

    public LabsKeeper() {
    }

    public LabsKeeper(List<Lab> labArrayList) {
        this.labList = labList;
    }

    public List<Lab> getLabList() {
        return this.labList;
    }

    public void setLabList(List<Lab> labList) {
        this.labList = labList;
    }

    public Lab getLab(int number) {
        for (Lab currentLab : this.labList) {
            if (currentLab.getNumberOfLab().equals(number))
                return currentLab;
        }
        return null;
    }
}
