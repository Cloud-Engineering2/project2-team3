package ce3.wbc.dto;

import ce3.wbc.entity.Restaurant;
import ce3.wbc.entity.attribute.Address;
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
    private String restPhone;
    private Address address;
    private  boolean restRental;
    private  boolean groupReservation;
    private  boolean corkage;
    private  boolean noKidsZone;
    private ChefDto chefDto;
    private List<CommentDto> comments;

    public static RestaurantDto toDto(Restaurant restaurant) {
        if(restaurant == null) {
            return new RestaurantDto(-1,"없어용", "default.jpg","번호없음" ,Address.of("","",""),
                    false,false,false,false,ChefDto.toDto(null), new ArrayList<>());
        }
        return RestaurantDto.builder()
                .restId(restaurant.getRestId())
                .restName(restaurant.getRestName())
                .restImg(restaurant.getRestImg())
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
    
    public static RestaurantDto toDto(Restaurant restaurant,Integer a) {
        if(restaurant == null) {
            return new RestaurantDto(-1,"없어용", "default.jpg","번호없음" ,Address.of("","",""),
                    false,false,false,false,ChefDto.toDto(null), new ArrayList<>());
        }
        return RestaurantDto.builder()
                .restId(restaurant.getRestId())
                .restName(restaurant.getRestName())
                .restImg(restaurant.getRestImg())
                .restPhone(restaurant.getRestPhone())
                .address(restaurant.getAddress())
                .restRental(restaurant.isRestRental())
                .groupReservation(restaurant.isGroupReservation())
                .corkage(restaurant.isCorkage())
                .noKidsZone(restaurant.isNoKidsZone())
                .chefDto(ChefDto.toDto(restaurant.getChef()))
                .build();

    }

    public static Restaurant toEntity(RestaurantDto restaurantDto, ChefDto chefDto) {
        return Restaurant.of(
                restaurantDto.getRestName(),
                restaurantDto.getRestImg(),
                restaurantDto.getRestPhone(),
                restaurantDto.getAddress(),
                restaurantDto.isRestRental(),
                restaurantDto.isGroupReservation(),
                restaurantDto.isCorkage(),
                restaurantDto.isNoKidsZone(),
                ChefDto.toEntity(chefDto),
                restaurantDto.getComments() != null
                        ? restaurantDto.getComments().stream()
                        .map(CommentDto::toEntity)  // ✅ CommentDto → Comment 변환
                        .collect(Collectors.toList())
                        : new ArrayList<>()

        );
    }
    public static Restaurant toEntity(RestaurantDto restaurantDto) {
        return Restaurant.of(
                restaurantDto.getRestName(),
                restaurantDto.getRestImg(),
                restaurantDto.getRestPhone(),
                restaurantDto.getAddress(),
                restaurantDto.isRestRental(),
                restaurantDto.isGroupReservation(),
                restaurantDto.isCorkage(),
                restaurantDto.isNoKidsZone(),
                restaurantDto.getChefDto() != null ? ChefDto.toEntity(restaurantDto.getChefDto()) : null,
                restaurantDto.getComments() != null
                        ? restaurantDto.getComments().stream()
                        .map(CommentDto::toEntity)  // ✅ CommentDto → Comment 변환
                        .collect(Collectors.toList())
                        : new ArrayList<>()
        );
    }

}