package ce3.wbc.controller.rto.response;

import ce3.wbc.dto.ChefDto;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class ChefRes {
    private Integer chefId;
    private String chefName;
    private String chefCategory;
    private String chefImage;

    public static ChefRes toResponse(ChefDto chefDto) {
        if (chefDto == null) {
            return null;
        }
        return ChefRes.builder()
                .chefId(chefDto.getChefId())
                .chefName(chefDto.getChefName())
                .chefCategory(chefDto.getChefCategory())
                .chefImage(chefDto.getChefImage())
                .build();
    }
}
