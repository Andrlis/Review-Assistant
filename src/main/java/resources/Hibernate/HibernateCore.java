package resources.Hibernate;

import data.Student;
import data.UniversityClass;
import data.User;
import data.group.Group;
import data.group.GroupsKeeper;
import data.lab.LabsKeeper;
import data.lecturer.LecturerKeeper;
import data.mark.LabMark;
import data.mark.TestMark;
import data.test.TestKeeper;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class HibernateCore {
    private final Logger logger = Logger.getLogger(HibernateCore.class);
    private final SessionFactory ourSessionFactory;

    private static volatile HibernateCore ourInstance;

    public static HibernateCore getInstance() {
        if (ourInstance == null) {
            synchronized (HibernateCore.class) {
                if (ourInstance == null) {
                    ourInstance = new HibernateCore();
                }
            }
        }
        return ourInstance;
    }

    private HibernateCore() {
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(UniversityClass.class);
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private Session getSession() throws HibernateException {
        logger.info("Get session.");
        return ourSessionFactory.openSession();
    }

    public void SQLQuery(String query) throws HibernateShellQueryException {
        final Session session = getSession();
        try {
            session.getTransaction().begin();
            session.createSQLQuery(query).executeUpdate();
            session.getTransaction().commit();
        } catch (javax.persistence.TransactionRequiredException ex) {
            throw new HibernateShellQueryException(ex);
        } finally {
            logger.info("Close session.");
            session.close();
        }
    }

    public List<Integer> getStudentsId() {
        final Session session = getSession();
        List<Integer> answer = null;
        try {
            answer = session.createQuery("select id from Student").list();
        } catch (javax.persistence.TransactionRequiredException ex) {
            System.out.println(ex);
        } finally {
            logger.info("Close session.");
            session.close();
        }
        return answer;
    }

    public Student getStudentById(String id) throws HibernateShellQueryException {
        final Session session = getSession();
        Student student;
        try {
            student = (Student) session.createQuery("from Student student WHERE student.id = " + id).list().get(0);
        } catch (Exception e) {
            throw new HibernateShellQueryException(e);
        } finally {
            logger.info("Close session.");
            session.close();
        }
        return student;
    }

    public TestMark getTestMarkById(Integer id) throws HibernateShellQueryException {
        final Session session = getSession();
        TestMark testMark = null;
        try {
            session.enableFilter("coefficientFilter");
            testMark = (TestMark) session.get(TestMark.class, id);
        } catch (Exception e) {
            throw new HibernateShellQueryException(e);
        } finally {
            logger.info("Close session.");
            session.close();
        }

        return testMark;
    }

    public LabMark getLabMarkById(Integer id) throws HibernateShellQueryException {
        logger.info("Start update lab coeff.");
        final Session session = getSession();
        LabMark labMark = null;
        try {
            session.enableFilter("coefficientFilter");
            labMark = (LabMark) session.get(LabMark.class, id);
        } catch (Exception e) {
            throw new HibernateShellQueryException(e);
        } finally {
            logger.info("Close session.");
            session.close();
        }

        return labMark;
    }

    public Group getGroupByGroupNumber(String number) throws HibernateShellQueryException {
        logger.info("Start get group by groupnumber(" + number + ").");
        final Session session = getSession();
        List answer;
        try {
            answer = session.createQuery("from Group group where group.numberOfGroup ='" + number + "'").list();
        } catch (Exception e) {
            throw new HibernateShellQueryException(e);
        } finally {
            logger.info("Close session.");
            session.close();
        }
        if (answer != null && answer.size() == 1) {
            logger.info("group found. End get group by groupnumber.");
            return (Group) answer.get(0);
        }
        logger.info("group not found. End get group by groupnumber.");
        return null;
    }

    public User getUserByUserName(String username) {
        logger.info("Start get user by username(" + username + ").");
        final Session session = getSession();
        List answer;
        try {
            answer = session.createQuery("from User user where user.username ='" + username + "'").list();
        } finally {
            logger.info("Close session.");
            session.close();
        }
        if (answer != null && answer.size() == 1) {
            logger.info("User found. End get user by username.");
            return (User) answer.get(0);
        }
        logger.info("User not found. End get user by username.");
        return null;
    }

    public Long getNumberOfLab() throws HibernateShellQueryException {
        logger.info("Start get number of labs.");
        final Session session = getSession();
        Long answer;
        try {
            answer = (Long) session.createQuery("SELECT COUNT(*) FROM Lab").uniqueResult();
        } catch (Exception e) {
            throw new HibernateShellQueryException(e);
        } finally {
            logger.info("Close session.");
            session.close();
        }
        logger.info("End get number of labs.");
        return answer;
    }

    public Long getNumberOfTests() throws HibernateShellQueryException {
        logger.info("Start get number of tests.");
        final Session session = getSession();
        Long answer;
        try {
            answer = (Long) session.createQuery("SELECT COUNT(*) FROM Test").uniqueResult();
        } catch (Exception e) {
            throw new HibernateShellQueryException(e);
        } finally {
            logger.info("Close session.");
            session.close();
        }
        logger.info("End get number of tests.");
        return answer;
    }

    public void save(Object object) {
        logger.info("End save.");
        final Session session = getSession();
        try {
            session.save(object);
        } finally {
            logger.info("Close session.");
            session.close();
        }
    }

    public void delete(Object object) {
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

    public void update(Object object) {
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

    public TestKeeper getTestKeeper() throws HibernateShellQueryException {
        logger.info("Start get test keeper.");
        final Session session = getSession();
        TestKeeper testKeeper = new TestKeeper();
        try {
            session.enableFilter("coefficientFilter");
            testKeeper.setTestList(session.createQuery("from Test").list());
        } catch (Exception e) {
            throw new HibernateShellQueryException(e);
        } finally {
            logger.info("Close session.");
            session.close();
        }
        logger.info("End get test keeper.");
        return testKeeper;
    }

    public LecturerKeeper getLecturerKeeper() throws HibernateShellQueryException {
        logger.info("Start get lectuturer keeper.");
        final Session session = getSession();
        LecturerKeeper lecturerKeeper = new LecturerKeeper();
        try {
            session.enableFilter("coefficientFilter");
            lecturerKeeper.setLecturerList(session.createQuery("from Lecturer").list());
        } catch (Exception e) {
            throw new HibernateShellQueryException(e);
        } finally {
            logger.info("Close session.");
            session.close();
        }
        logger.info("End get lecturer keeper.");
        return lecturerKeeper;
    }

    public LabsKeeper getLabsKeeper() throws HibernateShellQueryException {
        logger.info("Start get labs keeper.");
        final Session session = getSession();
        LabsKeeper labsKeeper = new LabsKeeper();
        try {
            session.enableFilter("coefficientFilter");
            labsKeeper.setLabList(session.createQuery("from Lab").list());
        } catch (Exception e) {
            throw new HibernateShellQueryException(e);
        } finally {
            logger.info("Close session.");
            session.close();
        }
        logger.info("End get labs keeper.");
        return labsKeeper;
    }

    public GroupsKeeper getGroupKeeper() throws HibernateShellQueryException {
        logger.info("Start get group keeper.");
        final Session session = getSession();
        GroupsKeeper groupsKeeper = new GroupsKeeper();
        try {
            session.enableFilter("coefficientFilter");
            groupsKeeper.setGroupList(session.createQuery("from Group").list());
        } catch (Exception e) {
            throw new HibernateShellQueryException(e);
        } finally {
            logger.info("Close session.");
            session.close();
        }
        logger.info("End get group keeper.");
        return groupsKeeper;
    }
}