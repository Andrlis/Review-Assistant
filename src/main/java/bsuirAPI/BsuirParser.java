package bsuirAPI;

import bsuirAPI.bsuirTimetable.DayTimetable;
import bsuirAPI.bsuirTimetable.Subject;
import bsuirAPI.bsuirTimetable.Timetable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import data.Group.Group;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 * Created by Andrey on 14.07.2017.
 * Parse BSUIR server`s responses.
 */
public class BsuirParser {
    private static final Logger logger = Logger.getLogger(BsuirParser.class);

    /**
     * Parse xml-file with groups.
     *
     * @param groups
     * @return
     * @throws Exception
     */
    public static ArrayList<Group> parseGroups(String groups) throws Exception {
        logger.info("Start parse group.");
        ArrayList<Group> groupList = new ArrayList<Group>();
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(new InputSource(new ByteArrayInputStream(groups.getBytes("utf-8"))));

        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("studentGroup");

        for (int temp = 0; temp < nodeList.getLength(); temp++) {
            Node node = nodeList.item(temp);
            Group group = new Group();
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                group.setScheduleApiGroupNumber(element.getElementsByTagName("id").item(0).getTextContent());
                group.setNumberOfGroup(element.getElementsByTagName("name").item(0).getTextContent());
                groupList.add(group);
            }
        }
        logger.info("End parse group.");
        return groupList;
    }

    /**
     * Parse xml-file with groups`s timetable.
     * @param xmlTimetable
     * @return
     * @throws Exception
     */
    public static Timetable parseTimetable(String xmlTimetable) throws Exception {
        logger.info("Start parse timetable.");
        Timetable timetable = new Timetable();

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(new InputSource(new ByteArrayInputStream(xmlTimetable.getBytes("utf-8"))));

        doc.getDocumentElement().normalize();
        NodeList schedule = doc.getElementsByTagName("scheduleModel");

        for (int day_id = 0; day_id < schedule.getLength(); day_id++) {
            NodeList scheduleModelNodes = schedule.item(day_id).getChildNodes();

            DayTimetable dayTimetable = new DayTimetable();
            for (int lesson_id = 0; lesson_id < scheduleModelNodes.getLength(); lesson_id++) {
                if (scheduleModelNodes.item(lesson_id).getNodeName() == "schedule") {
                    Element xmlSubject = (Element) scheduleModelNodes.item(lesson_id);
                    Subject subject = new Subject();
                    subject.setLessonName(xmlSubject.getElementsByTagName("subject").item(0).getTextContent());
                    subject.setLessonType(xmlSubject.getElementsByTagName("lessonType").item(0).getTextContent());
                    subject.setTime(xmlSubject.getElementsByTagName("lessonTime").item(0).getTextContent());
                    if(xmlSubject.getElementsByTagName("auditory").item(0).getTextContent()!= null)
                        subject.setClassroom(xmlSubject.getElementsByTagName("auditory").item(0).getTextContent());       //для Физкультуры нет аудитории. Null pointer Exception
                    subject.setSubGroup(xmlSubject.getElementsByTagName("numSubgroup").item(0).getTextContent());
                    for (int week_id = 0; week_id < xmlSubject.getElementsByTagName("weekNumber").getLength(); week_id++) {
                        subject.setWeek(xmlSubject.getElementsByTagName("weekNumber").item(week_id).getTextContent());
                    }
                    dayTimetable.setLesson(subject);
                } else if (scheduleModelNodes.item(lesson_id).getNodeName() == "weekDay") {
                    dayTimetable.setDay(scheduleModelNodes.item(lesson_id).getTextContent());
                }
            }
            timetable.setDay(dayTimetable);
        }
        logger.info("End parse timetable.");
        return timetable;
    }
}
