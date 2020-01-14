import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.Function;

public class ScoreService {
    private static final SessionFactory factory;
    static {
        try {
            factory = new Configuration().
                    configure().
                    buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static <T> T executeTransaction(Function<Session, T> crudFunction) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            T rt = crudFunction.apply(session);
            tx.commit();
            return rt;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        }
    }

    public Long add(String playername, double points){
        return executeTransaction((session) -> {
            Score score = new ScoreBuilder().
                    playername(playername).
                    points(points).
                    build();
            return (Long) session.save(score);
        });
    }

    public List get(String playername){
        return executeTransaction((session) -> {
            String hql = "FROM Score WHERE playername = :playername";
            Query query = session.createQuery(hql);
            query.setParameter("playername", playername);
            return query.list();
        });
    }

    public void delete(Long scoreId){
        executeTransaction((session) -> {
            Score score = session.get(Score.class, scoreId);
            session.delete(score);
            return Void.TYPE;
        });
    }

    public void update(long scoreId, String playername, double points){
        executeTransaction((session) -> {
            Score score = session.get(Score.class, scoreId);
            score.setPlayername(playername);
            score.setPoints(points);
            session.update(score);
            return Void.TYPE;
        });
    }
}