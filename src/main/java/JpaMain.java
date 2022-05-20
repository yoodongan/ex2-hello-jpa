
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setName("Scott");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member m1 = em.find(Member.class, member.getId());
            Team t1  = m1.getTeam();

            System.out.println("t1.getClass() = " + t1.getClass());


            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }finally{
            em.close();

        }
        emf.close();
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getName();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team.getName() = " + team.getName());

    }



}
