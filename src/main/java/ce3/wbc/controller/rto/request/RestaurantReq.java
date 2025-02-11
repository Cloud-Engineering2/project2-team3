package ce3.wbc.controller.rto.request;

import jakarta.annotation.Nullable;
import lombok.*;

/**
 * DTO for {@link ce3.wbc.entity.Restaurant}
 * just find
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class RestaurantReq {
    @Nullable
    private String restName;
    @Nullable
    private AddressReq address;
    @Nullable
    private Boolean restRental;
    @Nullable
    private Boolean groupReservation;
    @Nullable
    private Boolean corkage;
    @Nullable
    private Boolean noKidsZone;
    @Nullable
    private String chefName;
}