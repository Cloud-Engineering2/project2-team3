package ce3.wbc.controller.rto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class AddressReq {
    @NotBlank(message = "city 필수 입력 값입니다.")
    private String city;

    @NotBlank(message = "street 필수 입력 값입니다.")
    private String street;

    @NotBlank(message = "zipcode 필수 입력 값입니다.")
    private String zipcode;

}
