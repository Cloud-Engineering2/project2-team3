package ce3.wbc.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WbcAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String prevPage = (String) request.getSession().getAttribute("prevPage");
        request.getSession().removeAttribute("prevPage");
        if (prevPage != null && !prevPage.equals("/") && !prevPage.contains("/login")) {
            getRedirectStrategy().sendRedirect(request, response,prevPage);
        } else {
            getRedirectStrategy().sendRedirect(request, response,"/chefs");
        }

    }



}
