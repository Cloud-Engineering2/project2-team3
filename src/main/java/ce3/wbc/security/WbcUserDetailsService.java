package ce3.wbc.security;

import ce3.wbc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WbcUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<WbcUserDetails> wbcUserDetails = userRepository.findByUserId(userId).map(WbcUserDetails::new);
        return wbcUserDetails.orElseThrow(() -> new UsernameNotFoundException(userId));

    }
}
