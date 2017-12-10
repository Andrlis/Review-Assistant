package resources.Hibernate;

import data.Student;
import data.group.Group;
import data.group.GroupsKeeper;
import data.group.SubGroup;
import data.lab.IssuedLab;
import data.lab.Lab;
import data.lab.LabsKeeper;
import data.lecturer.LecturerKeeper;
import data.mark.LabMark;
import data.mark.TestMark;
import data.test.TestKeeper;
import data.UniversityClass;
import data.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaDelete;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
public class HibernateShell {
    private static final Logger logger = Logger.getLogger(HibernateShell.class);
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
        logger.info("Get session.");
        return ourSessionFactory.openSession();
    }

    public static GroupsKeeper getGroupKeeper(){
        logger.info("Start get group keeper.");
        final Session session = getSession();
        GroupsKeeper groupsKeeper = new GroupsKeeper();
        try {
            session.enableFilter("coefficientFilter");

            groupsKeeper.setGroupList(session.createQuery("from Group").list());

        } finally {
            logger.info("Close session.");
            session.close();
        }
        logger.info("End get group keeper.");
        return groupsKeeper;
    }

    public static LabsKeeper getLabsKeeper() {
        logger.info("Start get labs keeper.");
        final Session session = getSession();
        LabsKeeper labsKeeper = new LabsKeeper();
        try {
            session.enableFilter("coefficientFilter");

            labsKeeper.setLabList(session.createQuery("from Lab").list());

        } finally {
            logger.info("Close session.");
            session.close();
        }
        logger.info("End get labs keeper.");
        return labsKeeper;
    }

    public static LecturerKeeper getLecturerKeeper() {
        logger.info("Start get lectuturer keeper.");
        final Session session = getSession();
        LecturerKeeper lecturerKeeper = new LecturerKeeper();
        try {
            session.enableFilter("coefficientFilter");

            lecturerKeeper.setLecturerList(session.createQuery("from Lecturer").list());

        } finally {
            logger.info("Close session.");
            session.close();
        }
        logger.info("End get lecturer keeper.");
        return lecturerKeeper;
    }

    public static TestKeeper getTestKeeper() {
        logger.info("Start get test keeper.");
        final Session session = getSession();
        TestKeeper testKeeper = new TestKeeper();
        try {
            session.enableFilter("coefficientFilter");

            testKeeper.setTestList(session.createQuery("from Test").list());

        } finally {
            logger.info("Close session.");
            session.close();
        }
        logger.info("End get test keeper.");
        return testKeeper;
    }

    public static Long getNumberOfTests() {
        logger.info("Start get number of tests.");
        final Session session = getSession();
        Long answer = null;
        try {
            answer = (Long) session.createQuery("SELECT COUNT(*) FROM Test").uniqueResult();
        } finally {
            logger.info("Close session.");
            session.close();
        }
        logger.info("End get number of tests.");
        return answer;
    }

    public static Integer getNumberOfNextTest() {
        return (int)(getNumberOfTests() + 1);
    }

    public static Long getNumberOfLab() {
        logger.info("Start get number of labs.");
        final Session session = getSession();
        Long answer = null;
        try {
            answer = (Long) session.createQuery("SELECT COUNT(*) FROM Lab").uniqueResult();
        } finally {
            logger.info("Close session.");
            session.close();
        }
        logger.info("End get number of labs.");
        return answer;
    }

    public static Integer getNumberOfNextLab() {
        return (int)(getNumberOfLab() + 1);
    }

    public static void update(Object object) {
        logger.info("Start update.");
        final Session session = getSession();
        try {
            session.getTransaction().begin();
            session.update(object);
            session.getTransaction().commit();
        } finally {
            logger.info("Close session.");
            session.close();
        }
        logger.info("End update.");
    }

    /*
    Add by Andrlis.
     */
    public static void delete(Object object) {
        logger.info("Start delete.");
        final Session session = getSession();
        try {
            session.getTransaction().begin();
            session.flush();
            session.delete(object);
            session.flush();
            session.getTransaction().commit();
        } finally {
            logger.info("Close session.");
            session.close();
        }
        logger.info("End delete.");
    }

    public static void save(Object object) {
        logger.info("End save.");
        final Session session = getSession();
        try {
            session.save(object);
        } finally {
            logger.info("Close session.");
            session.close();
        }
    }

    public static User getUserByUserName(String username) {
        logger.info("Start get user by username(" + username + ").");
        final Session session = getSession();
        List<User> answer = null;
        try {
            answer = session.createQuery("from User user where user.username ='" + username + "'"  ).list();
        } finally {
            logger.info("Close session.");
            session.close();
        }
        if(answer != null && answer.size() == 1) {
            logger.info("User found. End get user by username.");
            return answer.get(0);
        }
        logger.info("User not found. End get user by username.");
        return null;
    }

    public static Group getGroupByGroupNumber(String number) {
        logger.info("Start get group by groupnumber(" + number + ").");
        final Session session = getSession();
        List<Group> answer = null;
        try {
            answer = session.createQuery("from Group group where group.numberOfGroup ='" + number + "'" ).list();
        } finally {
            logger.info("Close session.");
            session.close();
        }
        if(answer != null && answer.size() == 1) {
            logger.info("group found. End get group by groupnumber.");
            return answer.get(0);
        }
        logger.info("group not found. End get group by groupnumber.");
        return null;
    }


    public static void updateLabMark(Integer id, Integer mark){
        logger.info("Start update lab mark.");
        final Session session = getSession();
        LabMark labMark = null;
        try {
            session.enableFilter("coefficientFilter");

            labMark = (LabMark) session.get(LabMark.class, id);
            labMark.setMark(mark);

            session.getTransaction().begin();
            session.update(labMark);
            session.getTransaction().commit();
        } finally {
            logger.info("Close session.");
            session.close();
        }
        logger.info("End update lab mark.");
    }

    public static void updateLabCoeff(Integer id, Double coeff){
        logger.info("Start update lab coeff.");
        final Session session = getSession();
        LabMark labMark = null;
        try {
            session.enableFilter("coefficientFilter");

            labMark = (LabMark) session.get(LabMark.class, id);
            labMark.setCoefficient(coeff);

            session.getTransaction().begin();
            session.update(labMark);
            session.getTransaction().commit();
        } finally {
            logger.info("Close session.");
            session.close();
        }
        logger.info("End update lab coeff.");
    }

    public static void updateTestMark(Integer id, Integer mark){
        logger.info("Start update test mark.");
        final Session session = getSession();
        TestMark testMark = null;
        try {
            session.enableFilter("coefficientFilter");

            testMark = (TestMark) session.get(TestMark.class, id);
            testMark.setMark(mark);

            session.getTransaction().begin();
            session.update(testMark);
            session.getTransaction().commit();
        } finally {
            logger.info("Close session.");
            session.close();
        }
        logger.info("End update test mark.");
    }

    public static void updateBonusMark(Integer id, Integer mark){
		logger.info("Start update bonus mark.");
		final Session session = getSession();
		Student student = null;
		try {
			session.enableFilter("coefficientFilter");

			student = (Student) session.get(Student.class, id);
			student.setBonusMark(mark);

			session.getTransaction().begin();
			session.update(student);
			session.getTransaction().commit();
		} finally {
			logger.info("Close session.");
			session.close();
		}
		logger.info("End update bonus mark");
    }

    public static void addedLab(String keyWord){
        Lab lab = new Lab();
        lab.setKeyWord(keyWord);
        lab.setNumberOfLab(getNumberOfNextLab());

        save(lab);
    }

    public static void addedNewLab(SubGroup subGroup, UniversityClass universityClassOfIssue){
        Lab lab = getLabsKeeper().getLabByNumber(subGroup.getIssuedLabsList().size() + 1);

        IssuedLab issuedLab = new IssuedLab();

        issuedLab.setLabDescription(lab);
        issuedLab.setUniversityClassOfIssue(universityClassOfIssue);
        issuedLab.setCoefficientOfCurrentDeadline(1.0);
        //TODO getNextLab
        issuedLab.setCurrentDeadline(universityClassOfIssue);
        issuedLab.setDateOfLastRepoCheck(new Date());
        issuedLab.setStudentControlList(subGroup.getStudentsList());
        issuedLab.setSubGroup(subGroup);

        save(issuedLab);

        subGroup.addIssuedLab(issuedLab);

        for(Student student : subGroup.getStudentsList()) {
            LabMark labMark = new LabMark();
            labMark.setIssuedLab(issuedLab);
            labMark.setStudent(student);

            save(labMark);

            student.addLabMark(labMark);
        }


    }

    public static Student getStudentById(String id) {
        final Session session = getSession();
        Student student = null;
        try {
            student = (Student)session.createQuery("from Student student WHERE student.id = " + id).list().get(0);

        } finally {
            logger.info("Close session.");
            session.close();
        }
        return student;
    }

    public static void SQLQuery(String query) {
        final Session session = getSession();
        try {
            session.getTransaction().begin();
            session.createSQLQuery(query).executeUpdate();
            session.getTransaction().commit();
        }
        catch (javax.persistence.TransactionRequiredException ex) {
            System.out.println(ex);
        }
        finally {
                logger.info("Close session.");
                session.close();
        }
    }
}

