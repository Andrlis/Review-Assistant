package Data;

/**
 * Created by kesso on 08.08.17.
 */
public class SubGroup {
    private long id;
    private String subGroupNumber;
    private Group group;
    private Lecturer lecturer;

    public SubGroup(long id, String subGroupNumber, Group group, Lecturer lecturer) {
        this.id = id;
        this.subGroupNumber = subGroupNumber;
        this.group = group;
        this.lecturer = lecturer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubGroupNumber() {
        return subGroupNumber;
    }

    public void setSubGroupNumber(String subGroupNumber) {
        this.subGroupNumber = subGroupNumber;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }
}
