package ce3.wbc.service;

import ce3.wbc.dto.UserDto;
import ce3.wbc.entity.User;
import ce3.wbc.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;



	public User getUser(Integer uId) {
		User user = userRepository.findById(uId)
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + uId));

		return user;
	}

	public UserDto createUser(UserDto userDto) {
		User user = User.of(
				userDto.getUserName(),
				passwordEncoder.encode(userDto.getUserPassword()),
				userDto.getUserId()
		);
		userRepository.save(user);
		return UserDto.toDto(user);
	}



}
