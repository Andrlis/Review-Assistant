package resources.TableMaker;

import data.Student;
import data.mark.LabMark;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TableMaker {

    private static Map<String,Object> getStudentMap(Student student){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("student", student);

        for(LabMark labMark : student.getLabMarkList()) {
            String labNum = new String("lab" + labMark.getIssuedLab().getLabDescription().getNumberOfLab());

            map.put(labNum, labMark);
        }

        return map;
    }
}
