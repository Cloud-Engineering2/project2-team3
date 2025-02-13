package ce3.wbc.controller;

import ce3.wbc.controller.rto.request.RestaurantCreate;
import ce3.wbc.controller.rto.request.RestaurantEdit;
import ce3.wbc.controller.rto.request.RestaurantReq;
import ce3.wbc.controller.rto.response.RestaurantRes;
import ce3.wbc.dto.RestaurantDto;
import ce3.wbc.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantApiController {
    private final RestaurantService restaurantService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Page<RestaurantRes> getAllRestaurants(
            @PageableDefault(size = 6, sort = "restName", direction = Sort.Direction.ASC) Pageable pageable) {
        return restaurantService.findAllRestList(pageable).map(RestaurantRes::toResponse);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/chef/{chefId}")
    public Page<RestaurantRes> getChefRestaurants(@PathVariable int chefId,
                                                  @PageableDefault(size = 6, sort = "restName", direction = Sort.Direction.ASC) Pageable pageable) {
        System.out.println("셰프id = "+ chefId);
        return restaurantService.findRestList(chefId,pageable).map(RestaurantRes::toResponse);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/filter")
    public Page<RestaurantRes> getFilterRestaurants(@ModelAttribute RestaurantReq req,
                                                    @PageableDefault(size = 6, sort = "restName", direction = Sort.Direction.ASC)Pageable pageable) {
        return restaurantService.findFilterRestList(req,pageable).map(RestaurantRes::toResponse);
    }

    @GetMapping("/{restId}")
    public ResponseEntity<RestaurantRes> getRestaurantById(@PathVariable int restId){
        return new ResponseEntity<>(restaurantService.getRestaurantById(restId), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public RestaurantRes createRestaurant(@RequestPart(value = "restaurantCreate") @Valid RestaurantCreate restaurantCreate,
                                          @RequestPart(value = "file", required = false) MultipartFile file) {

        RestaurantDto restaurantDto = restaurantService.create(restaurantCreate, file);

        return RestaurantRes.toResponse(restaurantDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/edit/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public RestaurantRes editRestaurant(@PathVariable int id,
                                        @Valid @RequestBody RestaurantEdit restaurantEdit,
                                        @RequestPart(value = "file", required = false) MultipartFile file) {
        RestaurantRes updated = restaurantService.update(id, restaurantEdit,file);
        return updated;

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void  deleteRestaurant(@PathVariable int id) {
        restaurantService.delete(id);

    }

}
