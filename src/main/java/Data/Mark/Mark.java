package Data.Mark;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 * Mark
 */
@Embeddable
public class Mark {
    private Integer mark;

    public Mark(){}

    public Mark(Integer mark) {
        this.mark = mark;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
