package ce3.wbc.controller.rto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

/**
 * DTO for {@link ce3.wbc.entity.Restaurant}
 * just create
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class RestaurantCreate {
    @NotBlank(message = "Restaurant Name은 필수 입력 값입니다.")
    private String restName;
    @Pattern(regexp = "^[a-zA-Z0-]*$", message = "영어와 숫자만 입력 가능합니다.")
    private String restEngName;
    @Pattern(regexp = "^[a-zA-Z0-]*$", message = "영어와 숫자만 입력 가능합니다.")
    private String originalImgName;
    @Valid
    private AddressCreate address;
    @NotBlank(message = "chefName은 필수 입력 값입니다.")
    private String restPhone;

    // boolean은 null이 될 수 없으므로 별도 검증 필요 없음.
    private boolean restRental;
    private boolean groupReservation;
    private boolean corkage;
    private boolean noKidsZone;

    @NotBlank(message = "chef Name은 필수 입력 값입니다.")
    private String chefName;
}
