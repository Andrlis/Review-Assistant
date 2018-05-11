package data.lab;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class containing basic information about laboratory work(number of laboratory work,
 * word for checking commit), also all issue labs relevant number of lab. work
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
    @OneToMany(mappedBy = "labDescription", cascade = CascadeType.MERGE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<IssuedLab> issuedLabList;

    public Lab() {
        this.issuedLabList = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        if (this.getNumberOfLab().equals(((Lab) obj).getNumberOfLab()))
            return true;
        else return false;
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

    public List<IssuedLab> getIssuedLabList() {
        return issuedLabList;
    }

    public void setIssuedLabList(List<IssuedLab> issuedLabList) {
        this.issuedLabList = issuedLabList;
    }

    public void removeIssuedLab(IssuedLab issuedLab){
        this.issuedLabList.remove(issuedLab);
    }

    public void addIssuedLab(IssuedLab issuedLab){
        this.issuedLabList.add(issuedLab);
    }

    @Override
    public String toString() {
        return "Lab{" +
                "id=" + id +
                ", numberOfLab=" + numberOfLab +
                ", keyWord='" + keyWord + '\'' +
                '}';
    }
}
