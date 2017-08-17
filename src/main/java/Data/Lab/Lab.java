package Data.Lab;

import Data.Group.SubGroup;

import javax.persistence.*;
import java.util.Map;

/**
 * Class containing basic information about laboratory work(number of laboratory work,
 * word for checking commit), also all issued labs relevant number of lab. work
 */
@Entity
@Table(name = "labs")
public class Lab {
    @Id
    @Column(name ="id_lab")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "lab_number")
    private Integer numberOfLab;
    @Column(name = "key_word", length = 20)
    private String keyWord;

    @OneToMany
    @JoinTable(
            name = "issued_labs",
            joinColumns = @JoinColumn(name = "id_lab"),
            inverseJoinColumns = @JoinColumn(name ="id_issued_lab")
    )
    @MapKeyColumn(name = "id_group_subgroup", table = "issued_labs")
    private Map<SubGroup, IssuedLab> issuedLabMap;

    public Lab(){}

    public Lab(Integer id, Integer numberOfLab, String keyWord, Map<SubGroup, IssuedLab> issuedLabMap) {
        this.id = id;
        this.numberOfLab = numberOfLab;
        this.keyWord = keyWord;
        this.issuedLabMap = issuedLabMap;
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

    public Map<SubGroup, IssuedLab> getIssuedLabMap() {
        return issuedLabMap;
    }

    public void setIssuedLabMap(Map<SubGroup, IssuedLab> issuedLabMap) {
        this.issuedLabMap = issuedLabMap;
    }

    public void addIssuedLab(SubGroup subGroup, IssuedLab issuedLab) {
        this.issuedLabMap.put(subGroup, issuedLab);
    }

    public IssuedLab getIssuedLab(SubGroup subGroup) {
        return this.issuedLabMap.get(subGroup);
    }
}
