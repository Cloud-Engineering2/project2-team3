package ce3.wbc.controller;

import ce3.wbc.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("wbs/replies")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

}
