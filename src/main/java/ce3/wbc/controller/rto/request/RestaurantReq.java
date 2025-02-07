package ce3.wbc.controller.rto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * DTO for {@link ce3.wbc.entity.Restaurant}
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class RestaurantReq {
    @NotBlank(message = "chefName은 필수 입력 값입니다.")
    private String restName;
    @Valid
    private AddressReq address;
    @NotBlank(message = "chefName은 필수 입력 값입니다.")
    private String restPhone;

    // boolean은 null이 될 수 없으므로 별도 검증 필요 없음.
    private boolean restRental;
    private boolean groupReservation;
    private boolean corkage;
    private boolean noKidsZone;

    @NotBlank(message = "chefName은 필수 입력 값입니다.")
    private String chefName;
}
