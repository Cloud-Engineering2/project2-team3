package ce3.wbc.controller;

import ce3.wbc.service.ChefService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ce3.wbc.service.ChefService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("wbs/chefs")
@RequiredArgsConstructor
public class ChefController {

    private final ChefService chefService;

}
