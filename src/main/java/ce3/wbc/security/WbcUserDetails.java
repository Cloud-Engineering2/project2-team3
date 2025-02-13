package ce3.wbc.security;

import ce3.wbc.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class WbcUserDetails implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getUserRole().getRoleType()));
        // Spring Security 사용권장 : SimpleGrantedAuthority
    }

    @Override // 비밀버노인증
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override //Name인증
    public String getUsername() {
        return user.getUserId();
    }

    @Override  // 계정 잠금
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override // 비밀번호가 만료
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override // 계정이 만료됨
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override // 계정이 비활성화
    public boolean isEnabled() {
        return true;
    }
}



