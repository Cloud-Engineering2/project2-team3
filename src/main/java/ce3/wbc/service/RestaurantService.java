package ce3.wbc.service;
import ce3.wbc.entity.Restaurant;
import ce3.wbc.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

	public Restaurant getRestaurant(Integer restId) {
		Restaurant restaurant = restaurantRepository.findById(restId)
														.orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + restId));
//		RestaurantDto restaurantDto = RestaurantDto.toDto(restaurant);
		return restaurant;
	}
}
