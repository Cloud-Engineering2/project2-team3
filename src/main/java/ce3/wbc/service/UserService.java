package ce3.wbc.service;

import ce3.wbc.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RestaurantRepository restaurantRepository;



}
