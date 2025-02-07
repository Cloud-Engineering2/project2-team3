package ce3.wbc.controller;

import ce3.wbc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("wbs/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


}
