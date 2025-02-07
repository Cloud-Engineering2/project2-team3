package ce3.wbc.controller.rto.response;

import ce3.wbc.dto.UserDto;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class UserRes {
    private String userName;
    private String userId;

    public static UserRes toResponse(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return UserRes.builder()
                .userName(userDto.getUserName())
                .userId(userDto.getUserId())
                .build();
    }

}
