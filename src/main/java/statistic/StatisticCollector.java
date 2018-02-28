package statistic;

import data.group.Group;
import exceptions.StatisticException;
import resources.Hibernate.HibernateCore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StatisticCollector {

    public static String createStatisticFile(String groupNumber, Date fromDate, Date toDate) throws StatisticException {

        HibernateCore hibernateCore = HibernateCore.getInstance();
        FileBuilder fileBuilder = new FileBuilder();
        Analyzer analyzer = new Analyzer();
        String filePath = "D:\\" + groupNumber /*+ "_" + fromDate + "-" + toDate*/ +".xls";

        try {
            Group group = hibernateCore.getGroupByGroupNumber(groupNumber);
            List<StudentStatistic> statData = analyzer.analyzeGroup(group, fromDate, toDate);
            fileBuilder.writeStatistic(filePath, statData);

        } catch (Exception exc) {
            throw new StatisticException(exc);
        }

        return filePath;
    }
}