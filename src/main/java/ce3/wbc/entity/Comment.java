package ce3.wbc.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comm_id")
    private Integer commId;

    @Column(name = "comm_content")
    private String commContent;

    @Column(name = "comm_star")
    private String commStar;
    

    //연관 관계
    @ManyToOne
    @JoinColumn(name = "rest_id")
    private Restaurant Restaurant;

    //연관 관계
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
