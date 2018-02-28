package statistic;

import data.group.Group;
import exceptions.StatisticException;
import resources.Hibernate.HibernateCore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StatisticCollector {
    public void collect(String group_number, String from_date, String to_date) throws StatisticException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        HibernateCore hibernateCore = HibernateCore.getInstance();
        FileBuilder fileBuilder = new FileBuilder();
        Analyzer analyzer = new Analyzer();

        try {
            Date fromDate = dateFormat.parse(from_date);
            Date toDate = dateFormat.parse(to_date);

            Group group = hibernateCore.getGroupByGroupNumber(group_number);

            List<StudentStatistic> statData = analyzer.analyzeGroup(group, fromDate, toDate);
            fileBuilder.writeStatistic("C:\\Andrey\\IntellijIDEA\\TRTPOControlSystem\\StatisticFile\\" + group_number + "_statistic.xls", statData);



        } catch (Exception exc) {
            throw new StatisticException(exc);
        }


    }
}