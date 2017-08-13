package Data.Mark;

/**
 * Mark
 */
public class Mark {
    private Long id;
    private Integer mark;

    public Mark(Long id, Integer mark) {
        this.id = id;
        this.mark = mark;
    }

    public Mark(Integer mark) {
        this.id = new Long(-1);
        this.mark = mark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
