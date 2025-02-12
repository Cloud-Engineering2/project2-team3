package ce3.wbc.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class WbcAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final MessageSource messageSource;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String errorMessage = "로그인에 실패하였습니다. 아이디 또는 비밀번호를 확인해주세요!";

        if (exception instanceof UsernameNotFoundException) {
            errorMessage = messageSource.getMessage("username.notfound", null,errorMessage ,request.getLocale());
        } else if (exception instanceof BadCredentialsException) {
            errorMessage = messageSource.getMessage("bad.credentials", null,errorMessage ,request.getLocale());
        } else if (exception instanceof LockedException) {
            errorMessage = "계정이 잠겼습니다. 관리자에게 문의하세요.";
        } else if (exception instanceof CredentialsExpiredException) {
            errorMessage = "계정이 잠겼습니다. 관리자에게 문의하세요.";
        } else if (exception instanceof AccountExpiredException) {
            errorMessage = "계정이 잠겼습니다. 관리자에게 문의하세요.";
        } else if (exception instanceof DisabledException) {
            errorMessage = "비밀번호가 만료되었습니다. 새 비밀번호로 변경해주세요.";
        }
        request.setAttribute("errorMessage", errorMessage);
        response.sendRedirect(request.getContextPath() + "/login?error=");
    }
}
