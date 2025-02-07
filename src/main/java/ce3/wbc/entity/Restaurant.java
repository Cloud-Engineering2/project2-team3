package ce3.wbc.entity;

import ce3.wbc.entity.attribute.Address;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rest_id")
    private Integer restId;

    @Column(name = "restName")
    private String restName;

    @Column(name = "rest_img")
    private String restImg;

    @Embedded
    @Column(name = "rest_address")
    private Address address;

    @Column(name = "rest_rental",columnDefinition = "TINYINT(1)")
    private boolean restRental;

    @Column(name = "group_reservation",columnDefinition = "TINYINT(1)")
    private boolean groupReservation;

    @Column(name = "corkage",columnDefinition = "TINYINT(1)")
    private boolean corkage;

    @Column(name = "no_kids_zone",columnDefinition = "TINYINT(1)")
    private boolean noKidsZone;

    //연관 관계
    @ManyToOne
    @JoinColumn(name = "chef_id")
    private Chef chef;

}
