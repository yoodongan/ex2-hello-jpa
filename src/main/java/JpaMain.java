import javax.management.relation.Role;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Locker locker = new Locker();
            locker.setName("lockerA");
            em.persist(locker);

            Member member = new Member();
            member.setName("Scott");
            member.changeLocker(locker);
            em.persist(member);


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally{
            em.close();

        }
        emf.close();
    }
}
