package ce3.wbc.controller.rto.response;

import ce3.wbc.dto.CommentDto;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class CommentRes {
    private Integer commId;
    private String commContent;
    private String commStar;
//    private RestaurantRes restaurant;
    private UserRes user;

    public static CommentRes toResponse(CommentDto commentDto) {
        if (commentDto == null) {
            return null;
        }
        return CommentRes.builder()
                .commId(commentDto.getCommId())
                .commContent(commentDto.getCommContent())
                .commStar(commentDto.getCommStar())
//                .restaurant(RestaurantRes.toResponse(commentDto.getRestaurantDto()))
                .user(UserRes.toResponse(commentDto.getUserDto()))
                .build();
    }
    
}
