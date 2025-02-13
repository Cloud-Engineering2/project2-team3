package ce3.wbc.config;

import ce3.wbc.security.WbcAuthenticationFailureHandler;
import ce3.wbc.security.WbcAuthenticationSuccessHandler;
import ce3.wbc.security.WbcUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final WbcAuthenticationSuccessHandler wbcAuthenticationSuccessHandler;
    private final WbcAuthenticationFailureHandler wbcAuthenticationFailureHandler;
    private final WbcUserDetailsService wbcUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable());
        //인가
        security.authorizeHttpRequests((authorize ->
                authorize
                        .requestMatchers(HttpMethod.GET,"/restaurants/{restId}").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/restaurants/{restId}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users/sign-up").permitAll()
                        .requestMatchers(HttpMethod.POST, "/replies/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/replies/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/replies/**").authenticated()

                        .requestMatchers("/favicon.ico").permitAll()
                        .requestMatchers("/error", "/error/**").permitAll()
                        .anyRequest().permitAll()

        ));
        //로그인 폼사용
        security.formLogin(form -> form
                .loginPage("/users/login")
                .loginProcessingUrl("/users/login") //프론트엔드에서 action="/users/login"으로 설정할것
                .usernameParameter("userId")
                .successHandler(wbcAuthenticationSuccessHandler) // 로그인 처리
                .failureUrl("/login?error=true")
                .failureHandler(wbcAuthenticationFailureHandler)

        );

        //Spring Security기본설정 : POST /logout
        security.logout(logout -> logout.logoutSuccessUrl("/chefs")) //Spring Security기본설정 : POST /logout
                .logout(logout -> logout.logoutUrl("/users/logout") //FE GET/logout,추가
                        .logoutSuccessUrl("/chefs").invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"));

        // 세션
        security.sessionManagement(httpSecuritySessionManagementConfigurer
                -> httpSecuritySessionManagementConfigurer
                .invalidSessionUrl("/login")
                .sessionFixation().migrateSession()
                .maximumSessions(2)
                .expiredUrl("/login"));

        return security.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(wbcUserDetailsService); //WbcUserDetailsService
        authProvider.setPasswordEncoder(passwordEncoder()); //비밀번호 암호화 설정
        return authProvider;
    }

    //비밀번호 암호화 규칙
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity security) throws Exception {
        return security.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authenticationProvider())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}