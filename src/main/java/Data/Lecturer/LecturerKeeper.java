package Data.Lecturer;

import java.util.List;

/**
 * Created by kesso on 24.08.17.
 */
public class LecturerKeeper {
    private List<Lecturer> lecturerList;

    public LecturerKeeper() {}

    public LecturerKeeper(List<Lecturer> lecturerList) {
        this.lecturerList = lecturerList;
    }

    public List<Lecturer> getLecturerList() {
        return lecturerList;
    }

    public void setLecturerList(List<Lecturer> lecturerList) {
        this.lecturerList = lecturerList;
    }
}
