package ce3.wbc.service;


import ce3.wbc.controller.rto.request.RestaurantCreate;
import ce3.wbc.controller.rto.request.RestaurantEdit;
import ce3.wbc.controller.rto.request.RestaurantReq;
import ce3.wbc.controller.rto.response.RestaurantRes;
import ce3.wbc.dto.RestaurantDto;
import ce3.wbc.entity.Chef;
import ce3.wbc.entity.Restaurant;
import ce3.wbc.entity.attribute.Address;
import ce3.wbc.repository.ChefRepository;
import ce3.wbc.repository.RestaurantRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.NoSuchElementException;
@Transactional
@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ChefRepository chefRepository;
    private final S3ImageService s3ImageService;

    private static final String DEFAULT_IMAGE_KEY = "default.jpg";

    public Page<RestaurantDto> findAllRestList(Pageable pageable) {
        Page<Restaurant> restaurants = restaurantRepository.findAll(pageable);
        return restaurants.map(RestaurantDto::toDto);
    }

    public Page<RestaurantDto> findRestList(String chef, Pageable pageable) {
        if (!chefRepository.existsByChefName(chef)) {
            throw new NoSuchElementException(String.format("%s를 찾을 수 없습니다", chef));
        }
        Page<Restaurant> restaurants = restaurantRepository.findRestaurantByChef_ChefName(chef, pageable);
        if (restaurants.isEmpty()) {
            throw new NoSuchElementException(String.format("%s 의 레스토랑을 찾을 수 없습니다", chef));
        }
        return restaurants.map(RestaurantDto::toDto);
    }

    // filtering
    public Page<RestaurantDto> findFilterRestList(RestaurantReq req , Pageable pageable) {
        // 동적 조건 받음
        Specification<Restaurant> spec = Specification.where(null);
        if (req.getRestName() != null) {
            spec = spec.and((root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("restName"), req.getChefName()));
        }
        if (req.getAddress() != null && req.getAddress().getCity() != null) {
            spec = spec.and((root, query, criteriaBuilder)
                    -> criteriaBuilder.like(root.get("address").get("city"), req.getAddress().getCity() + "%"));
        }
        if (req.getRestRental() != null) {
            spec = spec.and(((root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("restRental"), req.getRestRental())));
        }
        if (req.getGroupReservation() != null) {
            spec = spec.and(((root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("groupReservation"), req.getGroupReservation())));
        }
        if (req.getCorkage() != null) {
            spec = spec.and(((root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("corkage"), req.getCorkage())));
        }
        if (req.getNoKidsZone() != null) {
            spec = spec.and(((root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("noKidsZone"), req.getNoKidsZone())));
        }
        if (req.getChefName() != null) {
            spec = spec.and(((root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("chef").get("chefName"), req.getChefName())));
        }

        Page<Restaurant> filteredRest = restaurantRepository.findAll(spec, pageable);
        return filteredRest.map(RestaurantDto::toDto);

    }


    public RestaurantDto create(@Valid RestaurantCreate restaurantCreate, MultipartFile file) {
        String s3Key;
        String restEngName = restaurantCreate.getRestEngName();
        String originalImgName = (file != null) ? file.getOriginalFilename() : "default";

        if (file == null || file.isEmpty()) {
            s3Key = DEFAULT_IMAGE_KEY;
        } else {
            //s3이미지저장
            s3Key = s3ImageService.uploadS3(file, restEngName);
        }
        Chef chef = chefRepository.findByChefName(restaurantCreate.getChefName())
                .orElseThrow(() -> new IllegalArgumentException("해당 셰프가 존재하지 않습니다."));

        Address address = Address.of(
                restaurantCreate.getAddress().getCity(),
                restaurantCreate.getAddress().getStreet(),
                restaurantCreate.getAddress().getZipcode()
        );
        Restaurant restaurant = Restaurant.of(
                restaurantCreate.getRestName(),              // 레스토랑 이름
                s3Key,                                       // S3에 저장된 이미지 키 (restImg)
                originalImgName,                             // 원본 이미지명
                restaurantCreate.getRestPhone(),
                address,                                     // 주소 값타입
                restaurantCreate.isRestRental(),
                restaurantCreate.isGroupReservation(),
                restaurantCreate.isCorkage(),
                restaurantCreate.isNoKidsZone(),
                chef,                                    // 셰프 엔티티
                new ArrayList<>()// 댓글 리스트 (초기엔 빈 리스트)
        );
        Restaurant savedRestaurant  = restaurantRepository.save(restaurant);
        return RestaurantDto.toDto(savedRestaurant);

    }

    public RestaurantRes update(int id, RestaurantEdit restaurantEdit, MultipartFile file) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 레스토랑이 존재하지 않습니다. id: " + id));

        if (restaurantEdit.getRestId() != null && !restaurantEdit.getRestId().equals(id)) {
            throw new IllegalArgumentException("경로의 id와 요청의 id가 일치하지 않습니다.");
        }
        String updatedImage = restaurant.getRestImg(); // 기존 이미지 유지
        String updatedOriginalImgName = restaurant.getOriginalImgName(); // 기존 원본 이미지 유지

        if (file != null && !file.isEmpty()) {

            updatedImage = s3ImageService.uploadS3(file, restaurant.getRestName());
            updatedOriginalImgName = file.getOriginalFilename();
        }


        Address updatedAddress = restaurantEdit.getAddress() != null ?
                Address.of(
                        restaurantEdit.getAddress().getCity(),
                        restaurantEdit.getAddress().getStreet(),
                        restaurantEdit.getAddress().getZipcode()
                ) : restaurant.getAddress();


        Chef updatedChef = restaurantEdit.getChefName() != null ?
                chefRepository.findByChefName(restaurantEdit.getChefName())
                        .orElseThrow(() -> new IllegalArgumentException("해당 셰프가 존재하지 않습니다.")) :
                restaurant.getChef();


        Restaurant updatedRestaurant = Restaurant.of(
                restaurant.getRestId(), // 기존 ID 유지
                restaurantEdit.getRestName() != null ? restaurantEdit.getRestName() : restaurant.getRestName(),
                updatedImage, // 변경된 이미지 반영
                updatedOriginalImgName, // 변경된 원본 이미지명 반영
                restaurantEdit.getRestPhone() != null ? restaurantEdit.getRestPhone() : restaurant.getRestPhone(),
                updatedAddress,
                restaurantEdit.isRestRental(),
                restaurantEdit.isGroupReservation(),
                restaurantEdit.isCorkage(),
                restaurantEdit.isNoKidsZone(),
                updatedChef,
                restaurant.getComments() // 기존 댓글 유지
        );

        Restaurant savedRestaurant = restaurantRepository.save(updatedRestaurant);

        return RestaurantRes.toResponse(RestaurantDto.toDto(savedRestaurant));
    }



    public void delete(int id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 레스토랑이 존재하지 않습니다. id: " + id));
        restaurantRepository.delete(restaurant);
    }
}