
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
            Member member = new Member();
            member.setName("Scott");
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.getReference(Member.class, member.getId());
            System.out.println("findMember.getClass() = " + findMember.getClass()); // proxy
            System.out.println("findMember.getName() = " + findMember.getName());

            Member member1 = em.find(Member.class, member.getId());
            System.out.println("member1.getClass() = " + member1.getClass());
            


//            logic(member, member2);

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

    private static void logic(Member m1, Member m2) {
        System.out.println("m1 == m2 " + (m1 instanceof Member));
        System.out.println("m1 == m2 " + (m2 instanceof Member));

    }


}
