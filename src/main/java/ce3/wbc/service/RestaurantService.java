package ce3.wbc.service;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    @Id
    @GeneratedValue
    @Column(name = "rest_id")
    private int restId;

    @Column(name = "restName")
    private String restName;

    @Column(name = "rest_img")
    private String restImg;

    @Column(name = "rest_rental")
    private String restRental;

    @Column(name = "group_ reservation")
    private String groupReservation;

    @Column(name = "corkage")
    private String corkage;

    @Column(name = "no-kids-zone")
    private String noKidsZone;

}
