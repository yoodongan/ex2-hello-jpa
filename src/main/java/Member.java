import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",   // 매핑할 데이터베이스 시퀀스 이름.
        initialValue = 1, allocationSize = 50
)
public class Member {
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "MEMBER_SEQ_GENERATOR"
    )
    private Long id;

    @Column(name = "username", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private LocalDate createdDate;
    private LocalDateTime lastModifiedDate;

    @Lob
    private String description;

    public Member() {
    }      // JPA는 동적으로 객체를 생성하므로 기본 생성자가 반드시 필요하다.

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
