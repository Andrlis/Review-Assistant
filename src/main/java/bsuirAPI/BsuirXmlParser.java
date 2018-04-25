package bsuirAPI;

import bsuirAPI.timetable.Lesson;
import bsuirAPI.timetable.Schedule;
import bsuirAPI.timetable.Timetable;
import data.group.Group;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class BsuirXmlParser implements BsuirParserInterface {

    @Override
    public Timetable parseTimetable(String xml) throws Exception {

        Timetable timetable = new Timetable();

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));

        doc.getDocumentElement().normalize();
        NodeList scheduleNode = doc.getElementsByTagName("scheduleModel");

        for (int day_id = 0; day_id < scheduleNode.getLength(); day_id++) {
            NodeList scheduleModelNodes = scheduleNode.item(day_id).getChildNodes();

            Schedule schedule = new Schedule();
            for (int lesson_id = 0; lesson_id < scheduleModelNodes.getLength(); lesson_id++) {

                if (scheduleModelNodes.item(lesson_id).getNodeName().equals("schedule")) {

                    Element xmlSubject = (Element) scheduleModelNodes.item(lesson_id);
                    Lesson lesson = new Lesson();
                    lesson.setSubject(xmlSubject.getElementsByTagName("subject").item(0).getTextContent());
                    lesson.setLessonType(xmlSubject.getElementsByTagName("lessonType").item(0).getTextContent());
                    lesson.setLessonTime(xmlSubject.getElementsByTagName("lessonTime").item(0).getTextContent());

                    lesson.setNumSubgroup(new Integer(xmlSubject.getElementsByTagName("numSubgroup")
                            .item(0).getTextContent()));

                    for (int week_id = 0; week_id < xmlSubject.getElementsByTagName("weekNumber").getLength(); week_id++) {
                        lesson.getWeekNumber().add(new Integer(xmlSubject.getElementsByTagName("weekNumber")
                                .item(week_id).getTextContent()));
                    }

                    schedule.getLessons().add(lesson);

                } else if (scheduleModelNodes.item(lesson_id).getNodeName().equals("weekDay")) {
                    schedule.setWeekDay(scheduleModelNodes.item(lesson_id).getTextContent());
                }
            }

            timetable.getSchedules().add(schedule);
        }
        return timetable;
    }
}