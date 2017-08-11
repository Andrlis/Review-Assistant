package Data;

import java.util.Date;

/**
 * Created by kesso on 08.08.17.
 */
public class Class {
    private long id;
    private Date dateAndTime;
    private SubGroup subGroup;

    public Class(long id, Date dateAndTime, SubGroup subGroup) {
        this.id = id;
        this.dateAndTime = dateAndTime;
        this.subGroup = subGroup;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public SubGroup getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(SubGroup subGroup) {
        this.subGroup = subGroup;
    }
}
