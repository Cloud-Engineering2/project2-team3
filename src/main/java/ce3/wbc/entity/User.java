package ce3.wbc.entity;

import ce3.wbc.entity.attribute.UserRole;
import ce3.wbc.util.UserRoleConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private Integer uId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_password", nullable = false)
    private String userPassword;
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "user_role",  nullable = false)
    @Convert(converter = UserRoleConverter.class)
    private UserRole userRole;
    public static User of(String userName, String userPassword, String userId) {
        return new User(null ,userName, userPassword, userId, UserRole.USER);
    }

    public static User of(Integer uId,String userName, String userPassword, String userId, UserRole userRole) {
        return new User(uId, userName, userPassword, userId,
                userRole != null ? userRole : UserRole.USER );
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User user = (User) o;
        return uId != null && Objects.equals(uId, user.uId);
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }


}
