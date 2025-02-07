package ce3.wbc.dto;

import ce3.wbc.entity.Restaurant;
import ce3.wbc.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * DTO for {@link ce3.wbc.entity.Comment}
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class CommentDto {
    private  Integer commId;
    private  String commContent;
    private  String commStar;
    private  String userId;

    private Restaurant Restaurant;
    private User user;

}