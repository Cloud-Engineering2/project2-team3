package ce3.wbc.controller.rto.response;

import ce3.wbc.dto.RestaurantDto;
import ce3.wbc.entity.attribute.Address;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class RestaurantRes {
    private Integer restId;
    private String restName;
    private String restImg;
    private String originalImgName;
    private String restPhone;
    private Address address;
    private boolean restRental;
    private boolean groupReservation;
    private boolean corkage;
    private boolean noKidsZone;
    private ChefRes chef;
    private List<CommentRes> comments;

    public static RestaurantRes toResponse(RestaurantDto restaurantDto) {
        if (restaurantDto == null) {
            return null;
        }
        return RestaurantRes.builder()
                .restId(restaurantDto.getRestId())
                .restName(restaurantDto.getRestName())
                .restImg(restaurantDto.getRestImg())
                .originalImgName(restaurantDto.getOriginalImgName())
                .restPhone(restaurantDto.getRestPhone())
                .address(restaurantDto.getAddress())
                .restRental(restaurantDto.isRestRental())
                .groupReservation(restaurantDto.isGroupReservation())
                .corkage(restaurantDto.isCorkage())
                .noKidsZone(restaurantDto.isNoKidsZone())
                .chef(ChefRes.toResponse(restaurantDto.getChefDto()))
                .comments(restaurantDto.getComments().stream() // null 방지 로직 제거
                        .map(CommentRes::toResponse)
                        .collect(Collectors.toList()))
                .build();
    }


}
