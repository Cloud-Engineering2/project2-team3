package ce3.wbc.controller;

import ce3.wbc.controller.rto.request.RestaurantReq;
import ce3.wbc.controller.rto.response.RestaurantRes;
import ce3.wbc.service.PagingService;
import ce3.wbc.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final PagingService pagingService;

    @GetMapping("")
    public String getAllRestaurants(Model model,
                                    @PageableDefault(size = 6,
                                            sort = "restName",
                                            direction = Sort.Direction.ASC) Pageable pageable) {

        Page<RestaurantRes> restaurants = restaurantService.findAllRestList(pageable).map(RestaurantRes::toResponse);
        addPaging(restaurants, model, pageable);
        return "index";
    }

    @GetMapping("chef")
    public String getChefRestaurants(@RequestParam String chef,Model model,
                                     @PageableDefault(size = 6,
                                             sort = "restName",
                                             direction = Sort.Direction.ASC) Pageable pageable) {
        Page<RestaurantRes> restaurants = restaurantService.findRestList(chef, pageable).map(RestaurantRes::toResponse);
        model.addAttribute("chef", chef);
        addPaging(restaurants, model, pageable);
        return "index";
    }

    @GetMapping("filter")
    public String getFilterRestaurants(@ModelAttribute RestaurantReq req,
                                       @PageableDefault(size = 6,
                                               sort = "restName",
                                               direction = Sort.Direction.ASC) Pageable pageable,
                                       Model model) {
        Page<RestaurantRes> restaurants = restaurantService.findFilterRestList(req,pageable).map(RestaurantRes::toResponse);
        addPaging(restaurants, model, pageable);
        return "index";
    }

    private void addPaging(Page<RestaurantRes> restaurants, Model model, Pageable pageable) {
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("currentpage", pageable.getPageNumber()); //현재 페이지 번호
        model.addAttribute("pagesize", pageable.getPageSize()); // 한 페이지당 보여줄 데이터 개수
        model.addAttribute("totalPages", restaurants.getTotalPages());// 총 페이지
        model.addAttribute("totalElements", restaurants.getTotalElements()); // 총 갯수
        model.addAttribute("hasNext", restaurants.hasNext()); // 다음페이지있니?
        model.addAttribute("hasPrevious", pageable.hasPrevious()); // 이전페이지있니?

        //페이징 번호 리스트(1부터처리 UI로 진행)
        List<Integer> pagingNumbers = pagingService.getPageNumbers(pageable.getPageNumber(), restaurants.getTotalPages());
        model.addAttribute("pagingNumbers", pagingNumbers);

    }


}
