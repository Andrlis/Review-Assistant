package jsonMaker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.Student;
import data.UniversityClass;
import data.group.Group;
import data.group.SubGroup;
import data.lab.IssuedLab;
import data.lab.Lab;
import data.lecturer.Lecturer;
import data.mark.LabMark;
import data.mark.TestMark;
import data.сomment.Comment;
import jsonMaker.convetrters.*;
import jsonMaker.jsonData.ItogMark;
import logics.CommentLogic;
import logics.TestLogic;
import exceptions.DataBaseCriteriaCountException;
import exceptions.DataBaseQueryException;
import jsonMaker.jsonData.BonusMark;
import jsonMaker.jsonData.Class;
import jsonMaker.jsonData.Key;
import jsonMaker.jsonData.Template;

import java.util.*;

public class JsonMaker {
    public static String getJsonGroups(List<Group> groups){
        ArrayList<Map<String,Object>> groupArray = new ArrayList<>();
        for (Group group : groups){
            groupArray.add(getGroupMap(group));
        }

        Map<String, Object> map = new LinkedHashMap<>();

        if (!groupArray.isEmpty()) {
            map.put("header", sortHeader(groupArray.get(0).keySet()));
        } else {
            map.put("header", new ArrayList<Object>());
        }

        map.put("args", groupArray);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Group.class, new GroupConverter());
        gsonBuilder.registerTypeAdapter(SubGroup.class, new SubGroupConverter());
        gsonBuilder.registerTypeAdapter(Lecturer.class, new LecturerConverter());

        Gson gson = gsonBuilder.create();

        return gson.toJson(map);
    }

    public static String getJsonSubGroupMarks(SubGroup subGroup, boolean editable) {

        ArrayList<Map<String, Object>> studentArray = new ArrayList<Map<String, Object>>();
        for (Student currentStudent : (subGroup == null) ? new ArrayList<Student>() : subGroup.getStudentsList()) {
            studentArray.add(getStudentMarkMap(currentStudent));
        }

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("table-class", "table table-hover table-bordered table-class");

        if (!studentArray.isEmpty()) {
            map.put("header", sortHeader(studentArray.get(0).keySet()));
        } else {
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
        ArrayList<Map<String, Object>> studentArray = new ArrayList<Map<String, Object>>();

        for (Student currentStudent : (subGroup == null) ? new ArrayList<Student>() : subGroup.getStudentsList()) {
            studentArray.add(getStudentVisitsMap(currentStudent, subGroup.getUniversityClassesList()));
        }

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("table-class", "table table-hover table-bordered table-class");

        if (!studentArray.isEmpty()) {
            map.put("header", studentArray.get(0).keySet());
        } else {
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
        ArrayList<Map<String, Object>> studentArray = new ArrayList<Map<String, Object>>();

        for (Student currentStudent : (subGroup == null) ? new ArrayList<Student>() : subGroup.getStudentsList()) {
            studentArray.add(getStudentRedactMap(currentStudent, editable));
        }

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("table-class", "table table-hover table-bordered table-class");

        if (!studentArray.isEmpty()) {
            map.put("header", studentArray.get(0).keySet());
        } else {
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

    private static String formJsonForComment (
            String studentName, String commentDescription,
            String type, String commentId,
            String secondCommentId, String comment) {

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("student", studentName);
        map.put("description", commentDescription);
        map.put("type",type);
        map.put("commentId", commentId);
        map.put("secondCommentId", secondCommentId);
        comment = (comment == null) ? "" : comment;
        map.put("comment", comment);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        return gson.toJson(map);
    }

    public static String getJsonTestMarkComment(TestMark testMark){

        String studentName = testMark.getStudent().getFulName();
        String descr = "Тест №" + testMark.getTest().getTestNumber();
        String type = "test";
        String commentId = "" + testMark.getId();
        String secondCommentId = "";
        String comment = testMark.getComment();

        return JsonMaker.formJsonForComment(
                studentName, descr,
                type, commentId,
                secondCommentId, comment);
    }

    public static String getJsonBonusMarkComment(Student student){
        String studentName = student.getFulName();
        String descr = "Бонус";
        String type = "bonus";
        String commentId = "" + student.getId();
        String secondCommentId = "";
        String comment = student.getComment();

        return JsonMaker.formJsonForComment(
                studentName, descr,
                type, commentId,
                secondCommentId, comment);
    }

    public static String getJsonClassComment(Student student, UniversityClass universityClass){
        CommentLogic commentLogic = new CommentLogic();

        String studentName = student.getFulName();
        String descr = "Пара " + universityClass.getDataTime();
        String type = "class";
        String commentId = "" + student.getId();
        String secondCommentId = "" + universityClass.getId();

        Comment commentObject = null;
        try {
            commentObject = commentLogic.get(student.getId(), universityClass.getId());
        } catch (DataBaseQueryException e) {
            e.printStackTrace();
        } catch (DataBaseCriteriaCountException e) {
            e.printStackTrace();
        }
        String comment = commentObject.getComment();

        return JsonMaker.formJsonForComment(
                studentName, descr,
                type, commentId,
                secondCommentId, comment);
    }

    public static String getJsonLabMarkComment(LabMark labMark){
        String studentName = labMark.getStudent().getFulName();
        String descr = "ЛР №" + labMark.getIssuedLab().getLabDescription().getNumberOfLab();
        String type = "lab";
        String commentId = "" + labMark.getId();
        String secondCommentId = "";
        String comment = labMark.getComment();

        return JsonMaker.formJsonForComment(
                studentName, descr,
                type, commentId,
                secondCommentId, comment);
    }

    public static String getJsonSubGroupClasses(SubGroup subGroup) throws DataBaseQueryException {
        TestLogic testLogic = new TestLogic();

        Map<String, Object> classesMap = new LinkedHashMap<String, Object>();

        classesMap.put("lab-number", subGroup.getIssuedLabsList().size() + 1);
        classesMap.put("test-number", testLogic.getNextNumber().toString());

        ArrayList<String> classes = new ArrayList<String>();
        for (UniversityClass universityClass : (subGroup == null) ? new ArrayList<UniversityClass>() : subGroup.getUniversityClassesList()) {
            classes.add(universityClass.getDataTime());
        }

        classesMap.put("dates", classes);

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Lab.class, new LabConverter());
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.toJson(classesMap);
    }

    public static String getAuthorisationResult(int errorCode, String message) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        map.put("code", Integer.toString(errorCode));
        map.put("message", message);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.toJson(map);
    }

    public static String getIssuedLabJson(IssuedLab lab) {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("type", "IssuedLab");
        map.put("issued-lab-data", lab);

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(IssuedLab.class, new IssuedLabConverter());
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.toJson(map);
    }

    public static String getLabJson(Lab lab) {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("type", "Lab");
        map.put("lab-data", lab);

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Lab.class, new LabConverter());
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.toJson(map);
    }

    private static Map<String, Object> getGroupMap(Group group){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("group", group);

        for (SubGroup subGroup : group.getSubGroupList()){
            map.put("subGroup", subGroup);
            map.put("lecturer", subGroup.getLecturer());
        }

        return map;
    }

    private static Map<String, Object> getStudentMarkMap(Student student) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("student", student);

        for (LabMark labMark : student.getLabMarkList()) {
            String labNum = "lab" + labMark.getIssuedLab().getLabDescription().getNumberOfLab();

            map.put(labNum, labMark);
        }

        for (TestMark testMark : student.getTestMarkList()) {
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

        for (UniversityClass universityClass : universityClasses) {
            map.put(universityClass.getDataTime(), new Class(universityClass,
                    !student.getMissedUniversityClassesList().contains(universityClass)));
        }

        return map;
    }

    private static Map<String, Object> getStudentRedactMap(Student student, boolean editable) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("student", student);

        map.put("eMail", new Template("cell-ui" + (editable ? " info-cell-editable" : ""), student.geteMail())); //класс и значение для емаила
        map.put("gitURL", new Template("cell-ui" + (editable ? " info-cell-editable" : ""), student.getGitURL())); //класс и значение для юрл

        return map;
    }

    private static ArrayList<String> sortHeader(Set<String> set) {
        ArrayList<String> strings = new ArrayList<String>();

        strings.add("student");

        for (int i = 1; ; i++) {
            if (set.contains("lab" + i)) {
                strings.add("lab" + i);
            } else
                break;
        }

        for (int i = 1; ; i++) {
            if (set.contains("test" + i)) {
                strings.add("test" + i);
            } else
                break;
        }

        strings.add("bonus");
        strings.add("itog");

        return strings;
    }
}
