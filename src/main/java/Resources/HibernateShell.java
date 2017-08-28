package Resources;

import Data.Group.Group;
import Data.Group.GroupsKeeper;
import Data.Group.SubGroup;
import Data.Lab.IssuedLab;
import Data.Lab.LabsKeeper;
import Data.Lecturer.Lecturer;
import Data.Lecturer.LecturerKeeper;
import Data.Mark.LabMark;
import Data.Student;
import Data.Test.Test;
import Data.Test.TestKeeper;
import Data.UniversityClass;
import Data.User;
import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.schema.internal.exec.GenerationTargetToDatabase;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kesso on 22.08.17.
 */

@Transactional
public class HibernateShell {
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

    public static GroupsKeeper getGroupKeeper(){
        final Session session = getSession();
        GroupsKeeper groupsKeeper = new GroupsKeeper();
        try {
            session.enableFilter("coefficientFilter");

            groupsKeeper.setGroupList(session.createQuery("from Group").list());

        } finally {
            session.close();
        }
        return groupsKeeper;
    }

    public static LabsKeeper getLabsKeeper() {
        final Session session = getSession();
        LabsKeeper labsKeeper = new LabsKeeper();
        try {
            session.enableFilter("coefficientFilter");

            labsKeeper.setLabList(session.createQuery("from Lab").list());

        } finally {
            session.close();
        }
        return labsKeeper;
    }

    public static LecturerKeeper getLecturerKeeper() {
        final Session session = getSession();
        LecturerKeeper lecturerKeeper = new LecturerKeeper();
        try {
            session.enableFilter("coefficientFilter");

            lecturerKeeper.setLecturerList(session.createQuery("from Lecturer").list());

        } finally {
            session.close();
        }
        return lecturerKeeper;
    }

    public static TestKeeper getTestKeeper() {
        final Session session = getSession();
        TestKeeper testKeeper = new TestKeeper();
        try {
            session.enableFilter("coefficientFilter");

            testKeeper.setTestList(session.createQuery("from Test").list());

        } finally {
            session.close();
        }
        return testKeeper;
    }

    public static Long getNumberOfTests() {
        final Session session = getSession();
        Long answer = null;
        try {
            answer = (Long) session.createQuery("SELECT COUNT(*) FROM Test").uniqueResult();
        } finally {
            session.close();
        }
        return answer;
    }

    public static Long getNumberOfLab() {
        final Session session = getSession();
        Long answer = null;
        try {
            answer = (Long) session.createQuery("SELECT COUNT(*) FROM Lab").uniqueResult();
        } finally {
            session.close();
        }
        return answer;
    }

    public static void update(Object object) {
        final Session session = getSession();
        try {
            session.getTransaction().begin();
            session.update(object);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    /*
    Add by Andrlis.
     */
    public static void delete(Object object) {
        final Session session = getSession();
        try {
            session.getTransaction().begin();
            session.flush();
            session.delete(object);
            session.flush();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public static void save(Object object) {
        final Session session = getSession();
        try {
            session.save(object);
        } finally {
            session.close();
        }
    }

    public static User getUserByUserName(String username) {
        final Session session = getSession();
        List<User> answer = null;
        try {
            answer = session.createQuery("from User user where user.username ='" + username + "'"  ).list();
        } finally {
            session.close();
        }
        if(answer != null && answer.size() == 1) {
            return answer.get(0);
        }
        return null;
    }
}
