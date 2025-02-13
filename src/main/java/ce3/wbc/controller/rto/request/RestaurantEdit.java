package ce3.wbc.controller.rto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * DTO for {@link ce3.wbc.entity.Restaurant}
 * just create
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class RestaurantEdit {
    @NotNull(message = "수정시 필수값입니다")
    private Integer restId;
    @Nullable
    private String restName;
    @Nullable
    private String restEngName;
    @Nullable
    private String originalImgName;
    @Valid
    private AddressReq address;
    @Nullable
    private String restPhone;

    // boolean은 null이 될 수 없으므로 별도 검증 필요 없음.
    private boolean restRental;
    private boolean groupReservation;
    private boolean corkage;
    private boolean noKidsZone;
    @Nullable
    private String chefName;




}
