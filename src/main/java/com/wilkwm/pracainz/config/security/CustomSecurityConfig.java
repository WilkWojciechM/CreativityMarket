package com.wilkwm.pracainz.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
class CustomSecurityConfig {
    private static final String USER_ROLE = "CREATOR";
    private static final String EMPLOYER_ROLE = "EMPLOYER";
    private static final String ADMIN_ROLE = "ADMIN";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                .mvcMatchers("/admin/**").hasAnyRole(USER_ROLE, EMPLOYER_ROLE, ADMIN_ROLE)
                .anyRequest().permitAll()
        )
                //domyślny formularz logowania
               // .formLogin(Customizer.withDefaults());
                .formLogin(login -> login.loginPage("/login").permitAll())

                //z dokumentacji CSRF
                //https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html#servlet-considerations-csrf-logout
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout/**", HttpMethod.GET.name()))
                        .logoutSuccessUrl("/login?logout").permitAll());
                //H2 console enable
        http.csrf().ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"));
        http.headers().frameOptions().sameOrigin();
        return http.build();

    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().mvcMatchers(
                "/img/**",
                "/scripts/**",
                "/styles/**"
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
