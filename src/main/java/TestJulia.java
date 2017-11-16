import resources.HibernateShell;
import intercalationJavaHTML.StudentListTableGenerator;

public class TestJulia {

    public static void main(String ... arg){

        /*ControlInformationTableGenerator tableGenerator = new ControlInformationTableGenerator();
        group group = HibernateShell.getGroupKeeper().getGroupList().get(0);
        tableGenerator.createTable(
                HibernateShell
                .getGroupKeeper()
                .getGroupList()
                .get(0)
                .getSubGroup("1"));
        return;*/
        StudentListTableGenerator tableGenerator = new StudentListTableGenerator();
        tableGenerator.createTable(
                HibernateShell
                        .getGroupKeeper()
                        .getGroupList()
                        .get(0)
                        .getSubGroup("1"));
        return;

    }
}
