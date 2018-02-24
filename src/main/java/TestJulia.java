import checker.RepositoryChecker;
import checker.ScheduleChecker;
import data.group.SubGroup;
import intercalationJavaHTML.StudentListTableGenerator;

public class TestJulia {

    public static void main(String ... arg){

        //RepositoryChecker repositoryChecker = new RepositoryChecker();
        try {
            ScheduleChecker.groupScheduleCheck();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return;

    }
}
