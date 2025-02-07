package ce3.wbc.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * DTO for {@link ce3.wbc.entity.Chef}
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class ChefDto  {
    Integer chefId;
    String chefName;
    String chefAddress;
    String chefImage;


}