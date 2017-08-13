package Data;

import java.util.Date;

/**
 * University class
 */
public class UniversityClass {
    private Long id;
    private Date date;

    public UniversityClass(Long id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
