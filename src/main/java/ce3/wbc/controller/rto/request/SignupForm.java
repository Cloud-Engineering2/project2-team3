package ce3.wbc.controller.rto.request;

import ce3.wbc.dto.UserDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class SignupForm {

    @NotBlank(message = "userName 필수 입력 값입니다.")
    private String userName;
    @NotBlank(message = "userPassword 필수 입력 값입니다.")
    private String userPassword;
    @NotBlank(message = "userId 필수 입력 값입니다.")
    private String userId;

    public static UserDto toDto(SignupForm form) {
        return UserDto.builder()
                .userName(form.getUserName())
                .userPassword(form.getUserPassword())
                .userId(form.getUserId())
                .build();
    }
}
