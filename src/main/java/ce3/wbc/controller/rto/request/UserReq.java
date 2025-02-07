package ce3.wbc.controller.rto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * DTO for {@link ce3.wbc.entity.User}
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class UserReq {
    @NotBlank(message = "userName 필수 입력 값입니다.")
    private String userName;
    @NotBlank(message = "userPassword 필수 입력 값입니다.")
    private String userPassword;
    @NotBlank(message = "userId 필수 입력 값입니다.")
    private String userId;

}
