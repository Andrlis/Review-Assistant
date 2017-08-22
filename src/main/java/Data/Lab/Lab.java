package Data.Lab;

import javax.persistence.*;

/**
 * Class containing basic information about laboratory work(number of laboratory work,
 * word for checking commit), also all issued labs relevant number of lab. work
 */
@Entity
@Table(name = "labs")
public class Lab {
    @Id
    @Column(name = "id_lab")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "lab_number")
    private Integer numberOfLab;
    @Column(name = "key_word", length = 20)
    private String keyWord;

    public Lab() {
    }

    public Lab(Integer id, Integer numberOfLab, String keyWord) {
        this.id = id;
        this.numberOfLab = numberOfLab;
        this.keyWord = keyWord;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumberOfLab() {
        return numberOfLab;
    }

    public void setNumberOfLab(Integer numberOfLab) {
        this.numberOfLab = numberOfLab;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
