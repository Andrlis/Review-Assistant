//import Data.UniversityClass;
//import org.hibernate.HibernateException;
//import org.hibernate.Metamodel;
//import org.hibernate.query.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//import javax.persistence.metamodel.EntityType;
//
//import java.util.Date;
//import java.util.Map;
//
///**
// * Created by kesso on 15.08.17.
// */
//public class Main {
//    private static final SessionFactory ourSessionFactory;
//
//    static {
//        try {
//            Configuration configuration = new Configuration();
//            configuration.addAnnotatedClass(UniversityClass.class);
//            configuration.configure();
//
//            ourSessionFactory = configuration.buildSessionFactory();
//        } catch (Throwable ex) {
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static Session getSession() throws HibernateException {
//        return ourSessionFactory.openSession();
//    }
//
//    public static void main(final String[] args) throws Exception {
//        final Session session = getSession();
//        try {
//            UniversityClass universityClass = new UniversityClass();
//            universityClass.setDate(new Date());
//            universityClass.setSubGroupID(0);
//            System.out.println(universityClass);
//
//            session.beginTransaction();
//            session.save(universityClass);
//            session.getTransaction().commit();
//            session.close();
//        } finally {
//            session.close();
//        }
//    }
//}