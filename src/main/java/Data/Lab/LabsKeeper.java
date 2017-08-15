package Data.Lab;

import java.util.ArrayList;

/**
 * Class consisting list of information about lab
 */
public class LabsKeeper {
    private ArrayList<Lab> labArrayList;

    public LabsKeeper(){}

    public LabsKeeper(ArrayList<Lab> labArrayList) {
        this.labArrayList = labArrayList;
    }

    public ArrayList<Lab> getLabArrayList() {
        return labArrayList;
    }

    public void setLabArrayList(ArrayList<Lab> labArrayList) {
        this.labArrayList = labArrayList;
    }

    public Lab getLab(int number) {
        return this.labArrayList.get(number);
    }
}
