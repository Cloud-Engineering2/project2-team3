package ce3.wbc.dto;

import ce3.wbc.entity.Chef;
import lombok.*;

/**
 * DTO for {@link ce3.wbc.entity.Chef}
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class ChefDto  {
    Integer chefId;
    String chefName;
    String chefCategory;
    String chefImage;

    public static ChefDto toDto(Chef chef) {
        if (chef == null) {
            return new ChefDto(-1, "없어용", "W/B", "default.jpg");
        }
        return ChefDto.builder()
                .chefId(chef.getChefId())
                .chefName(chef.getChefName())
                .chefCategory(chef.getChefCategory())
                .chefImage(chef.getChefImage())
                .build();
    }

    public static Chef toEntity(ChefDto chefDto) {
        return Chef.of(
                chefDto.getChefName(),
                chefDto.getChefCategory(),
                chefDto.getChefImage()
        );
    }

}