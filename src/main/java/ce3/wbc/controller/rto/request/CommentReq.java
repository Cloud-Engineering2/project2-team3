package ce3.wbc.controller.rto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class CommentReq {
    @NotBlank(message = "댓글내용을 입력해주세요.")
    private String commContent;
    @NotBlank(message = "별점은 필수 입력 값입니다.")
    private String commStar;
    @NotNull
    private Integer restId;
}

