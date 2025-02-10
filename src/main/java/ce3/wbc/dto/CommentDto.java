package ce3.wbc.dto;


import ce3.wbc.entity.Comment;
import ce3.wbc.entity.Restaurant;
import lombok.*;

/**
 * DTO for {@link ce3.wbc.entity.Comment}
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class CommentDto {
    private  Integer commId;
    private  String commContent;
    private  String commStar;

    private Integer restId;
    private UserDto userDto;

    public static CommentDto toCommentDto(Comment comment) {
        return CommentDto.builder()
                .commId(comment.getCommId())
                .commContent(comment.getCommContent())
                .commStar(comment.getCommStar())
                .restId(comment.getRestaurant().getRestId())
                .userDto(UserDto.toDto(comment.getUser()))
                .build();
    }

    public static Comment toEntity(CommentDto commentDto, Restaurant restaurant) {
        return Comment.of(
                commentDto.getCommContent(),
                commentDto.getCommStar(),
                restaurant,
                UserDto.toEntity(commentDto.getUserDto())
        );
    }

}




