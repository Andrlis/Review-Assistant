package resources.TableMaker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.Student;
import data.UniversityClass;
import data.group.SubGroup;
import data.mark.LabMark;
import data.mark.TestMark;
import resources.TableMaker.Convetrters.*;
import resources.TableMaker.Convetrters.UniversityClassConverter;
import resources.TableMaker.Data.BonusMark;
import resources.TableMaker.Data.Class;
import resources.TableMaker.Data.Key;
import resources.TableMaker.Data.Template;

import javax.validation.constraints.NotNull;
import java.util.*;

public class JsonMaker {
    public static String getJsonSubGroupMarks(SubGroup subGroup, boolean editable){

        ArrayList<Map<String,Object>> studentArray = new ArrayList<Map<String, Object>>();
        for(Student currentStudent: (subGroup == null) ? new ArrayList<Student>() : subGroup.getStudentsList()) {
            studentArray.add(getStudentMarkMap(currentStudent));
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

    public static String getJsonSubGroupVisits(SubGroup subGroup, boolean editable) {
        ArrayList<Map<String,Object>> studentArray = new ArrayList<Map<String, Object>>();

        for(Student currentStudent : (subGroup == null) ? new ArrayList<Student>() : subGroup.getStudentsList()) {
            studentArray.add(getStudentVisitsMap(currentStudent, subGroup.getUniversityClassesList()));
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
        builder.registerTypeAdapter(Class.class, new UniversityClassConverter(editable));
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.toJson(map);
    }

    /*
    Надо добавить класс info-cell-editable в каждую ячейку этой таблицы
     */
    public static String getJsonSubGroupStudentRedact(SubGroup subGroup) {
        ArrayList<Map<String,Object>> studentArray = new ArrayList<Map<String, Object>>();

        for(Student currentStudent :  (subGroup == null) ? new ArrayList<Student>() : subGroup.getStudentsList()) {
            studentArray.add(getStudentRedactMap(currentStudent));
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
        builder.registerTypeAdapter(Template.class, new TemplateConverter());
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.toJson(map);
    }

    private static Map<String, Object> getStudentMarkMap(Student student){
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

    private static Map<String, Object> getStudentVisitsMap(Student student, List<UniversityClass> universityClasses) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("student", student);

        for(UniversityClass universityClass : universityClasses){
            map.put(universityClass.getDataTime(), new Class(universityClass,
                    !student.getMissedUniversityClassesList().contains(universityClass)));
        }

        return map;
    }

    private static Map<String, Object> getStudentRedactMap(Student student) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("student", student);

        map.put("eMail", new Template("cell-ui info-cell-editable", student.geteMail())); //класс и значение для емаила
        map.put("gitURL", new Template("cell-ui info-cell-editable", student.getGitURL())); //класс и значение для юрл

        return map;
    }
}
