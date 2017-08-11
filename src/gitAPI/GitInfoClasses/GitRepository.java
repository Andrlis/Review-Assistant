package gitAPI.GitInfoClasses;

/**
 * Created by Andrey on 13.07.2017.
 */
public class GitRepository {

    private String id;
    private GitUser owner;
    private String created_at;
    private String updated_at;
    private String pushed_at;           //Is necessary for progress control
    private int size;
    private String language;

    public GitRepository(){
        id = null;
        owner = null;
        created_at = null;
        updated_at = null;
        pushed_at = null;
        size = 0;
        language = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GitUser getOwner() {
        return owner;
    }

    public void setOwner(GitUser owner) {
        this.owner = owner;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getPushed_at() {
        return pushed_at;
    }

    public void setPushed_at(String pushed_at) {
        this.pushed_at = pushed_at;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "GitRepository{" +
                "id='" + id + '\'' +
                ", owner=" + owner.toString() +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", pushed_at='" + pushed_at + '\'' +
                ", size=" + size +
                ", language='" + language + '\'' +
                '}';
    }
}
