package ce3.wbc.controller.rto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * DTO for {@link ce3.wbc.entity.Chef}
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class ChefReq {
    @NotBlank(message = "chefName 필수 입력 값입니다.")
    private  String chefName;
    @NotBlank(message = "chefCategory 필수 입력 값입니다.")
    private String chefCategory;
}

