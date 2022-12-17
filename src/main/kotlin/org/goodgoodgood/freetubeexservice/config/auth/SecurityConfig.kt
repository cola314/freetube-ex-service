package org.goodgoodgood.freetubeexservice.config.auth

import org.goodgoodgood.freetubeexservice.domain.user.Role
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val customOAuth2UserService: CustomOAuth2UserService,
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/**").hasRole(Role.USER.name)
                .anyRequest().authenticated()
            .and()
                .logout()
                    .logoutSuccessUrl("/")
            .and()
                .oauth2Login()
                    .userInfoEndpoint()
                        .userService(customOAuth2UserService)

        return http.build()
    }
}