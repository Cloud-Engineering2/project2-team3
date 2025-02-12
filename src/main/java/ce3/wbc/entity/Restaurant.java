package ce3.wbc.entity;

import ce3.wbc.entity.attribute.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rest_id")
    private Integer restId;

    @Column(name = "rest_name", nullable = false)
    private String restName;

    @Column(name = "rest_img")
    private String restImg;

    @Column(name = "originalImgName")
    private String originalImgName;

    @Column(name = "rest_phone")
    private String restPhone;

    @Embedded
    @Column(name = "rest_address", nullable = false)
    private Address address;

    @Column(name = "rest_rental", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean restRental;

    @Column(name = "group_reservation", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean groupReservation;

    @Column(name = "corkage", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean corkage;

    @Column(name = "no_kids_zone", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean noKidsZone;

    //연관 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chef_id")
    @JsonIgnore
    private Chef chef;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<>();

    //연관관계 편의 메서드
    public void addComment(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("댓글이 null일 수 없습니다.");
        }
        this.comments.add(comment);
        comment.assignToRestaurant(this); // `Comment`의 필드도 설정
    }



    public static Restaurant of(String restName, String restImg,String originalImgName, String restPhone, Address address, boolean restRental, boolean groupReservation, boolean corkage, boolean noKidsZone, Chef chef, List<Comment> comments) {
        return new Restaurant(null, restName, restImg,originalImgName,restPhone, address, restRental, groupReservation, corkage, noKidsZone, chef,comments);
    }
    public static Restaurant of(Integer restId, String restName, String restImg,String originalImgName, String restPhone, Address address, boolean restRental, boolean groupReservation, boolean corkage, boolean noKidsZone,Chef chef, List<Comment> comments) {
        return new Restaurant(restId, restName, restImg,originalImgName,restPhone, address, restRental, groupReservation, corkage, noKidsZone, chef, comments);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Restaurant that = (Restaurant) o;
        return getRestId() != null && Objects.equals(getRestId(), that.getRestId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
