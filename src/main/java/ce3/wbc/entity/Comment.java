package ce3.wbc.entity;

import ce3.wbc.entity.attribute.AuditingFields;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Comment extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comm_id")
    private Integer commId;
    @Column(name = "comm_content", nullable = false)
    private String commContent;

    @Column(name = "comm_star", nullable = false)
    private String commStar;


    //연관 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id", nullable = true)
    private Restaurant restaurant;

    //연관 관계
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "user_id", nullable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private User user;


    public static Comment of(String commContent, String commStar, Restaurant restaurant, User user) {
        return new Comment(null, commContent, commStar, restaurant, user);
    }
    
    public Comment update(Integer commId, String commContent, String commStar) {
		this.commContent = commContent;
		this.commStar = commStar;
    	return null;
    	
    }
    
    public void assignToRestaurant(Restaurant restaurant) {
        if (restaurant == null) {
            throw new IllegalArgumentException("댓글은 반드시 특정 레스토랑에 속해야 합니다.");
        }
        this.restaurant = restaurant;
    }

}

