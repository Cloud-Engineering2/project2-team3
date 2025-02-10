package ce3.wbc.controller;

import ce3.wbc.dto.ChefDto;
import ce3.wbc.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chefs")
public class ChefController {

    @Autowired
    private ChefService chefService;

    @GetMapping
    public String getAllChefs(Model model) {
        List<ChefDto> chefs = chefService.getAllChefs();
        model.addAttribute("chefs", chefs);
        return "chefs";
    }
}