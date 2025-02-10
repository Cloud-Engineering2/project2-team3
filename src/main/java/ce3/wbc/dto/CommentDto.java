package ce3.wbc.dto;


import ce3.wbc.entity.Comment;
import ce3.wbc.entity.Restaurant;
import ce3.wbc.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link ce3.wbc.entity.Comment}
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class CommentDto {
    private Integer commId;
    private String commContent;
    private String commStar;

    private Integer restId;
    private UserDto userDto;

    
    public static CommentDto of(Integer commId, String commContent, String commStar, Integer restId, UserDto userDto) {
    	return new CommentDto(commId, commContent, commStar, restId, userDto);
    }
    
    public static CommentDto of(String commContent, String commStar, Integer restId, UserDto userDto) {
    	return of(null, commContent, commStar, restId, userDto);
    }
    

    public static CommentDto toCommentDto(Comment comment) {
        return CommentDto.builder()
                .commId(comment.getCommId())
                .commContent(comment.getCommContent())
                .commStar(comment.getCommStar())
                .restId(comment.getRestaurant().getRestId())
                .userDto(UserDto.toDto(comment.getUser()))
                .build();
    }

    public static Comment toEntity(CommentDto commentDto, Restaurant restaurant, User user) {
        return Comment.of(
                commentDto.getCommContent(),
                commentDto.getCommStar(),
                restaurant,
                user // 주소값
        );
    }
    

}




