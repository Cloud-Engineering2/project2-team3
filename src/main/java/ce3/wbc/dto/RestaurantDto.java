package ce3.wbc.dto;

import ce3.wbc.entity.Chef;
import ce3.wbc.entity.Comment;
import ce3.wbc.entity.Restaurant;
import ce3.wbc.entity.User;
import ce3.wbc.entity.attribute.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * DTO for {@link ce3.wbc.entity.Restaurant}
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class RestaurantDto {
    private  Integer restId;
    private  String restName;
    private  String restImg;
    private String originalImgName;
    private String restPhone;
    private Address address;
    private  boolean restRental;
    private  boolean groupReservation;
    private  boolean corkage;
    private  boolean noKidsZone;
    @JsonIgnore
    private ChefDto chefDto;
    @Builder.Default @JsonIgnore
    private List<CommentDto> comments = new ArrayList<>();

    public static RestaurantDto toDto(Restaurant restaurant) {
        if(restaurant == null) {
            return new RestaurantDto(-1,"없어용", "default.jpg","default","번호없음" ,Address.of("","",""),
                    false,false,false,false,ChefDto.toDto(null), new ArrayList<>());
        }
        return RestaurantDto.builder()
                .restId(restaurant.getRestId())
                .restName(restaurant.getRestName())
                .restImg(restaurant.getRestImg())
                .originalImgName(restaurant.getOriginalImgName())
                .restPhone(restaurant.getRestPhone())
                .address(restaurant.getAddress())
                .restRental(restaurant.isRestRental())
                .groupReservation(restaurant.isGroupReservation())
                .corkage(restaurant.isCorkage())
                .noKidsZone(restaurant.isNoKidsZone())
                .chefDto(ChefDto.toDto(restaurant.getChef()))
                .comments(restaurant.getComments().stream()
                        .map(CommentDto::toCommentDto)
                        .collect(Collectors.toList()))
                .build();

    }
    public static Restaurant toEntityWithComm(RestaurantDto restaurantDto, Chef chef, List<CommentDto> commentDtos, User user) {
        if (chef == null) {
            throw new IllegalArgumentException("레스토랑을 생성하려면 Chef가 반드시 필요합니다."); // ✅ 예외 처리
        }
      
        Restaurant restaurant = toEntity(restaurantDto, chef);

        // CommentDto 리스트 → Comment 엔티티 리스트 변환
        List<Comment> comments = commentDtos.stream()
                .map(dto -> CommentDto.toEntity(dto, restaurant, user)) // ✅ Restaurant을 전달
                .collect(Collectors.toList());

        // Restaurant에 Comment 추가
        restaurant.getComments().addAll(comments);

        return restaurant;
    }

    public static Restaurant toEntity(RestaurantDto restaurantDto, Chef chef) {
        return Restaurant.of(
                restaurantDto.getRestId(),
                restaurantDto.getRestName(),
                restaurantDto.getRestImg(),
                restaurantDto.getOriginalImgName(),
                restaurantDto.getRestPhone(),
                restaurantDto.getAddress(),
                restaurantDto.isRestRental(),
                restaurantDto.isGroupReservation(),
                restaurantDto.isCorkage(),
                restaurantDto.isNoKidsZone(),
                chef,
                new ArrayList<>()
        );
    }
}