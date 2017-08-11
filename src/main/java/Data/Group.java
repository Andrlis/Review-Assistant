package Data;

/**
 * Created by kesso on 08.08.17.
 */
public class Group {
    private long id;
    private String groupNumber;
    private String bsuirGroupId;

    public Group(long id, String groupNumber, String bsuirGroupId) {
        this.id = id;
        this.groupNumber = groupNumber;
        this.bsuirGroupId = bsuirGroupId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getBsuirGroupId() {
        return bsuirGroupId;
    }

    public void setBsuirGroupId(String bsuirGroupId) {
        this.bsuirGroupId = bsuirGroupId;
    }
}
