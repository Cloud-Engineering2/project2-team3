package ce3.wbc.dto;

import ce3.wbc.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * DTO for {@link ce3.wbc.entity.User}
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class UserDto  {
    private  Integer uId;
    private  String userName;
    private  String userPassword;
    private  String userId;

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .uId(user.getUId())
                .userName(user.getUserName())
                .userPassword(user.getUserPassword())
                .userId(user.getUserId())
                .build();
    }

    public static User toEntity(UserDto userDto) {
        return User.of(
                userDto.getUserName(),
                userDto.getUserPassword(),
                userDto.getUserId()
        );
    }
}