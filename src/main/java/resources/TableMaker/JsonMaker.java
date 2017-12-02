package resources.TableMaker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.Student;
import data.group.SubGroup;
import data.mark.LabMark;
import data.mark.TestMark;

import java.util.*;

public class JsonMaker {
    public static String getJsonSubGroupMarks(SubGroup subGroup, boolean editable){

        ArrayList<Map<String,Object>> studentArray = new ArrayList<Map<String, Object>>();
        for(Student currentStudent: (subGroup == null) ? new ArrayList<Student>() : subGroup.getStudentsList()) {
            studentArray.add(getStudentMap(currentStudent));
        }

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("table-class", "table-ui");

        if(!studentArray.isEmpty()) {
            map.put("header", studentArray.get(0).keySet());
        }else {
            map.put("header", new ArrayList<Object>());
        }

        map.put("args", studentArray);


        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Student.class, new StudentConverter());
        builder.registerTypeAdapter(LabMark.class, new LabMarkConverter(editable));
        builder.registerTypeAdapter(TestMark.class, new TestMarkConverter(editable));
        builder.registerTypeAdapter(BonusMark.class, new BonusMarkConverter(editable));
        builder.registerTypeAdapter(Key.class, new KeyConverter());
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.toJson(map);
    }

    private static Map<String,Object> getStudentMap(Student student){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("student", student);

        for(LabMark labMark : student.getLabMarkList()) {
            String labNum = "lab" + labMark.getIssuedLab().getLabDescription().getNumberOfLab();

            map.put(labNum, labMark);
        }

        for(TestMark testMark : student.getTestMarkList()) {
            String testNum = "test" + testMark.getTest().getTestNumber();

            map.put(testNum, testMark);
        }

        map.put("bonus", new BonusMark(student));

        return map;
    }
}
