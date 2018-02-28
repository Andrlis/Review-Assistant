package statistic;

import data.Student;
import data.UniversityClass;
import data.group.Group;
import data.group.SubGroup;
import data.mark.LabMark;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Analyzer {

    List<StudentStatistic> analyzeGroup(Group group, Date startDate, Date stopDate) {

        List<StudentStatistic> studentStatistics = new ArrayList<>();

        for(SubGroup subGroup : group.getSubGroupList()){

            for(Student student: subGroup.getStudentsList()){
                StudentStatistic statistic = new StudentStatistic();
                statistic.studentFullName = student.getFulName();
                statistic.numberOfLabs = getNumberOfLabs(student.getLabMarkList());
                statistic.numberOfMissedClasses = getNumberOfMissedClasses(student.getMissedUniversityClassesList(),
                        startDate, stopDate);
                studentStatistics.add(statistic);
            }
        }

        return studentStatistics;
    }

    private int getNumberOfLabs(List<LabMark> labMarkList){
        int count = 0;
        for (LabMark mark : labMarkList){
            if(mark.getMark() == -1) count++;
        }
        return count;
    }

    private int getNumberOfMissedClasses(List<UniversityClass> missedClasses, Date startDate , Date stopDate){
        int count = 0;
        for(UniversityClass missedClass: missedClasses){
            if(missedClass.getDate().after(startDate) && missedClass.getDate().before(stopDate)) count++;
        }
        return count;
    }
}