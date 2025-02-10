package ce3.wbc.controller;

import ce3.wbc.controller.rto.response.ChefRes;
import ce3.wbc.dto.ChefDto;
import ce3.wbc.service.ChefService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/chefs")
@RequiredArgsConstructor
public class ChefController {

    private final ChefService chefService;

    @GetMapping
    public String getAllChefs(Model model) {
        List<ChefRes> chefs = chefService.getAllChefs();
        model.addAttribute("chefs", chefs);
        return "chefs";
    }

    @GetMapping("/groupedByCategory")
    public String getChefsGroupedByCategory(Model model) {
        Map<String, List<ChefRes>> groupedChefs = chefService.getChefsGroupedByCategory();
        model.addAttribute("groupedChefs", groupedChefs);
        return "groupedByCategory";
    }
}