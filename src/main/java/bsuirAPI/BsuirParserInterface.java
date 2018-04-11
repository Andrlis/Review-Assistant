package bsuirAPI;

import bsuirAPI.timetable.Timetable;

public interface BsuirParserInterface {
    Timetable parseTimetable(String dataForParse) throws Exception;
}
