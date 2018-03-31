package resources.Hibernate.Controller;

import data.UniversityClass;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import resources.Hibernate.HibernateCore;
import resources.Hibernate.HibernateShellQueryException;
import resources.Hibernate.Interfaces.DataBaseCoreInterface;

import javax.persistence.criteria.*;
import java.util.List;

public class DataBaseCore implements DataBaseCoreInterface {
    private final Logger logger = Logger.getLogger(HibernateCore.class);
    private final SessionFactory ourSessionFactory;

    private static volatile  DataBaseCore ourInstance;

    public static DataBaseCore getInstance() {
        if (ourInstance == null) {
            synchronized (HibernateCore.class) {
                if (ourInstance == null) {
                    ourInstance = new DataBaseCore();
                }
            }
        }
        return ourInstance;
    }

    private DataBaseCore() {
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


    @Override
    public Object getById(Class c, Integer id) throws HibernateShellQueryException {
        logger.info("DataBaseCore.getById(). " + c.getName());

        final Session session = getSession();
        Object answer;

        try {
            answer = (Object) session.get(c, id);
        } catch (Exception e) {
            throw new HibernateShellQueryException(e);
        } finally {
            session.close();
        }

        return answer;
    }

    @Override
    public Object create(Object object) {
        logger.info("DataBaseCore.create(). " + object.getClass().getName());

        final Session session = getSession();

        try {
            session.save(object);
        } finally {
            session.close();
        }

        return object;
    }

    @Override
    public Object update(Object object) {
        logger.info("DataBaseCore.update(). " + object.getClass().getName());

        final Session session = getSession();

        try {
            session.getTransaction().begin();
            session.update(object);
            session.getTransaction().commit();
        } finally {
            session.close();
        }

        return object;
    }

    @Override
    public void delete(Object object) {
        logger.info("DataBaseCore.delete(). " + object.getClass().getName());

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

    @Override
    public Integer getCount(Class c) {
        logger.info("DataBaseCore.getCount(). " + c.getName());

        final Session session = getSession();
        Integer count;

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object> query = builder.createQuery(c);
        Root<Object> root = query.from(c);
        Query<Object> q = session.createQuery(query);

        try {
             count = q.getResultList().size();
        } catch (org.hibernate.NonUniqueResultException | javax.persistence.NoResultException e){
            throw e;
        }

        return count;
    }

    @Override
    public Object getByCriteria(Class c, Object... criteria) {
        logger.info("DataBaseCore.getByCriteria. " + c.getName());

        if(criteria.length % 2 != 0){
            //TODO throw new Exception
            return null;
        }

        final Session session = getSession();

        Object object;

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object> query = builder.createQuery(c);
        Root<Object> root = query.from(c);

        Predicate predicate[] = getPredicates(c, builder, root, criteria);

        query.select(root).where(predicate);


        Query<Object> q = session.createQuery(query);
        try {
            object = q.getSingleResult();
        } catch (org.hibernate.NonUniqueResultException | javax.persistence.NoResultException e){
            throw e;
        }

        return object;
    }

    @Override
    public Integer getNumberCriteria(Class c, Object... criteria) {
        logger.info("DataBaseCore.getByCriteria. " + c.getName());

        if(criteria.length % 2 != 0){
            //TODO throw new Exception
            return null;
        }

        final Session session = getSession();

        Integer number;

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object> query = builder.createQuery(c);
        Root<Object> root = query.from(c);

        Predicate predicate[] = getPredicates(c, builder, root, criteria);

        query.select(root).where(predicate);


        Query<Object> q = session.createQuery(query);
        try {
            number = q.getResultList().size();
        } catch (org.hibernate.NonUniqueResultException | javax.persistence.NoResultException e){
            throw e;
        }

        return number;
    }

    @Override
    public List<Object> getAll(Class c) {
        logger.info("DataBaseCore.getAll(). " + c.getName());

        final Session session = getSession();
        List<Object> answer;

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object> query = builder.createQuery(c);
        Root<Object> root = query.from(c);
        Query<Object> q = session.createQuery(query);

        try {
            answer = q.getResultList();
        } catch (org.hibernate.NonUniqueResultException | javax.persistence.NoResultException e){
            throw e;
        }

        return answer;
    }

    private Predicate[] getPredicates(Class c,CriteriaBuilder builder, Root<Object> root, Object... criteria) {
        Predicate predicate[] = new Predicate[criteria.length / 2];

        for(int i = 0; i < criteria.length / 2; i++) {
            String column = (String) criteria[i * 2];
            Object param = criteria[(i * 2) + 1];

            Path path = null;

            for(String s : column.split("\\.")) {
                if(path == null){
                    path = root.get(s);
                } else {
                    path = path.get(s);
                }
            }

            predicate[i] = builder.equal((Expression)path, param);
        }

        return predicate;
    }
}

