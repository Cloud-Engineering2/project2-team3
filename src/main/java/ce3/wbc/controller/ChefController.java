package ce3.wbc.controller;

import ce3.wbc.controller.rto.request.ChefCreate;
import ce3.wbc.controller.rto.response.ChefRes;
import ce3.wbc.dto.ChefDto;
import ce3.wbc.service.ChefService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        return "main";
    }

    @GetMapping("/groupedByCategory")
    public String getChefsGroupedByCategory(Model model) {
        Map<String, List<ChefRes>> groupedChefs = chefService.getChefsGroupedByCategory();
        model.addAttribute("groupedChefs", groupedChefs);
        return "groupedByCategory";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ChefRes createChef (@RequestPart(value = "chefCreate") @Valid ChefCreate chefCreate,
                               @RequestPart(value = "file", required = false) MultipartFile file) {

        ChefDto chefDto = chefService.saveChef(ChefCreate.toChefDto(chefCreate), file);
        return ChefRes.toResponse(chefDto);
    }
}