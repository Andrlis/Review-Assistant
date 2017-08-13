package Data.Group;

import Data.Lab.IssuedLab;
import Data.Student;
import Data.UniversityClass;

import java.util.List;

/**
 * class containing information about subgroup and list of classes, list of laboratory work
 */
public class SubGroup {
    private Long id;
    private String subGroupNumber;
    private List<Student> studentsList;
    private List<UniversityClass> universityClassesList;
    private List<IssuedLab>issuedLabsList;

    public SubGroup(Long id, String subGroupNumber, List<Student> studentsList, List<UniversityClass> universityClassesList, List<IssuedLab> issuedLabsList) {
        this.id = id;
        this.subGroupNumber = subGroupNumber;
        this.studentsList = studentsList;
        this.universityClassesList = universityClassesList;
        this.issuedLabsList = issuedLabsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubGroupNumber() {
        return subGroupNumber;
    }

    public void setSubGroupNumber(String subGroupNumber) {
        this.subGroupNumber = subGroupNumber;
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(List<Student> studentsList) {
        this.studentsList = studentsList;
    }

    public List<UniversityClass> getUniversityClassesList() {
        return universityClassesList;
    }

    public void setUniversityClassesList(List<UniversityClass> universityClassesList) {
        this.universityClassesList = universityClassesList;
    }

    public List<IssuedLab> getIssuedLabsList() {
        return issuedLabsList;
    }

    public void setIssuedLabsList(List<IssuedLab> issuedLabsList) {
        this.issuedLabsList = issuedLabsList;
    }

    public  void addStudent(Student student) {
        this.studentsList.add(student);
    }

    public void addIssuedLab(IssuedLab issuedLab) {
        this.issuedLabsList.add(issuedLab);
    }

    public void addUniversityClass(UniversityClass universityClass) {
        this.universityClassesList.add(universityClass);
    }

    public UniversityClass getNextUniversityClass(UniversityClass universityClass) {
        for(UniversityClass currentClass : universityClassesList) {
            if(currentClass.getDate().getTime() > universityClass.getDate().getTime()) {
                return currentClass;
            }
        }
        return null;
    }
}

