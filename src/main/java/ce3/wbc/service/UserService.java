package ce3.wbc.service;

import ce3.wbc.dto.UserDto;
import ce3.wbc.entity.User;
import ce3.wbc.repository.RestaurantRepository;
import ce3.wbc.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

    public User getUser(Integer uId) {
		User user = userRepository.findById(uId)
								  	.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + uId));
		return user;
	}
    
    public UserDto getUserDto(Integer uId) {
		User user = getUser(uId);
		UserDto userDto = UserDto.toDto(user);
		return userDto;
	}

}
