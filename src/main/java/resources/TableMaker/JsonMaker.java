package resources.TableMaker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.Student;
import data.UniversityClass;
import data.group.SubGroup;
import data.mark.LabMark;
import data.mark.TestMark;
import resources.Hibernate.*;
import resources.TableMaker.Convetrters.*;
import resources.TableMaker.Convetrters.UniversityClassConverter;
import resources.TableMaker.Data.BonusMark;
import resources.TableMaker.Data .ItogMark;
import resources.TableMaker.Data.Class;
import resources.TableMaker.Data.Key;
import resources.TableMaker.Data.Template;
import java.util.*;

public class JsonMaker {
    public static String getJsonSubGroupMarks(SubGroup subGroup, boolean editable){

        ArrayList<Map<String,Object>> studentArray = new ArrayList<Map<String, Object>>();
        for(Student currentStudent: (subGroup == null) ? new ArrayList<Student>() : subGroup.getStudentsList()) {
            studentArray.add(getStudentMarkMap(currentStudent));
        }

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("table-class", "table table-hover table-bordered table-class");

        if(!studentArray.isEmpty()) {
            map.put("header", sortHeader(studentArray.get(0).keySet()));
        }else {
            map.put("header", new ArrayList<Object>());
        }

        map.put("args", studentArray);


        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Student.class, new StudentConverter(true, false));
        builder.registerTypeAdapter(LabMark.class, new LabMarkConverter(editable));
        builder.registerTypeAdapter(TestMark.class, new TestMarkConverter(editable));
        builder.registerTypeAdapter(BonusMark.class, new BonusMarkConverter(editable));
        builder.registerTypeAdapter(ItogMark.class, new ItogMarkConverter());
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
        map.put("table-class", "table table-hover table-bordered table-class");

        if(!studentArray.isEmpty()) {
            map.put("header", studentArray.get(0).keySet());
        }else {
            map.put("header", new ArrayList<Object>());
        }

        map.put("args", studentArray);

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Student.class, new StudentConverter(true, false));
        builder.registerTypeAdapter(Class.class, new UniversityClassConverter(editable));
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.toJson(map);
    }

    public static String getJsonSubGroupStudentRedact(SubGroup subGroup, boolean editable) {
        ArrayList<Map<String,Object>> studentArray = new ArrayList<Map<String, Object>>();

        for(Student currentStudent :  (subGroup == null) ? new ArrayList<Student>() : subGroup.getStudentsList()) {
            studentArray.add(getStudentRedactMap(currentStudent, editable));
        }

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("table-class", "table table-hover table-bordered table-class");

        if(!studentArray.isEmpty()) {
            map.put("header", studentArray.get(0).keySet());
        }else {
            map.put("header", new ArrayList<Object>());
        }

        map.put("args", studentArray);

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Student.class, new StudentConverter(false, editable));
        builder.registerTypeAdapter(Template.class, new TemplateConverter());
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.toJson(map);
    }

    public static String getJsonSubGroupClasses(SubGroup subGroup) throws HibernateShellQueryException {
        TestHibernateShell testHibernateShell = new TestHibernateShell();

        Map<String,Object> classesMap = new LinkedHashMap<String, Object>();

        classesMap.put("lab-number", subGroup.getIssuedLabsList().size() + 1);
        classesMap.put("test-number", testHibernateShell.getNumberOfNextTest().toString());

        ArrayList<String> classes = new ArrayList<String>();
        for(UniversityClass universityClass : (subGroup == null) ? new ArrayList<UniversityClass>() : subGroup.getUniversityClassesList()) {
            classes.add(universityClass.getDataTime());
        }

        classesMap.put("dates", classes);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.toJson(classesMap);
    }

    public static String getAuthorisationResult(int errorCode, String message) {
        Map<String,Object> map = new LinkedHashMap<String, Object>();

        map.put("code", Integer.toString(errorCode));
        map.put("message", message);

        GsonBuilder builder = new GsonBuilder();
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
        map.put("itog", new ItogMark(student));

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

    private static Map<String, Object> getStudentRedactMap(Student student, boolean editable) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("student", student);

        map.put("eMail", new Template("cell-ui" + (editable ? " info-cell-editable" : ""), student.geteMail())); //класс и значение для емаила
        map.put("gitURL", new Template("cell-ui"+ (editable ? " info-cell-editable" : ""), student.getGitURL())); //класс и значение для юрл

        return map;
    }

    private static ArrayList<String> sortHeader(Set<String> set) {
        ArrayList<String> strings = new ArrayList<String>();

        strings.add("student");

        for(int i = 1; ; i++){
            if(set.contains("lab" + i)){
                strings.add("lab" + i);
            }
            else
                break;
        }

        for(int i = 1; ; i++){
            if(set.contains("test" + i)){
                strings.add("test" + i);
            }
            else
                break;
        }

        strings.add("bonus");
        strings.add("itog");

        return strings;
    }
}
