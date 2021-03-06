package statistic;

import data.group.Group;
import exceptions.StatisticException;
import logics.GroupLogic;

import java.util.Date;
import java.util.List;

public class StatisticCollector {

    public static String createStatisticFile(String groupNumber, Date fromDate, Date toDate) throws StatisticException {
        GroupLogic groupLogic = new GroupLogic();

        FileBuilder fileBuilder = new FileBuilder();
        Analyzer analyzer = new Analyzer();
//        String filePath = "C:\\Andrey\\IntellijIDEA\\TRTPOControlSystem\\TestFiles\\"
//                + groupNumber + ".xls";
        String filePath = ".\\" + groupNumber + ".xls";


        if (fromDate.after(toDate)) {
            throw new StatisticException("Date interval exception.");
        }

        try {
            Group group = groupLogic.getByNumber(groupNumber);
            List<StudentStatistic> statData = analyzer.analyzeGroup(group, fromDate, toDate);
            fileBuilder.writeStatistic(filePath, statData);

        } catch (Exception exc) {
            throw new StatisticException(exc);
        }

        return filePath;
    }
}