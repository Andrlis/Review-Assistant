import checker.RepositoryChecker;
import data.group.SubGroup;
import resources.Hibernate.HibernateShell;
import intercalationJavaHTML.StudentListTableGenerator;
import resources.Hibernate.HibernateShellQueryException;
import timerTasks.RepositoryCheckerTask;

public class TestJulia {

    public static void main(String ... arg){

        //RepositoryChecker repositoryChecker = new RepositoryChecker();
        try {
            RepositoryChecker.checkForCommitsInGroup(HibernateShell
                    .getGroupByGroupNumber("550501")
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;

    }
}
