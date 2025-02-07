package ce3.wbc.dto;

import ce3.wbc.entity.Chef;
import ce3.wbc.entity.attribute.Address;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


/**
 * DTO for {@link ce3.wbc.entity.Restaurant}
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class RestaurantDto {
    private  Integer restId;
    private  String restName;
    private  String restImg;
    private Address address;
    private  String restRental;
    private  String groupReservation;
    private  String corkage;
    private  String noKidsZone;

    private Chef chef;
}