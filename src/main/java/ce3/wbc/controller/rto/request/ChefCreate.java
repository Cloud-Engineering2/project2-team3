package ce3.wbc.controller.rto.request;

import ce3.wbc.dto.ChefDto;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class ChefCreate {
    private String chefName;
    private String chefCategory;
    @Pattern(regexp = "^[a-zA-Z0-]*$", message = "영어와 숫자만 입력 가능합니다.")
    private String chefEngName;
    @Pattern(regexp = "^[a-zA-Z0-]*$", message = "영어와 숫자만 입력 가능합니다.")
    private String originalImgName;

    public static ChefDto toChefDto (ChefCreate chefCreate) {
        return ChefDto.of(
                chefCreate.getChefName(),
                chefCreate.getChefCategory(),
                chefCreate.getChefEngName(),
                chefCreate.getOriginalImgName()
        );
    }


}
