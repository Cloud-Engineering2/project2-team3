package ce3.wbc.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public static User of(String userName, String userPassword, String userId) {
        return new User(null,userName, userPassword, userId);
    }


}
