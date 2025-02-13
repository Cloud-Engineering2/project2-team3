package ce3.wbc.controller;

import ce3.wbc.controller.rto.request.SignupForm;
import ce3.wbc.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //가입페이지
    @GetMapping("/sign-up")
    public String getSignUp(Model model) {
        model.addAttribute("signupForm", new SignupForm());
        log.info("|>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.getSignUp");
        return "sign-up";
    }

    // 회원가입 처리
    @PostMapping("/sign-up")
    public String SignUp (@Valid @ModelAttribute SignupForm form, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                log.warn("회원가입 유효성 검사 실패: {}", bindingResult.getAllErrors());
                return "sign-up";
            }
            userService.createUser(SignupForm.toDto(form));
        } catch (Exception e) {
            log.error("회원가입 중 예외 발생: ", e);
            return "error/500";
        }
        return "redirect:/users/login";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String getLoginPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        // 이전페이지URL저장
        String referer = request.getHeader("Referer");
        if ( session != null && referer != null && !referer.contains("/login") && !referer.contains("/sign-up") ) {
            session.setAttribute("prevPage", referer);
        }
        //에러메세지처리, 한번만 표시후 삭제
        if (session != null) {
            if (session.getAttribute("errorMessage")!= null) {
                model.addAttribute("errorMessage", session.getAttribute("errorMessage"));
                session.removeAttribute("errorMessage");
            }
        }
        return "login";
    }

    // @PostMapping("/login")public String login() {} : 세큐리티 처리
    // @PostMapping("/logout")public String logout() {} 세큐리티 처리



}
