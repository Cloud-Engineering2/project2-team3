package ce3.wbc.dto;


import ce3.wbc.entity.Comment;
import ce3.wbc.entity.Restaurant;
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

    private RestaurantDto restaurantDto;
    private UserDto userDto;
    
    private Restaurant restaurant;
    
    private CommentDto(Integer commId, String commContent, String commStar, RestaurantDto restaurantDto, UserDto userDto) {
    	this.commId = commId;
    	this.commContent = commContent;
    	this.commStar = commStar;
    	this.restaurantDto = restaurantDto;
    	this.userDto = userDto;
    }
    
    private CommentDto(Integer commId, String commContent, String commStar, Restaurant restaurant, UserDto userDto) {
    	this.commId = commId;
    	this.commContent = commContent;
    	this.commStar = commStar;
//    	this.restaurantDto = restaurantDto;
    	this.userDto = userDto;
    	this.restaurant = restaurant;
    }
    
    
    public static CommentDto of(Integer commId, String commContent, String commStar, RestaurantDto restaurantDto, UserDto userDto) {
    	return new CommentDto(commId, commContent, commStar, restaurantDto, userDto);
    }
    
    public static CommentDto of(String commContent, String commStar, RestaurantDto restaurantDto, UserDto userDto) {
    	return of(null, commContent, commStar, restaurantDto, userDto);
    }
    
    public static CommentDto of(String commContent, String commStar, Restaurant restaurant, UserDto userDto) {
    	return new CommentDto(null, commContent, commStar, restaurant, userDto);
    }

    public static CommentDto toCommentDto(Comment comment) {
        return CommentDto.builder()
                .commId(comment.getCommId())
                .commContent(comment.getCommContent())
                .commStar(comment.getCommStar())
                .restaurantDto(RestaurantDto.toDto(comment.getRestaurant()))
                .userDto(UserDto.toDto(comment.getUser()))
                .build();
    }
    
    public static CommentDto toCommentDto(Comment comment, Restaurant restaurant) {
        return CommentDto.builder()
                .commId(comment.getCommId())
                .commContent(comment.getCommContent())
                .commStar(comment.getCommStar())
                .restaurant(restaurant)
                .userDto(UserDto.toDto(comment.getUser()))
                .build();
    }

    public static Comment toEntity(CommentDto commentDto) {
        return Comment.of(
                commentDto.getCommContent(),
                commentDto.getCommStar(),
                RestaurantDto.toEntity(commentDto.getRestaurantDto()),
                UserDto.toEntity(commentDto.getUserDto())
        );
    }
    

}




