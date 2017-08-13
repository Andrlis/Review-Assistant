package Data.Lab;

import Data.Group.SubGroup;

import java.util.Map;

/**
 * Class containing basic information about laboratory work(number of laboratory work,
 * word for checking commit), also all issued labs relevant number of lab. work
 */
public class Lab {
    private Long id;
    private Integer numberOfLab;
    private String keyWord;
    private Map<SubGroup, IssuedLab> issuedLabMap;

    public Lab(Long id, Integer numberOfLab, String keyWord, Map<SubGroup, IssuedLab> issuedLabMap) {
        this.id = id;
        this.numberOfLab = numberOfLab;
        this.keyWord = keyWord;
        this.issuedLabMap = issuedLabMap;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void addIssuedLab(SubGroup subGroup,IssuedLab issuedLab) {
        this.issuedLabMap.put(subGroup,issuedLab);
    }

    public IssuedLab getIssuedLab(SubGroup subGroup) {
        return this.issuedLabMap.get(subGroup);
    }
}
