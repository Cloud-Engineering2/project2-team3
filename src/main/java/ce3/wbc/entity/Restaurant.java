package ce3.wbc.entity;

import ce3.wbc.entity.attribute.Address;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
    private Chef chef;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public static Restaurant of(String restName, String restImg, String restPhone, Address address, boolean restRental, boolean groupReservation, boolean corkage, boolean noKidsZone,Chef chef, List<Comment> comments) {
        return new Restaurant(null, restName, restImg, restPhone, address, restRental, groupReservation, corkage, noKidsZone, chef, new ArrayList<>());
    }
}
