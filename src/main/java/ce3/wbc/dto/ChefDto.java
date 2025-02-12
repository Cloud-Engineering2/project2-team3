package ce3.wbc.dto;

import ce3.wbc.entity.Chef;
import lombok.*;

/**
 * DTO for {@link ce3.wbc.entity.Chef}
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
public class ChefDto  {
    private Integer chefId;
    private String chefName;
    private String chefCategory;
    private String chefImage;
    private String originalImgName;

    public static ChefDto toDto(Chef chef) {
        if (chef == null) {
            return new ChefDto(-1, "없어용", "W/B", "default.jpg","default");
        }
        return ChefDto.builder()
                .chefId(chef.getChefId())
                .chefName(chef.getChefName())
                .chefCategory(chef.getChefCategory())
                .chefImage(chef.getChefImage())
                .originalImgName(chef.getOriginalImgName())
                .build();
    }

    public static Chef toEntity(ChefDto chefDto) {
        return Chef.of(
                chefDto.getChefName(),
                chefDto.getChefCategory(),
                chefDto.getChefImage(),
                chefDto.getOriginalImgName()
        );
    }

    public static ChefDto of(String chefName, String chefCategory, String chefImage, String originalImgName) {
        return new ChefDto(null,chefName, chefCategory, chefImage, originalImgName);
    }
}