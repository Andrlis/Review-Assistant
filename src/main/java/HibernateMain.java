import Data.Group.Group;
import Data.Group.SubGroup;
import Data.Lab.IssuedLab;
import Data.Lab.Lab;
import Data.Lecturer;
import Data.Mark.LabMark;
import Data.Mark.TestMark;
import Data.Student;
import Data.Test.Test;
import Data.UniversityClass;
import org.hibernate.*;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by kesso on 15.08.17.
 */
public class HibernateMain {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(UniversityClass.class);
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            /*UniversityClass universityClass1 = new UniversityClass();
            universityClass1.setDate(new Date());
            UniversityClass universityClass2 = new UniversityClass();
            universityClass2.setDate(new Date());

            Lecturer lecturer1 = new Lecturer();
            lecturer1.setFullName("lecturer1 full name");

            Group group = new Group();
            group.setAmountOfTest(1);
            group.setNumberOfGroup("550502");
            group.setScheduleApiGroupNumber("47");

            SubGroup subGroup1 = new SubGroup();
            subGroup1.setSubGroupNumber("1");
            ArrayList<UniversityClass> subGroupClasses1 = new ArrayList<UniversityClass>();
            subGroupClasses1.add(universityClass1);
            subGroup1.setUniversityClassesList(subGroupClasses1);
            universityClass1.setSubGroup(subGroup1);

            SubGroup subGroup2 = new SubGroup();
            subGroup2.setSubGroupNumber("2");
            ArrayList<UniversityClass> subGroupClasses2 = new ArrayList<UniversityClass>();
            subGroupClasses2.add(universityClass2);
            subGroup2.setUniversityClassesList(subGroupClasses2);
            universityClass2.setSubGroup(subGroup2);

            ArrayList<SubGroup> subGroupArrayList = new ArrayList<SubGroup>();
            subGroupArrayList.add(subGroup1);
            subGroupArrayList.add(subGroup2);
            group.setSubGroupList(subGroupArrayList);
            subGroup1.setGroup(group);
            subGroup2.setGroup(group);

            lecturer1.setSubGroupList(subGroupArrayList);
            subGroup1.setLecturer(lecturer1);
            subGroup2.setLecturer(lecturer1);



            Lab lab = new Lab();
            lab.setKeyWord("322");
            lab.setNumberOfLab(1);

            IssuedLab issuedLab1 = new IssuedLab();
            issuedLab1.setDateOfLastRepoCheck(new Date());
            issuedLab1.setCoefficientOfCurrentDeadline(new Double(0.4));
            issuedLab1.setCurrentDeadline(universityClass2);
            issuedLab1.setLabDescription(lab);
            issuedLab1.setUniversityClassOfIssue(universityClass1);
            IssuedLab issuedLab2 = new IssuedLab();
            issuedLab2.setDateOfLastRepoCheck(new Date());
            issuedLab2.setCoefficientOfCurrentDeadline(new Double(0.4));
            issuedLab2.setCurrentDeadline(universityClass2);
            issuedLab2.setLabDescription(lab);
            issuedLab2.setUniversityClassOfIssue(universityClass1);

            subGroup1.getIssuedLabsList().add(issuedLab1);
            subGroup2.getIssuedLabsList().add(issuedLab2);

            Student student1 = new Student();
            student1.setBonusMark(9);
            student1.seteMail("email1");
            student1.setFulName("student1");
            student1.setGitRepoName("123");
            student1.setGitUserName("456");

            Student student2 = new Student();
            student2.setGitUserName("123");
            student2.setGitRepoName("456");
            student2.setFulName("student2");
            student2.seteMail("2151234");
            student2.getMissedUniversityClassesList().add(universityClass1);

            Test test = new Test();
            test.setTestDate(new Date());
            test.setTestNumber(1);

            TestMark testMark1 = new TestMark();
            testMark1.setTest(test);
            testMark1.setMark(2);
            TestMark testMark2 = new TestMark();
            testMark2.setTest(test);
            testMark2.setMark(3);
            test.getTestMarkList().add(testMark1);
            test.getTestMarkList().add(testMark2);

            student1.getTestMarkList().add(testMark1);
            student2.getTestMarkList().add(testMark2);

            LabMark labMark1 = new LabMark();
            labMark1.setCoefficient(new Double(1));
            labMark1.setLab(lab);
            LabMark labMark2 = new LabMark();
            labMark2.setCoefficient(new Double(0.4));
            labMark2.setLab(lab);
            labMark2.setMark(9);

            student1.getLabMarkList().add(labMark1);
            student2.getLabMarkList().add(labMark2);

            subGroup1.getStudentsList().add(student1);
            subGroup2.getStudentsList().add(student2);
            issuedLab2.getStudentControlList().add(student2);

            session.save(group);*/

            Filter filter = session.enableFilter("coefficientFilter");

            Group group = (Group)session.get(Group.class, 1);

            group.getId();

        } finally {
            session.close();
        }
    }
}